# Assessment to run before the course start

### Q What are the characteristics of a time-serie (multichoice)
- all time-stamped data
- data are generated with regular time periods
- the volume of data is huge
- the variety of data is high 

### Q Why do we need time-series databases (all true, little marketing)
- because data ingestion is critical for data-intensive applications
- because temporal queries the center of analytics
- because continuous queries enable real-time analytics

## InfluxDB Basics

### What is the Line Protocol?
- the data format used for data ingestion into influxdb
- a communication protocol between Telegraf and influxDB
- an extension of AVRO for time-series serialization.

### What is telegraf
- a plugin-based server agent that can collect metrics from a wide variety of sources
- a influxdb plugin for data ingestion
- a source in flux

| |||||AIQ||||
|O3  |PM | CO2 | SO2 | NO2  |City  | Nation | Continent | timestamp  |
|----|---|----|----|----|-------|--------|-----------|------------|
|191 |54 | 27 | 1451 | 153 | London| UK     |Europe         |1590741668868|
|161 |331| 16 | 603 | 914  | Rome | Italy   |Europe         |1590741675932|
|419 |358| 31 | 46 | 894   | Milan | Italy  |Europe         |159074169693|

## Looking at the Table Above, can you identify the valid line-protocol line in the following ones? 

- A
```
AiQ,city=London,continent=Europe,nation=UK co2=16i,no2=896i,o3=403i,pm=356i,so2=1266i 1590741724944
```
- B
```
city=Milan,measurement=AiQ,continent=Europe,nation=Italy co2=23i,no2=1138i,o3=566i,pm=33i,so2=391i 1590741731846
```
- C
```
AiQ,o3=2i,city=Liverpool,co2=10i,pm=146i,continent=Europe,no2=1182i,nation=UK,so2=1446i 1590741731946
```

##  Time-Series

## How would you describe the time-series in figure
![](./synch.pdf)

- A: they are not synchronized
- B: S1 is synchronized with S3
- C: S1 is synchronized with S2

answer :A

## In Stream Processing, windows are a very important abstraction because:
- A: they allow dealing with infinite nature of data stream
- B: they regularize the stream rate making the processing predictable
- C: they reduce the memory consumption of aggregates over streams

answer: A

## In Time-Series DB, windows are a very important abstraction because:
- A: they allow dealing with infinite nature of data stream
- B: they regularize the stream rate making the processing predictable
- C: they reduce the memory consumption of aggregates over streams


