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
    dag_id='second_dag_stub',
    default_args=default_args_dict,
    catchup=False
)

task_one = DummyOperator(
    task_id='get_spreadsheet',
    dag=second_dag,
    trigger_rule='all_success',
    depends_on_past=False,
)

task_two = DummyOperator(
    task_id='transmute_to_csv',
    dag=second_dag,
    trigger_rule='all_success',
    depends_on_past=False,
)

task_three = DummyOperator(
    task_id='time_filter',
    dag=second_dag,
    trigger_rule='all_success',
    depends_on_past=False,
)

task_four = DummyOperator(
    task_id='emptiness_check',
    dag=second_dag,
    trigger_rule='all_success',
    depends_on_past=False,
)

task_five = DummyOperator(
    task_id='split',
    dag=second_dag,
    trigger_rule='all_success',
    depends_on_past=False
)

task_six_a = DummyOperator(
    task_id='create_intavolature_query',
    dag=second_dag,
    trigger_rule='all_success',
    depends_on_past=False
)

task_six_b = DummyOperator(
    task_id='create_composer_query',
    dag=second_dag,
    trigger_rule='all_success',
    depends_on_past=False,
)

task_seven_a = DummyOperator(
    task_id='insert_intavolature_query',
    dag=second_dag,
    trigger_rule='all_success',
    depends_on_past=False
)

task_seven_b = DummyOperator(
    task_id='insert_composer_query',
    dag=second_dag,
    trigger_rule='all_success',
    depends_on_past=False
)

join_tasks = DummyOperator(
    task_id='coalesce_transformations',
    dag=second_dag,
    trigger_rule='none_failed',
    depends_on_past=False
)

end = DummyOperator(
    task_id='end',
    dag=second_dag,
    trigger_rule='none_failed',
    depends_on_past=False
)
