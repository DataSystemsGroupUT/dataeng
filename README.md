### PreLab

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