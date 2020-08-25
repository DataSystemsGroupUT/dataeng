footer:  Curtesy of Marco Brambilla (polimi)
slidenumbers: true
<!-- : #course, #topic, #LTAT.02.007 -->

[Reading](http://cidrdb.org/cidr2015/Papers/CIDR15_Paper2.pdf)



# Data Engineering
## Data Wrangling
#### LTAT.02.007
#### Ass Prof. Riccardo Tommasini
#### Assistants: Mohamed Ragab, Samuele Langhi, Hassan Elsaeeb
#### Curtesy of Marco Brambilla

- [https://courses.cs.ut.ee/2020/dataeng](https://courses.cs.ut.ee/2020/dataeng)
- [Forum]() 

---

### Conventional Definition of Data Quality

* __Accuracy__
  * The data was recorded correctly
* __Completeness__
  * All relevant data was recorded
* __Uniqueness__
  * Entities are recorded once
* __Timeliness__
  * The data is kept up to date(and time consistency is granted(
* __Consistency__
  * The data agrees with itself

### Problems …

* Unmeasurable
  * Accuracy and completeness are extremely difficult, perhaps impossible to measure
* Context independent
  * No accounting for what is important  Eg, if you are computing aggregates, you can tolerate a lot of inaccuracy
* Incomplete
  * What about interpretability, accessibility, metadata, analysis, etc
* Vague
  * The conventional definitions provide no guidance towards practical improvements of the data

--- 

![inline](./attachments/17-dataWrangling4.png)

---

# Isn’t data science sexy?

---
### 1. When Data Is Wrong

### The skeptic approach

![inline](./attachments/SkepticalDW.png)

### The pragmatic approach

![[Pasted image.png]]

### The (pseudo) practioner approach
![inline](./attachments/pseudo-practitioner.png)

### Goal: Better Faster Cheaper!

![inline 150%](./attachments/Better Faster Cheaper.png)

### The Vicious Cycle of Bad Data

![inline](./attachments/data quality issues.png)

### Data Quality Issue

Gartner Report

By 2017, 33% of the largest global companies will experience an information crisis due to their inability to adequately value, govern and trust their enterprise information.

> If you torture the data long enough, it will confess to anything
> – Darrell Huff
																			
---

# Making a Wrong Right

---
### Data Wrangling is …

The process of transforming “raw” data into data that can be analyzed to generate valid actionable insights

Data scientists spend more timeon preparing data than on analyzing it.

### Data Wrangling  a.k.a.

Data Preprocessing
Data Preparation
Data Cleansing
Data Scrubbing
Data Munging
Data Fold, Spindle, Mutilate…
(good old ETL)



### Data Wrangling Steps

- Iterative process
- Understand
- Explore
- Transform
- Augment
- Visualize

### What is Data Cleansing?

__Data cleansing__ or __data scrubbing__ is the act of __detecting and correcting (or removing corrupt or inaccurate records__) from a data set

The term refers to identifying incomplete, incorrect, inaccurate, partial or irrelevant parts of the data and then replacing, modifying, filling in or deleting this dirty data

### Why is Data “Dirty” ?

Dummy Values,
Absence of Data,
Multipurpose Fields,
Cryptic Data,
Contradicting Data,
Shared Field Usage,
Inappropriate Use of Fields,
Violation of Business Rules,
Reused Primary Keys,
Non-Unique Identifiers,
Data Integration Problems

### Data Cleansing in Practice

Parsing

Correcting

Standardizing

Matching

Consolidating

### Parsing

Parsing locates and identifies individual data elements in the source files and then isolates these data elements in the target files

![[parsing.png]]

### Correcting

Corrects parsed individual data components using sophisticated data algorithms and secondary data sources

### Standardizing

Standardizing applies conversion routines to __transform data into its preferred (and consistent( format__ using both standard and custom business rules, as well as coherent measurement units,…

### Matching

Searching and __matching records__ within and across the parsed, corrected and standardized data based on predefined business rules to __eliminate duplications__ 

### Match Patterns

__Customer__

__\#__  __/__  __Tax ID__

### Matching

### Consolidating

Analyzing and __identifying relationships__ between matched records and consolidating/merging them into ONE representation

### Understanding Data: PDF

![inline](./attachments/17-dataWrangling12.png)

---

![inline](./attachments/17-dataWrangling13.png)

---

![inline](./attachments/17-dataWrangling14.png)

### Understanding Data: Free Text

![inline](./attachments/17-dataWrangling15.png)

Python \+Textract\+Tesseract

![inline](./attachments/17-dataWrangling16.png)

![inline](./attachments/17-dataWrangling17.png)

[ ] TODO

### Understanding Data: more

<span style="color:#008000">“Looks like my V8 Chevy is running low on fuel Didn’t I fill up just the day before?”</span>

![inline](./attachments/17-dataWrangling18.png)

![inline](./attachments/17-dataWrangling19.png)

![inline](./attachments/17-dataWrangling20.png)

Decode the following secret message:

$DALDFWSFOEWRBOSDCALAXORDJFKMCO$

↓

$DAL DFW SFO EWR BOSDCA LAX ORD JFK MCO$

### Data Munging

Potentiallylossytransformations applied to a piece of data or a file

Vague data transformation steps that are not yet completely clear

Eg, removing punctuation or html tags, data parsing, filtering, and transformation

---
# Semantics

---
### Semantics and Outliers

![inline](./attachments/17-dataWrangling21.png)

The value stands inthe abnormal

### Missing Data: Detection

__Overtly missing data__

Match data specifications against data \- are all the attributes present?

Scan individual records \- are there gaps?

Rough checks : number of files, file sizes, number of records, number of duplicates

Compare estimates (averages, frequencies, medians( with “expected” values and bounds; check at various levels of granularity since aggregates can be misleading

### Missing data: Detection (cont.)

__Hidden damage to data__

Values are truncated or censored \- check for spikes and dips in distributions and histograms

Missing values and defaults are indistinguishable \- too many missing values? metadata or domain expertise can help

Errors of omission eg all calls from a particular area are missing \- check if data are missing randomly or are localized in some way

### Missing Values: Random

System failures

Complete miss

![inline](./attachments/17-dataWrangling22.png)

### Missing Values: Wrong Ingestion

CSV to table / excel

Merged fields

Missing fields

![inline](./attachments/17-dataWrangling23.png)

Missing due to invalid data and ingestion

![inline](./attachments/17-dataWrangling24.png)

### Missing Values: Inapplicability

Partial data by nature

Remember to leave empty slots

### Imputing Values to Missing Data

In federated data, between 30%\-70% of the data points will have at least one missing attribute \- data wastage if we ignore all records with a missing value

Remaining data is seriously biased

Lack of confidence in results

Understanding pattern of missing data unearths data integrity issues

### Missing Value Imputation - 1

* Standalone imputation
  * Mean, median, other point estimates
  * Assume: Distribution of the missing values is the same as the non\-missing values
  * Does not take into account inter\-relationships
  * Introduces bias
  * Convenient, easy to implement

* Better imputation \-  use attribute relationships
* Assume : all prior attributes are populated
  * That is, _monotonicity_ in missing values
  * _X1| X2| X3| X4| X5_
  * 10| 20| 35|   4| 
  * 11| 18| 40|   2| 
  * 19| 22| 22|    | 
  * 09| 15|     |    | 
* Two techniques
  * Regression (parametric(,
  * Propensity score (nonparametric(

### Missing Value Imputation –3

* Regression method
  * Use linear regression, sweep left\-to\-right
  * X3=a\+b\*X2\+c\*X1;
  * X4=d\+e\*X3\+f\*X2\+g\*X1,  and so on
  * X3 in the second equation is estimated from the first equation if it is missing

* Propensity Scores (nonparametric(
  * Let Yj=1 if Xjis missing, 0 otherwise
  * Estimate P(Yj=1( based on X1through X(j\-1(using logistic regression
  * Group by propensity score P(Yj=1(
  * Within each group, estimate missing Xjs from known Xjs using approximate Bayesian bootstrap
  * Repeat until all attributes are populated

### Missing Value Imputation - 4

* Arbitrary missing pattern
  * Markov Chain Monte Carlo (MCMC(
  * Assume data is multivariate Normal,with parameterQ
  * (1( Simulate missing X, givenQestimated from observed X ; (2( Re\-computeQusing filled in X
  * Repeat until stable
  * Expensive: Used most often to induce monotonicity
* __Note that imputed values are useful in aggregates but can’t be trusted individually__

### Censoring and Truncation

Well studied in Biostatistics, relevant to time dependent data egduration

<span style="color:#0000FF"> _Censored_ </span> \- Measurement is bounded but not precise eg Call duration > 20 are recorded as 20

<span style="color:#0000FF"> _Truncated_ </span> \- Data point dropped if it exceeds or falls below a certain bound eg customers with less than 2 minutes of calling per month

![inline](./attachments/17-dataWrangling25.png)

Censored time intervals

### Censoring/Truncation (cont.)

If censoring/truncation mechanism not known, analysis can be inaccurate and biased

But if you know the mechanism, you can mitigate the bias from the analysis

Metadata should record the existence as well as the nature of censoring/truncation

# 

![inline](./attachments/17-dataWrangling26.png)

Spikes usually indicate censored time intervals

caused by resetting of timestamps to defaults

### Suspicious Data

Consider the data points

3, 4, 7, 4, 8, 3, 9, 5, 7, 6, 92

“92” is suspicious \- an <span style="color:#0000FF"> _outlier_ </span>

Outliers are potentially legitimate

Often, they are data or model glitches

Or, they could be a data miner’s dream, eg highly profitable customers

### Outliers

* Outlier – “departure from the expected”
* Types of outliers – defining “expected”
* Many approaches
  * Error bounds, tolerance limits – control charts
  * Model based – regression depth, analysis of residuals
  * Geometric
  * Distributional
  * Time Series outliers

### Control Charts

* Quality control of production lots
* Typically univariate: X\-Bar, R, CUSUM
* Distributional assumptions for charts not based on means eg R–charts
* Main steps (based on statistical inference(
  * Define “expected” and “departure” eg Mean and standard error based on sampling distribution of sample mean (aggregate(;
  * Compute aggregate each sample
  * Plot aggregates vs expected and error bounds
  * “Out of Control” if aggregates fall outside bounds

### An Example

(http://wwwitlnistgov/div898/handbook/mpc/section3/mpc3521htm(

![inline](./attachments/17-dataWrangling27.png)

### Multivariate Control Charts - 1

* Bivariate charts:
  * based on bivariate Normal assumptions
  * component\-wise limits lead to Type I, II errors
* Depth based control charts (nonparametric(:
  * map n\-dimensional data to one dimension using depth egMahalanobis
  * Build control charts for depth
  * Compare against benchmark using depth eg Q\-Q plots of depth of each data set

Bivariate Control Chart

# 

### Multivariate Control Charts - 2

* Multiscale process control with wavelets:
  * Detects abnormalities at multiple scales as large wavelet coefficients
  * Useful for data with heteroscedasticity
  * Applied in chemical process control

### Model Fitting and Outliers

* Models summarize general trends in data
  * more complex than simple aggregates
  * eg linear regression, logistic regression focus on attribute relationships
* Data points that do not conform to well fitting models are _potential outliers_
* Goodness of fit tests (DQ for analysis/mining(
  * check suitableness of model to data
  * verify validity of assumptions
  * data rich enough to answer analysis/business question?

### Set Comparison and Outlier Detection

“Model” consists of partition based summaries

Perform nonparametric statistical tests for a rapid section\-wise comparison of two or more massive data sets

If there exists a baseline “good’’ data set, this technique can detect potentially corrupt sections in the test data set

### Types of data

* Categorical
* Qualitative
  * Subjective
* Quantitative
  * Discrete
  * Continuous

![inline](./attachments/17-dataWrangling28.png)

Color

Nice, Good, For birthday

6 balloons

Pressure 15 PSI,

139 m over sea level

* Categorical
* Qualitative
  * Subjective
* Quantitative
  * Discrete
  * Continuous

![inline](./attachments/17-dataWrangling29.png)

### Data Source Selection Criteria

Credibility

Completeness

Accurateness

Verifiability

Currency

Accessibility

Compliance

Cost

Legal issues

Security

Storage

Provenance

### Not all tables are created equal

Find total comedy movies in  all of 2014? \-> Not easy in current form

Find % of hit comedy movies in a 2015?

Very easy to add a new column

Very messy data

Variables in both rows and columns

Each row is complete

observation

Normalize to avoid duplication

Multiple Tables

Divided by Time

Combine all tables

accommodating

varying formats

### The “Key” (matching) problem

Keys are crucial in DB

Many DBs \-\-> Many keys

How to align?

Identification to certaindegree of accuracylikely\-identities

eg, same user match

![inline](./attachments/17-dataWrangling30.tiff)

### The “Duplicates” problem

Related to Key problem

Identification to certain degree of accuracy likely\-duplicates

eg, duplicate posts

![inline](./attachments/17-dataWrangling31.tiff)

Related to Key problem

Identification to certain degree ofaccuracy likely\-duplicates

### Lessons Learnt on Tables

(Multiple( variables in columns

Never values as columns\!

Shape may depend on convenience of queries

Matching identities and duplications are crucial in data science\!

Each observation is complete and atomic

Each variable belongs to (only\!( one column\!

### Schema-On-Write Vs Schema-On-Read

Traditional DBMSs enforced writing only data consistent with a pre\-designed schema

Today data modeling at design time is a luxury

In schema on read, data is applied to a plan or schema as it is pulled out of a stored location, rather than as it goes in

### Popular Open Source Tools

![inline](./attachments/17-dataWrangling32.png)![inline](./attachments/17-dataWrangling33.png)![inline](./attachments/17-dataWrangling34.png)![inline](./attachments/17-dataWrangling35.png)![inline](./attachments/17-dataWrangling36.png)![inline](./attachments/17-dataWrangling37.png)![inline](./attachments/17-dataWrangling38.png)

### Other Resources

![inline](./attachments/17-dataWrangling39.png)![inline](./attachments/17-dataWrangling40.png)![inline](./attachments/17-dataWrangling41.png)![inline](./attachments/17-dataWrangling42.png)

![inline](./attachments/17-dataWrangling43.png)![inline](./attachments/17-dataWrangling44.png)![inline](./attachments/17-dataWrangling45.png)![inline](./attachments/17-dataWrangling46.png)

### Commercial Vendors

![inline](./attachments/17-dataWrangling47.png)![inline](./attachments/17-dataWrangling48.png)![inline](./attachments/17-dataWrangling49.png)![inline](./attachments/17-dataWrangling50.png)

![inline](./attachments/17-dataWrangling51.png)![inline](./attachments/17-dataWrangling52.png)![inline](./attachments/17-dataWrangling53.png)![inline](./attachments/17-dataWrangling54.png)

### Trifacta Wrangler

![inline](./attachments/17-dataWrangling55.png)

### Google’s Open Refine

![inline](./attachments/17-dataWrangling56.png)

### Hands on Data Wrangling

* __Data Ingestion__
  * CSV
  * PDF
  * API/JSON
  * HTML Web Scraping
  * XLS, Access,…\!
* __Data Exploration__
  * Visual inspection
  * Graphing
* __Data Shaping__
  * Tidying Data

* __Data Cleansing__
  * Missing values
  * Format
  * Measurement Units
  * Outliers
  * Data Errors Per Domain
  * Fat Fingered Data
* __Data Augmenting__
  * Aggregate data sources
  * Fuzzy/Exact match

![inline](./attachments/17-dataWrangling57.png)

### R Libraries for Data Wrangling

  * stringr
  * dplyr
  * tidyr
  * readxl,xlsx
  * lubridate
  * gtools
  * plyr
  * rvest

### References – Web and Books

Web:

www2gbiforg/DataCleaningpdf

wwwwebopediacom/TERM/D/data\_cleansinghtml

Books:

Data Mining by Ian H Witten and Eibe Frank

Exploratory Data Mining and Data Quality by  Dasu and Johnson  (Wiley, 2004(

### References - Tools

Stanford Wranglerhttp://visstanfordedu/papers/wranglerhttp://visstanfordedu/wrangler

http://openrefineorg/

http://okfnlabsorg/

http://schoolofdataorg/

![inline](./attachments/17-dataWrangling58.tiff)

![inline](./attachments/17-dataWrangling59.tiff)

# Marco Brambilla,       @marcobrambi, marco.brambilla@polimi.it
http://datascience.deib.polimi.it

![inline](./attachments/17-dataWrangling60.png)

