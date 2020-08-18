# Assessment to run after the course 

### Q From Buckets to Tables, which Flux scripts has the effect illustrated?
![Image 1](./asmnt1-range1.pdf)

####TODO: posso fare un'immagine unica
- A 
```go
from(bucket: "assessment") 
|> range(start: -1s) 
|> filter(fn: (r) => r._measurement == "AiQ")   
|> group(columns: ["_measurement", "_field"])
```
- B 
```go
from(bucket: "assessment") 
|> range(start: -1s) 
|> filter(fn: (r) => r._measurement == "AiQ")  
```
- C 
```go
from(bucket: "assessment") 
|> range(start: -1s) 
|> filter(fn: (r) => r._measurement == "AiQ" 
        and r._field=="co2")
```

- D
```go
from(bucket: "assessment") 
|> range(start: v.start, stop: v.stop) 
|> filter(fn: (r) => r._measurement == "AiQ")  
|> group(columns: ["_measurement", "continent", "nation","_field"])
```

answer: **B**. A and D are wrong because they contain unnecessary groups clauses that use shape with a different group key. C is wrong because it does add an additional filter to output.

### Q Every Flux query must include:
#### TODO: va ripensata ma query secondo me non è un dag
- A:  At most one data source, at most one time filter (range) and any number of data filters, shaping, processing steps;
- B:  A processing step regardless the filters and sources;
- C:  At **least** a data source and a time-range, and a filter or shaping step;
- D:  At **most** a data source and a time-range, and a processing or shaping step;

Answer: B

### Q Which Flux script contains a shaping step (multichoice);
- A 
```go
from(bucket: "assessment") 
|> range(start: -1s) 
|> filter(fn: (r) => r["_measurement"] == "AiQ")  
|> last()
```
- B
```go
from(bucket: "assessment") 
|> range(start: -1s) 
|> filter(fn: (r) => r["_measurement"] == "AiQ")  
|> max() 
|> map(fn: (r)=> ({_time: r._stop, _value=r._value*r._value}))
```
- C
```go 
from(bucket: "assessment") 
|> range(start: -1s) 
|> filter(fn: (r) => r["_measurement"] == "AiQ")  
|> group(columns: ["_measurement", "continent", "nation","_field"]) 
|> aggregateWindow(every: 10s, fn: mean) 
```
- D
```go
from(bucket: "assessment") 
|> range(start: -1s) 
|> pivot(rowKey:["_time"], columnKey: ["_field"], valueColumn: "_value")
```

Answer: **C** with group and **D** with pivot

### Q Which Flux script contains a processing step (multichoice);

- A 
```go
from(bucket: "assessment") 
|> range(start: -1s) 
|> filter(fn: (r) => r["_measurement"] == "AiQ")  
|> last()
```
- B
```go
from(bucket: "assessment") 
|> range(start: -1s) 
|> filter(fn: (r) => r["_measurement"] == "AiQ")  
|> max() 
|> map(fn: (r)=> ({_time: r._stop, _value=r._value*r._value}))
```
- C
```go 
from(bucket: "assessment") 
|> range(start: -1s) 
|> filter(fn: (r) => r["_measurement"] == "AiQ")  
|> group(columns: ["_measurement", "continent", "nation","_field"]) 
|> aggregateWindow(every: 10s, fn: mean) 
```
- D
```go
from(bucket: "assessment") 
|> range(start: -1s) 
|> pivot(rowKey:["_time"], columnKey: ["_field"], valueColumn: "_value")
```

Answer: **D** with pivot and **B** with max/map

### Q Which Flux script produces the illustrated behavior?
![img1](./asmnt1-filter.pdf)

- A
```go
filter(fn: (r) => r.city == "Rome" and r.city == "Liverpool")
```

- B
```go
map(fn: (r) => r.city == "Milan")
```
- C
```go
filter(fn: (r) => r.city == "Rome") 
|> filter(fn: (r) => r._field == "London")
```
- D
```go
filter(fn: (r) => r.city == "London" or r.city == "Milan")
```


### Q Which Flux script produces the illustrated behavior?
![img1](./asmnt1-group1.pdf)

- A
```go 
group[columns: ["_field"])
```
- B
```go
group[columns: ["continent","nation","_field"])
```
- C
```go
group[columns: ["continent","nation","city","_field"])
```
- D
```go
pivot(columns: ["continent","nation","_field"])
```

answer: B

### Q Which Flux script produces the illustrated behavior?
![img1](./asmnt1-mean1.pdf)

- A
```go
mean() |> map(fn: (r) => ({ r with _time: r._stop}))
```
- B
```go
mean() |> map(fn: (r) => ({_time: r._stop})
```
- C
```go
mean()
```
- D
```go
map( fn(r) => ({_time: r._stop, _value:mean()}))
```

answer: A, because *mean* removes the column "\_time" but *map-with* adds it back

