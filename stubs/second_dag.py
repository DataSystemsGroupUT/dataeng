import airflow
import datetime
import urllib.request as request
import pandas as pd
from airflow import DAG
from airflow.operators.bash_operator import BashOperator
from airflow.operators.python_operator import PythonOperator, BranchPythonOperator
from airflow.operators.dummy_operator import DummyOperator
from airflow.operators.postgres_operator import PostgresOperator

default_args_dict = {
    'start_date': datetime.datetime(2020, 6, 25, 0, 0, 0),
    'concurrency': 1,
    'schedule_interval': "0 0 * * *",
    'retries': 1,
    'retry_delay': datetime.timedelta(minutes=5),
}

second_dag = DAG(
    dag_id='second_dag',
    default_args=default_args_dict,
    catchup=False,
    template_searchpath=['/usr/local/airflow/data/']
)


# Downloading a file from an API/endpoint?

def _get_spreadsheet(epoch, url, output_folder):
    request.urlretrieve(url=url, filename=f"{output_folder}/{epoch}.xlsx")


task_one = PythonOperator(
    task_id='get_spreadsheet',
    dag=second_dag,
    python_callable=_get_spreadsheet,
    op_kwargs={
        "output_folder": "/usr/local/airflow/data",
        "epoch": "{{ execution_date.int_timestamp }}",
        "url": "http://www.gerbode.net/spreadsheet.xlsx"
    },
    trigger_rule='all_success',
    depends_on_past=False,
)

# oh noes :( it's xlsx... let's make it a csv.

task_two = BashOperator(
    task_id='transmute_to_csv',
    dag=second_dag,
    bash_command="xlsx2csv /usr/local/airflow/data/{{ execution_date.int_timestamp }}.xlsx > /usr/local/airflow/data/{{ execution_date.int_timestamp }}_correct.csv",
    trigger_rule='all_success',
    depends_on_past=False,
)


# Now we have to filter out

def _time_filter(previous_epoch: int, epoch: int, next_epoch: int, output_folder: str):
    df = pd.read_csv(f'{output_folder}/{str(epoch)}_correct.csv')
    df_filtered = df[(df['Modified'] >= int(previous_epoch)) & (df['Modified']) <= int(next_epoch)]
    df_filtered.to_csv(path_or_buf=f'{output_folder}/{str(previous_epoch)}_filtered.csv')


task_three = PythonOperator(
    task_id='time_filter',
    dag=second_dag,
    python_callable=_time_filter,
    op_kwargs={
        "output_folder": "/usr/local/airflow/data",
        "epoch": "{{ execution_date.int_timestamp }}",
        "previous_epoch": "{{ prev_execution_date.int_timestamp }}",
        "next_epoch": "{{ next_execution_date.int_timestamp }}",
    },
    trigger_rule='all_success',
    depends_on_past=False,
)


def _emptiness_check(previous_epoch: int, output_folder: str):
    df = pd.read_csv(f'{output_folder}/{str(previous_epoch)}_filtered.csv')
    length = len(df.index)
    if length == 0:
        return 'end'
    else:
        return 'split'


task_four = BranchPythonOperator(
    task_id='emptiness_check',
    dag=second_dag,
    python_callable=_emptiness_check,
    op_kwargs={
        'previous_epoch': '{{ prev_execution_date.int_timestamp }}',
        "output_folder": "/usr/local/airflow/data"
    },
    trigger_rule='all_success',
)


def _split(previous_epoch: int, output_folder: str):
    df = pd.read_csv(f'{output_folder}/{str(previous_epoch)}_filtered.csv')

    df = df.replace(',|"|\'|`', '', regex=True)

    df_intavolature = df[['Piece', 'Type', 'Key', 'Difficulty', 'Date', 'Ensemble']]
    df_composer = df['Composer'].drop_duplicates()

    df_intavolature.to_csv(path_or_buf=f'{output_folder}/{str(previous_epoch)}_intavolature.csv')
    df_composer.to_csv(path_or_buf=f'{output_folder}/{str(previous_epoch)}_composer.csv')


