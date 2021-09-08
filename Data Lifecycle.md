
---

![inline](attachments/DataJourney_UoT%20page%203.pdf)

[^dl1]: Curtesy of Herminio Velazquez

---

### A 30000ft view

- Data Collection 
- Data Wrangling
	-  Data Shaping
	-  Data Cleansing
	-  Data Augmenting
- Data Analysis


![](attachments/flying-blind.jpg)

---

![Data Collection](Data%20Collection.md)

---

[[Data Quality]]

---

[[Data Wrangling]]

---

![Data Cleansing](Data%20Cleansing)

---


---

![Data Augmentation](Data%20Augmentation.md)

--- 

![Data Preprocessing](pdfs/Data%20Preprocessing.pdf)

---

![Data Processing](Data%20Processing.md)

---

### Data Analysis

- ~~Statistical ~~
- Machine Learning?
- Query Based


# Data Science vs Data Engineering Pipelines




## Data Analysis Pipeline

![inline](./attachments/pipeline1.png)

## Data Engineering Parts

![inline](./attachments/datapipeline2.png)

## Recall

### Traditional Data Modelling Workflow

- Known as Schema on Write
- Focus on the modelling a schema that can accommodate all needs
- Bad impact on those analysis that were not envisioned

![right fit](./attachments/schemaonread.png)

^
 - Extract Transform Load
 - Some analyses may no longer be performed because the data were lost at writing time,

---
## Schema on Read

- Load data first, ask question later
- All data are kept, the minimal schema need for an analysis is applied when needed
- New analyses can be introduced in any point in time

![right fit](./attachments/schemaonwrite.png)

## Data Lakes (Conceptual View)

![inline](./attachments/datalakewf.png)


## Data Lakes (Physical View)

![inline](./attachments/lakephysical.png)

# So, what’s a logical architecture for a data engineering pipeline?

---

![inline](./attachments/lakelogical.png)

---

## [[Data Collection]]

- It is the process of collecting raw data from various silo databases or files and integrating it into a data lake on the data processing platform, e.g., Hadoop data lake.
- It involves loading data from a variety of sources
- It can involve altering and modification of individual files to fit into a format that optimizes the storage
- For instance, in Big Data small files are concatenated to form files of 100s of MBs and large files are broken down in files of 100s of MB

## [[Data Wrangling]]

The process of creating *reliable*  that can be analyzed to generate valid actionable insights.

The central goal is to make data usable: to put data in a form that can be easily manipulated by analysis tools.

It includes understanding, cleansing, augmenting and shaping data.

^ Additional goals:
- ensure that data is responsive to the intended analyses
- ensure that data contain the necessary information, 
- ensure metadata that describe data are available
- ensure that data are sufficiently correct to support successful modeling and decision-making.

The results is data in the best format (e.g., columnar) for the analysis to perform.

![right 150%](./attachments/wranglingsteps4.png)

##  ~~ETL~~ [[Data Pipelines]]

A data pipeline aggregates, organizes, and moves data to a destination for storage, insights, and analysis. 

Modern data pipeline generalize the notion of ETL (extract, transform, load) to include data ingestion, integration, and movement across any cloud architecture and add additional layers of resiliency against failure.

- [[Apache Airflow]]
- [[Kafka Streams]]
- [[KSQL]]






