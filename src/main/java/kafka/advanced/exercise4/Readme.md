# Exercize 3: Writing a Simple Consumer 

To create a Producer in Java use the KafkaConsumer class which **NOT** thread safe.

- Useful Property reference:
    - bootstrap.servers=List of Broker host/port pairs used to establish the initial connection to the cluster
    - key.deserializer=Class used to deserialize the key. Must implement the Deserializer interface 
    - value.deserializer=Class used to deserialize the value. Must implement the Deserializer interface
    - group.id=A unique string that identifies the Consumer Group this Consumer belongs to.
    - enable.auto.commit=When set to true (the default), the Consumer will trigger offset commits based on the value of auto.commit.interval.ms (default 5000ms)