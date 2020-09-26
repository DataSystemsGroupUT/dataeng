## PreLab



### Windows users:

- We install it on windows using the MSI version (https://www.mongodb.com/try/download/community?tck=docs_server), cutomize the installation to "c:/mongodb"
- Add the "data/db"  and "logs"" dirs into the installation directory which you already customized.
- From the CMD **"As administrator"**, configure the logs and databases directories, and start the mongoDB service:
    -  from the "bin "directory run the following command>>> <code>mongod --directorydb --dpath c:\mongodb\data\db --logpath c:\mongodb\log\mongo.log --logappend --rest --install </code>

- Now we can run the mongodb service 
    - net start mongodb
- Putting your mongoDBHome/bin to the enviroment variables Paths:
    - so you can run the Shell of MongoDb using the command '>mongo'




### Docker users

Simply clone the repository and run

```bash
docker-compose up
```

The following docker compose file will build the notebook container which includes all the required dependencies.
Services are also exposed to the host network so you can connect to the via localhost.

[Open Jupyter](http://127.0.0.1:8888/)


```yaml
version: "3"

services:
  neo4j:
    image: neo4j
    ports:
      - 7474:7474 
      - 7687:7687
    volumes:
      - ./Neo4j:/var/lib/neo4j/import
    environment:
      - NEO4J_AUTH=none
  notebook:
    build: notebook/
    ports:
      - 8888:8888
    volumes:
       - ./:/home/jovyan/work/data
    environment:
      - GRANT_SUDO=yes
```     
