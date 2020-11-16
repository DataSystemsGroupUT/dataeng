FROM jupyter/datascience-notebook

COPY requirements.txt requirements.txt

RUN pip install -r requirements.txt

USER root

# Install dependencies
RUN apt-get update && apt-get install -y \
  software-properties-common \
  curl

# Install Zulu OpenJdk 11 (LTS)
RUN add-apt-repository -y ppa:linuxuprising/java && apt-get autoclean

RUN mv /var/cache/apt/ /home/ && ln -s /home/apt/ /var/cache/apt

RUN apt-get update && \
    apt-get install -y openjdk-11-jre-headless && \
    apt-get clean;

# Unpack and install the kernel
RUN curl -L https://github.com/SpencerPark/IJava/releases/download/v1.3.0/ijava-1.3.0.zip > ijava-kernel.zip
RUN unzip ijava-kernel.zip -d ijava-kernel \
  && cd ijava-kernel \
  && python3 install.py --sys-prefix

# Install jupyter RISE extension.
RUN pip install jupyter_contrib-nbextensions RISE \
  && jupyter-nbextension install rise --py --system \
  && jupyter-nbextension enable rise --py --system \
  && jupyter contrib nbextension install --system \
  && jupyter nbextension enable hide_input/main

# Cleanup
RUN rm ijava-kernel.zip

USER $NB_USER

ENTRYPOINT ["jupyter","notebook","--NotebookApp.token=''"]