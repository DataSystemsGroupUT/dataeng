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
    bash_command="curl https://www.lutemusic.org/spreadsheet.xlsx --output /opt/airflow/dags/{{ds_nodash}}.xlsx",
)

# oh noes :( it's xlsx... let's make it a csv.

task_two = BashOperator(
    task_id='transmute_to_csv',
    dag=first_dag,
    bash_command="xlsx2csv /opt/airflow/dags/{{ds_nodash}}.xlsx > /opt/airflow/dags/{{ds_nodash}}_correct.csv",
)

task_three = BashOperator(
    task_id='time_filter',
    dag=first_dag,
    bash_command="awk -F, 'int($31) > 1588612377' /opt/airflow/dags/{{ds_nodash}}_correct.csv > /opt/airflow/dags/{{ds_nodash}}_correct_filtered.csv",
)

task_four = BashOperator(
    task_id='load',
    dag=first_dag,
    bash_command="echo \"done\""
)

task_five = BashOperator(
    task_id='cleanup',
    dag=first_dag,
    bash_command="rm /opt/airflow/dags/{{ds_nodash}}_correct.csv /opt/airflow/dags/{{ds_nodash}}_correct_filtered.csv /opt/airflow/dags/{{ds_nodash}}.xlsx",
)

task_one >> task_two >> task_three >> task_four >> task_five
