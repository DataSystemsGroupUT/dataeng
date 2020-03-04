# Exercize 3: Writing a Simple Producer 

To create a Producer in Java use the KafkaProducer class

- This class is thread safe; sharing a single Producer instance across threads will typically be
faster than having multiple instances

- Create a Properties object, and pass that to the Producer
- Useful Property reference:
    - bootstrap.servers=List of Broker host/port pairs used to establish the initial connection to the cluster
    - key.serializer=Class used to serialize the key. Must implement the Serializer interface
    - value.serializer=Class used to serialize the value. Must implement the Serializer interface
    - compression.type=How data should be compressed. Values are none, snappy, gzip, lz4. Compression is performed on batches of records
    - acks=Number of acknowledgment the Producer requires the leader 
            to have before considering the request complete. 
            This controls the durability of records. 
            - acks=0: Producer will not wait for any acknowledgment from the server; 
            - acks=1: Producer will wait until the leader has written the record to its local log; 
            - acks=all: Producer will wait until all in- sync replicas have acknowledged receipt of the record
