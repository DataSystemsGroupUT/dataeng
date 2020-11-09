## Table of contents

* What is Airflow?
* What's the point of Airflow?
* What can and can't it do?
* The Dag
* The Operators
* Trigger Rules

## Requirements

* `sudo apt-get install python3-dev`
* To have docker *and* docker-compose installed.
* Install docker and docker-compose exactly as it is described in the website.
* **do not do do apt install docker or docker-compose**

## How to spin the webserver up

Run this, docker build, *once*
```shell script

sudo docker build -t airflow-training:1.0 .

```

### command dissection

* docker - err...the docker cli tool :D 
* build - run the code written on the Dockerfile
* -t tag the image with the following name:version naming convention

```shell script

sudo docker-compose -f docker-compose.yml up -d

```

### command dissection

* docker-compose - err... the docker-compose cli tool :D 
* -f - run the compose file 
* up - aggregates all logs to the same stdout
* -d - runs in the background i.e doesn't take over your current bash session

shut down everything

```shell script

sudo docker-compose down

```

## Acknowledgements

The dockerfile and entrypoint are modified versions of those taken from: https://github.com/puckel/docker-airflow and https://github.com/happilyeverafter95/slack-airflow
