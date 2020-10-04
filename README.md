## Introduction:
### Neo4J:
   <a href="https://neo4j.com/">Neo4J</a>  is a graph database management system developed by Neo4j, Inc. Described by its developers as a native graph storage and processing. It is the most popular graph database according to DB-Engines ranking, and the 22nd most popular database overall.<br/>
<div style="text-align:center"><img src="https://neo4j.com/wp-content/themes/neo4jweb/assets/images/neo4j-logo-2015.png"></div>


### The Property Graph Model
* In the property graph model, data is organized as nodes, relationships, and properties (data stored on the nodes or relationships).

<div style="text-align:center"><img src= 'https://dist.neo4j.com/wp-content/uploads/property_graph_elements.jpg' width='600'></div>


###  Cypher Query Language

Cypher is Neo4j’s graph query language that allows users to store and retrieve data from the graph database. Cypher’s syntax provides a visual and logical way to match patterns of nodes and relationships in the graph.
t is a declarative, SQL-inspired language for describing visual patterns in graphs using ASCII-Art syntax. It allows us to state what we want to select, insert, update, or delete from our graph data without a description of exactly how to do it.

- First Steps with Cypher can be followed from this <a href='https://neo4j.com/developer/cypher/'>link </a>.

### Neo4j PreLab
#### Windows users:


- Install neo4j 3.5 Community Edition followin this <a href='https://neo4j.com/download-thanks/?edition=community&release=3.5.21&flavour=winzip&_ga=2.186751336.1137015944.1598288824-1813280835.1541597058'>link </a>

- Notes:

 * Click  <a href= 'https://neo4j.com/download-thanks-desktop/?edition=desktop&flavour=winstall64&release=1.3.4&offline=true'> here </a> to download the latest version of neo4j DeskTop for Windows.
 * Follow the installation guide provided <a href= 'https://neo4j.com/download-thanks-desktop/?edition=desktop&flavour=winstall64&release=1.3.4&offline=true#installation-guide' > here </a> to download, install neo4j DEsktop on your Windows.
 * make sure that you run Neo4j as a console application, use: 
     - <b> NEO4J_HOME\bin\neo4j console </b>
     - Or install Neo4j as a service using: <b>NEO4J_HOME\bin\neo4j install-service </b>
 
#### Linux users:

Follow this detailed instructions in this [link](https://neo4j.com/docs/operations-manual/current/installation/linux/).

#### MacOs users

- Download Neo4J last version
- Download JVM vs. 11: https://adoptopenjdk.net/variant=openjdk11&jvmVariant=openj9
- Set HOME PATH for Neo4J on ~/.bash_profile file
- Launch Neo4J via CL:
    - cd to the neo4j bin directory and
    -  /.neo4j start
- Verify that Neo4J is reachable at the URL  http://localhost:7474/
