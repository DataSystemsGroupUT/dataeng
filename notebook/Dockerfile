FROM jupyter/datascience-notebook

COPY requirements.txt requirements.txt

RUN pip install -r requirements.txt

ENTRYPOINT ["jupyter","notebook","--NotebookApp.token=''"]