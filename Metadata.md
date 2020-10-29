### Metadata

![inline](https://image.slidesharecdn.com/marquez-191112193750/95/data-lineage-with-apache-airflow-using-marquez-7-638.jpg?cb=1573587558)

### ABCs of Data Context[^2]

Data management necessarily entails tracking or controlling who accesses data, what they do with it, where they put it, and how it gets consumed downstream. 

- **Application** context describes how raw bits get interpreted for use.
	- many schemas for the same data
	- with custom code for data integration
- **Behavior** context describes how data was created and used over time
	- spans multiple services, applications and formats and often originates from highvolume sources 
	- upstream and downsteam lineage
- **Change** context describes the version history of data, code and associated information, including changes over time to both structure and content

[^2]:[paper](http://cidrdb.org/cidr2017/papers/p111-hellerstein-cidr17.pdf)

^ 
- Application context ranges from basic data descriptions (encodings, schemas, ontologies, tags), to statistical models and parameters, to user annotations.
