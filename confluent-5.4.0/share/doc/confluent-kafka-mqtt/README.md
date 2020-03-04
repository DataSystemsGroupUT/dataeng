# New Wave
A collection of proxies to Apache Kafka 

Current list: 
  * `kafka-mqtt` is a proxy connecting MQTT clients to Apache Kafka.

## Running in IDE:
Please add the following VM option in order for log4j to work properly when launching the project from IDE:
```
-Dlog4j.configuration=file:./kafka-mqtt/src/test/resources/log4j.properties
```
## Using start/stop scripts
### For development environment:
```
mvn clean package
kafka-mqtt/bin/kafka-mqtt-start kafka-mqtt/config/kafka-mqtt-dev.properties
```
### For deb/rpm installation:
```
kafka-mqtt-start -daemon /etc/confluent-kafka-mqtt/kafka-mqtt-dev.properties
```
### For archive installation:
```
cd $CONFLUENT_HOME
bin/kafka-mqtt-start -daemon etc/confluent-kafka-mqtt/kafka-mqtt-dev.properties
```
## Settings for communication between `kafka-mqtt` and MQTT client
### Security settings
6 modes are supported: `PLAINTEXT`, `SSL`, `TLS`, `SASL_PLAINTEXT`, `SASL_SSL`, `SASL_TLS`,
which can be configured by setting `listeners.security.protocol` parameter.
By default, un-authenticated, non-encrypted channel is used. 

| `listeners.security.protocol` | Authenticated | Encrypted  |
|-------------------------------|---------------|------------|
| PLAINTEXT                     |       No      |     No     |
| SSL                           |       No      |    Yes     |
| TLS                           |       No      |    Yes     |
| SASL_PLAINTEXT                |      Yes      |     No     |
| SASL_SSL                      |      Yes      |    Yes     |
| SASL_TLS                      |      Yes      |    Yes     |

### Authentication settings
To configure and use authentication, you have to set `listeners.security.protocol=SASL_PLAINTEXT`,
`listeners.security.protocol=SASL_SSL` or `listeners.security.protocol=SASL_TLS`
and pass a regular JAAS config file as VM option `-Djava.security.auth.login.config=<JAAS config file>`.
`kafka-mqtt` ships with a default login module,
`io.confluent.mqtt.protocol.security.PropertyFileLoginModule`,
which authenticates users against a local properties file.
It is for demonstration purposes only thus should not be used in production environments.
#### Sample JAAS config file
```
ConfluentKafkaMqtt {
  io.confluent.mqtt.protocol.security.PropertyFileLoginModule required
  file="/tmp/credentials.txt";
};
```
### Encryption settings
By default, encryption is disabled. To enable it, you have to set `listeners.security.protocol=SSL`,
`listeners.security.protocol=SASL_SSL`, `listeners.security.protocol=TLS` or
`listeners.security.protocol=SASL_TLS` and pass desired `org.apache.kafka.common.config.SslConfigs`.
For more details, please refer to this page - https://docs.confluent.io/current/tutorials/security_tutorial.html.
In order to debug encryption issues, add this VM option: `-Djavax.net.debug=all`.

## Settings for communication between `kafka-mqtt` and Kafka
### Security settings
3 modes are supported: `PLAINTEXT`, `SSL`, `TLS`, which can be configured by setting
`producer.security.protocol` parameter.
By default, un-authenticated, non-encrypted channel is used.

| `producer.security.protocol`  | Authenticated | Encrypted  |
|-------------------------------|---------------|------------|
| PLAINTEXT                     |       No      |     No     |
| SSL                           |       No      |    Yes     |
| TLS                           |       No      |    Yes     |
### Authentication settings
Authentication is not currently supported.