### Q Which Flux script produces the illustrated behavior?
![img1](./asmnt1-last.pdf)
#### secondo Emanuele è sbagliata
- A
```go
last() |> map(fn: (r) => ({ r with _time: r._stop}))
```
- B
```go
last()
```
- C
```go
max()
```
- D
```go
map( fn(r) => ({_time: r._stop, _value:last}))
```

answer: B, because *last* does not removes the column "\_time" 


### Q every flux query 
    - A: must have at most a range clause 
    - B: must have at least a range clause 
    - C: must have at one and only one range clause 
    - D: must have a least filter clause 

#### secondo Emanuele è da riformulare, ma senza la range (B) non funziona flux

### Q What of the following operations *MAY* changes the number of tables in the result (multichoice) (corner cases excluded, e.g., empty table or group by all)
    - A: filter
    - B: group
    - C: mean 
    - D: last

Answers: A,B,C

### Q What of the following operations MAY change the group key of tables in the result (multichoice)
    - A: aggregateWindow
    - B: group
    - C: mean 
    - D: last

Answer: B,C

### Q What does best describe the aggregateWindow function:
    - A: it always changes the number of tables in the result 
    - B: it is just a process step
    - C: it eases the down-sampling task

Answer: C
### Q To what of the following operators does the "createEmpty" parameter belong    :
    - A: filter, and it always changes the number of tables in the result
    - B: filter, and it never changes the number of tables in the result
    - C: aggregateWindow and it can change the number of tables in the result
    - D: aggregateWindow, and it does not change the number of tables in the result 

Answer: C

### Q in a functional programming language, the map() function
   - A: applies a function to each element of a collection 
   - B: applies a function to all the elements of a collection
   - C: applies a function to any element of a collection
   - D: applies a function to some element of a collection 
   
answers: A

### Q  in Flux, the map() function   
    - A:  does always add a column to each table 
    - B:  may add a column to each table if the with clause is used 
    - C:  is the only function that takes a function as input 

Answer: A

### Q in a pipe-forwardable function with a parameter (t=<-), t represents
- A: input bucket that the function iterate over 
- B: input columns that the function applies on
- C: input tables that the function applies to 

Answer C
### Q A bucket is 
- A a data structure with retention policy 
- B named data source with retention policy 
- C the storage layer of Flux data 

Answer: A

### Q  the operator |> is callsed
- A forward-piper
- B Forwarder
- C Pipe
- D Pipe-forward

answer D

### Q What’s the Pipe-forward operator |>  for?
- A: chains operations through an implicit data flow
- B: combines operators to foster optimization
- C: links buckets with complex data structures

answer A

### Q What’s a Table in Flux?
- A: a part of a result only
- B: a part of an input only
- C: a part of an input or a result
- D: a part of an input and a result

Answer C

### Q What’s a Group Key in Flux?
- A: list of unique tag/field values that identifies a row in a table
- B: list of tags/field for which every row in a table has the same value 
- C: list of rows for which every row in a table has the same value 
- D: list of values for which every row in a table has the same tags 

Answer A

### Q Before joining two time-series T1 and T2 such that T1 is regular but T2 is not we should:
- A: Align T1 and T2 using timeShift
- B: Align T1 and T2 using aggregateWindow 
- C: Align T1 and T2 using timeShift and then using an aggregateWindow
- D: none

answer: B

### Q Before joining two time-series T1 and T2 such that have the same rate, but metrics occur at a different times we should:
- A: Align T1 and T2 using timeShift 
- B: Align T1 and T2 using aggregateWindow
- C: Align T1 and T2 using timeShift and then using an aggregateWindow
- D: Do nothing 

answer: A

### Q Before joining two time-series T1 and T2 such that they are regular, they have the same rate, and metrics occur at the same times we should:
- A: Align T1 and T2 using timeShift 
- B: Align T1 and T2 using aggregateWindow
- C: Align T1 and T2 using timeShift and then using an aggregateWindow
- D: Do nothing 

answer: D

### Q Before joining two time-series T1 and T2 such that they are regular, but they do not have the same rate nor metrics occur at the same times
- A: Align T1 and T2 using timeShift 
- B: Align T1 and T2 using aggregateWindow 
- C: Align T1 and T2 using timeShift and then using an aggregateWindow
- D: Do nothing 

answer: B

### Q Before joining two time-series T1 and T2 we should (multichoice):
- A: align T1 and T2 using timeShift if they have the same rate, but metrics occur at a different times 
- B: align T1 and T2 using aggregateWindow  if T1 is regular but T2 is not
- C: do nothing
- D: align T1 and T2 using timeShift and then aggregateWindow.

A,B

### Q The operation chain 
```go
group(columns: list1) |> group(columns: list2)
```

is equivalent to

```go
group(columns: list1++list2)
```

when:
-  A: when list1 ∩ list2=∅; 
-  B: when list1 ⊆ list2; 
-  C: when list1 ⊆ list1; 
-  D: when list1 = list1; 

answer: D