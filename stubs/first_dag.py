import airflow
import datetime
from airflow import DAG
from airflow.operators.bash_operator import BashOperator

default_args_dict = {
    'start_date': airflow.utils.dates.days_ago(0),
    'concurrency': 1,
    'schedule_interval': None,
    'retries': 1,
    'retry_delay': datetime.timedelta(minutes=5),
}

first_dag = DAG(
    dag_id='first_dag',
    default_args=default_args_dict,
    catchup=False,
)

# Downloading a file from an API/endpoint?

task_one = BashOperator(
    task_id='get_spreadsheet',
    dag=first_dag,
    bash_command="curl http://www.gerbode.net/spreadsheet.xlsx --output /usr/local/airflow/data/{{ds_nodash}}.xlsx",
    depends_on_past=False,
)

# oh noes :( it's xlsx... let's make it a csv.

task_two = BashOperator(
    task_id='transmute_to_csv',
    dag=first_dag,
    bash_command="xlsx2csv /usr/local/airflow/data/{{ds_nodash}}.xlsx > /usr/local/airflow/data/{{ds_nodash}}_correct.csv",
    trigger_rule='all_success',
    depends_on_past=False,
)

# Now we have to filter out
# Instead of an arbitrary value like 1588612377 we'd have something more akin to {{ execution_date.int_timestamp }}

task_three = BashOperator(
    task_id='time_filter',
    dag=first_dag,
    bash_command="awk -F, 'int($31) > 1588612377' /usr/local/airflow/data/{{ds_nodash}}_correct.csv > /usr/local/airflow/data/{{ds_nodash}}_correct_filtered.csv",
    trigger_rule='all_success',
    depends_on_past=False,
)

task_four = BashOperator(
    task_id='load',
    dag=first_dag,
    bash_command="rm /usr/local/airflow/data/{{ds_nodash}}_correct.csv /usr/local/airflow/data/{{ds_nodash}}_correct_filtered.csv /usr/local/airflow/data/{{ds_nodash}}.xlsx",
    trigger_rule='all_success',
    depends_on_past=False,
)

task_one >> task_two >> task_three >> task_four
