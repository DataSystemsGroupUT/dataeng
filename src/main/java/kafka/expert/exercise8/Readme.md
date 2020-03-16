# Exercize 9: Use AVRO for sharing the data

The exercise uses the following example: there is a building with N floors and M rooms per floor.

Rooms are equipped with sensors that measures different things, e.g., temperature, humidity, energy usage.

Create the various sensors:

- Termometer (Temperature Producer)
- Termometer (Temperature Producer)

## Creating the Avro Schemas
You need to create an Avro schema for the key and prefVal of the message.

1. Make sure you are in the right directory. The *.avsc schema files should be in the relative path
kafka_avro/src/main/avro
2. Create a schema for the key with following characteristics:
• The name should be ShakespeareKey
• A unique namespace, e.g. partial.model
• The schema should have a field for the name of the work, e.g., Julius Caesar
• The schema should have a field for the year the work was published, e.g., 1599
3. Create a schema for the prefVal with following characteristics: • The name should be ShakespeareValue
• A unique namespace, e.g. partial.model
• The schema should have a field for the line number
• The schema should have a field for the line itself


```json
{"namespace": "package",
 "type": "record",
 "name": "Location",
 "fields": [
     {"name": "building", "type": "string", "doc" : "The building address"},
     {"name": "floor", "type": "int", "doc" : "The floor number"},
     {"name": "number", "type": "int", "doc" : "The room number"}
 ]
}
```


```json
{"namespace": "kafka.expert.exercise8",
 "type": "record",
 "name": "Observation",
 "fields": [
     {"name": "id", "type": "long", "doc" : "The observation id"},
     {"name": "prefVal", "type": "double", "doc" : "The actual measurement from the sensor"},
     {"name": "measurement", "type": "string", "doc" : "The measurement type, e.g., temperature"},
     {"name": "timestamp", "type": "long", "doc" : "The measurement timestamp"}
 ]
}
```

Exercise:

read and write sensor data from kafka

calculate average temperature per room