footer:  [Riccardo Tommasini](http://rictomm.me) - riccardo.tommasini@ut.ee - @rictomm 
slide-dividers: #, ##, ###
slidenumbers: true
autoscale: true
theme: Plain Jane

# Data Engineering: Topic
#### LTAT.02.007
#### Ass Prof. Riccardo Tommasini
#### Assistants: [Fabiano Spiga](mailto:),  [Mohamed Ragab](mailto:mohamed.ragab@ut.ee),  [Hassan Eldeeb](mailto:hassan.eldeeb@ut.ee)
- [https://courses.cs.ut.ee/2020/dataeng](https://courses.cs.ut.ee/2020/dataeng)
- [Forum](https://piazza.com/ut.ee/fall2020/ltat02007/home) 
- [Moodle](https://moodle.ut.ee/course/view.php?id=10457)


# Key-Value Stores

### Why Key-value Store?

(Business) Key -> Value

(twitter.com) tweet id -> information about tweet

(kayak.com) Flight number -> information about flight

(yourbank.com) Account number -> information about it

(amazon.com) item number -> information about it

Search by ID is usually built on top of a key-value store

### Isn’t that just a database?

- Queried using SQL
- Key-based
- Foreign keys
- Indexes 
- Joins

```sql 
SELECT user_id from users WHERE 
	username = “jbellis”
```

![right fit](./attachments/reldbex.png)