task_five = PythonOperator(
    task_id='split',
    dag=second_dag,
    python_callable=_split,
    op_kwargs={
        'output_folder': '/usr/local/airflow/data',
        'previous_epoch': '{{ prev_execution_date.int_timestamp }}',
    },
    trigger_rule='all_success',
)


def _create_intavolature_query(previous_epoch: int, output_folder: str):
    df = pd.read_csv(f'{output_folder}/{str(previous_epoch)}_intavolature.csv')
    with open("/usr/local/airflow/data/intavolature_inserts.sql", "w") as f:
        df_iterable = df.iterrows()
        f.write(
            "CREATE TABLE IF NOT EXISTS intavolature (\n"
            "title VARCHAR(255),\n"
            "subtitle VARCHAR(255),\n"
            "key VARCHAR(255),\n"
            "difficulty VARCHAR(255),\n"
            "date VARCHAR(255),\n"
            "ensemble VARCHAR(255));\n"
        )
        for index, row in df_iterable:
            piece = row['Piece']
            type = row['Type']
            key = row['Key']
            difficulty = row['Difficulty']
            date = row['Date']
            ensemble = row['Ensemble']

            f.write(
                "INSERT INTO intavolature VALUES ("
                f"'{piece}', '{type}', '{key}', '{difficulty}', '{date}', '{ensemble}'"
                ");\n"
            )

        f.close()


task_six_a = PythonOperator(
    task_id='create_intavolature_query',
    dag=second_dag,
    python_callable=_create_intavolature_query,
    op_kwargs={
        'previous_epoch': '{{ prev_execution_date.int_timestamp }}',
        'output_folder': '/usr/local/airflow/data',
    },
    trigger_rule='all_success',
)


def _create_composer_query(previous_epoch: int, output_folder: str):
    df = pd.read_csv(f'{output_folder}/{str(previous_epoch)}_composer.csv')
    with open("/usr/local/airflow/data/composer_inserts.sql", "w") as f:
        df_iterable = df.iterrows()
        f.write(
            "CREATE TABLE IF NOT EXISTS composer (\n"
            "name VARCHAR(255)\n"
            ");\n"
        )
        for index, row in df_iterable:
            composer = row['Composer']

            f.write(
                "INSERT INTO composer VALUES ("
                f"'{composer}'"
                ");\n"
            )


task_six_b = PythonOperator(
    task_id='create_composer_query',
    dag=second_dag,
    python_callable=_create_composer_query,
    op_kwargs={
        'previous_epoch': '{{ prev_execution_date.int_timestamp }}',
        'output_folder': '/usr/local/airflow/data',
    },
    trigger_rule='all_success',
)

task_seven_a = PostgresOperator(
    task_id='insert_intavolature_query',
    dag=second_dag,
    postgres_conn_id='postgres_not_default',
    sql='intavolature_inserts.sql',
    trigger_rule='all_success',
    autocommit=True
)

task_seven_b = PostgresOperator(
    task_id='insert_composer_query',
    dag=second_dag,
    postgres_conn_id='postgres_not_default',
    sql='composer_inserts.sql',
    trigger_rule='all_success',
    autocommit=True,
)

join_tasks = DummyOperator(
    task_id='coalesce_transformations',
    dag=second_dag,
    trigger_rule='none_failed'
)

end = DummyOperator(
    task_id='end',
    dag=second_dag,
    trigger_rule='none_failed'
)

task_one >> task_two >> task_three >> task_four
task_four >> [task_five, end]
task_five >> [task_six_a, task_six_b]
task_six_a >> task_seven_a
task_six_b >> task_seven_b
[task_seven_a, task_seven_b] >> join_tasks
join_tasks >> end
