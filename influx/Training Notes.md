## Ideas

- trainign migration from 1.0 to 2.0
- material that justifies why enterprise plan
- neo4j and flux (from and to)

## Questions
- **Question**: If I monitor a sensor data on delta change, does it become an event rather than a metric? (Solomon Fekadu).
-  **Question**: What do people mean by cardinality [cardinality](https://docs.influxdata.com/influxdb/v1.8/concepts/glossary/#series-cardinality?).
- **Question**: Sometimes we need a data to be both field & tag. 
    -  Since we need to group these data but at the same time we wanna do some aggregation functions with them?
    -  What's the better approach to accomplish this?
- **Question**: Is there a rule of thumb on how many time series data to dump in a bucket?
	- Do not exceeed variety on the bucket.
	- Do we have any benchmark.
- **Question**: Can we have cardinality discussion later maybe in some break  (some was there yesterday ) may be more examples from you experts would be great  for everyone to design a measurement.
- **Question**: Regarding the “bucket”, what is the best practice, meaning do you have to see how big a physical “bucket” should be?