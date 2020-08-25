footer:  [Riccardo Tommasini](http://rictomm.me) - riccardo.tommasini@ut.ee - @rictomm - 
slidenumbers: true
<!-- : #course, #topic, #LTAT.02.007 -->

### Querying the Data
[.text: text-scale(2.0)]

![left](./attachments/Processing%20and%20Querying.png)

Generic term that describes data manipulation.


A query is a request for data or information from a database.

### Programming with Streams: Declarative Languages 

![right fit](./attachments/Images/programming_step1.pdf)

*The key idea of declarative programming is that a program is a theory
in some suitable logic, and the computation is deduction from the
theory*
																									-- J.W. Lloyd

### Declarative Languages: Why? 

-  Writing the optimal solution is as hard as solving the problem (e.g.
  JOIN optimisation)

-  We want to enhance programmer productivity by adding Domain-Specific
  abstraction (e.g. streams)

-  We want to limit the expressiveness of the languages to ensure some
  nice property (e.g. decidability)

### Declarative Languages: Why? 

![inline](./attachments/Images/flyby.png)

### Declarative Languages: Why 

![inline](./attachments/Images/flyby2.pdf)

### Declarative Languages: Parsing 
-  Obtaining the Declarative Program/Query

-  verify it is is syntactically valid

-  creating an abstract syntax tree

![left fit](./attachments/Images/parsingmap.png)

### Declarative Languages: Parsing 

![inline](./attachments/Images/declarative0.pdf)

### Declarative Languages: Planning (Logical)

-  Obtaining the AST of the program/query
-  verify all the preconditions hold
-  apply optimizations
-  errors: statistics not updated, wrong decision
-  generates logical plan.

![left fit](./attachments/Images/logicalmap.png)

### Declarative Languages: Planning (Logical)

![inline](./attachments/Images/declarative1.pdf)

### Declarative Languages: Planning (Physical) 
-  Obtaining the logical plan of the program/query
-  verify all the preconditions
-  errors: table not exists
-  generates physical plan

![left fit](./attachments/Images/physicalmap.png)

### Declarative Languages: Planning (Physical) 

![inline](./attachments/Images/declarative2.pdf)

### Example of Physical Plan Optimization

![inline](./attachments/Images/physicalplanex.png)

### Declarative Languages: Executing 
-  obtain physical plan of the query
-  load it for execution
-  run!

![right fit](./attachments/Images/runexec.png)

### Declarative Languages: Errors 

-  Input not compliant to the expected one
-  table dropped while long-running
-  network fail (fixable)
-  node fail (fixable)

![left fit](./attachments/Images/errors.png)


### Extras
- [[Functional Programming]]
