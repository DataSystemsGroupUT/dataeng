
### Notes 

examples 1 and 2 rely on some knowledge about frequency of the event
example 3 relies only on the maximum duration

### Other cool stuff

Report to the uber user who asked for a car in the last 15 minutes but did not found one, that a car near him is now available. 

### mapping available somewhere
```cypher
LOAD FROM kafka://discountstream  WITH MAPPINGS
```

### Streaming Ingestion one element at time

As it is right now with Kafka
```
CALL streams.consume('my-topic') YIELD event
CREATE (p:Person{firstName: event.data.name, lastName: event.data.surname})
```

using vocals to de-reference the stream

```cypher
LOAD CSV/JSON/RDF/PGRAPH FROM <streamsource>
CREATE (:Artist {name: line.Name, year: toInteger(line.Year)})
```

but the graph remains static. How to enable continuous queries

```cypher
LOAD CSV <streamsource>
CREATE STREAM (:Artist {name: line.Name, year: toInteger(line.Year)}) implicit {stream:<source>}
WINDOW SLIDE/HOP->EVERY/SESSIOn
```
