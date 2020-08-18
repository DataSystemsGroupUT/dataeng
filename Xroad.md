# X-Road
	
	
- [X-Road: Use Case Model for Member Communication](https://github.com/nordic-institute/X-Road/blob/872aa1da2cfb2313da94d9d945d0ae4a76001fb7/doc/UseCases/uc-mess_x-road_member_communication_use_case_model.md)
- https://github.com/nordic-institute/X-Road/blob/9df06505bf30c9e8d43c571fa5e6d28f38a8dc46/doc/Manuals/ug-ss_x-road_6_security_server_user_guide.md#102-managing-the-timestamping-services
- https://x-road.global/case-study-tax-boards

## Kafkaesque 

- identify what subsets of kafka capabilities are useful for Xroad
- prioritize them according to xroad roadmap
- i am the responsible if the students work on some sensitive data (2 weeks for preparing the agreemnts)
- studnets have to sign an agrement to contribute the xroad code: only the phd student
	- https://github.com/nordic-institute/X-Road-development/#contributor-license-agreement
- send anastsiia the correct number 500*4+1300*4=7200+23% + 20% =10296
- discussing in the hourly rate
 
## Notes
-	 date of the deliverables
-	 frees the delvierables and how they will be delivered
	-	 code via github
	-	 report via shared document
-	 elaborate on this
	-	 The delivery shall be executed by the instrument of delivery and receipt, which specifies the date of delivery, a detailed list of work submitted and/or services provided and/or products delivered and, if needed, any occurring deficiencies. Along with the instrument of transfer and receipt the university shall transfer to the contracting entity the technical and other documentation required for the use and management of the object of the contract on paper and/or an electronic data medium (CD/DVD etc.) and/or by e-mail and/or by storing on a digital platform indicated by the contracting entity or install it in the environment indicated by the contracting entity.
-	 Time table (estimation of work)
--- 
## Annex 1

According to Attachement [^1],  X-Road has been immensely successful helping digital government to evolve.
Nevertheless, to make sure X-Road does not become outdated, the following improvements are necessary:

- Large scale information systems are designed so that they share the same endpoint - even if internally the services have little in common with one another. Ideally, each services available on X-Road should have its own autonomous endpoints to connect to. 
- Implementing massive data analysis is becoming a problem with X-Road, which was not originally intended for massive data requests. Indeed,  while X-Road allows for complex requests from multiple different sources, these requests are synchronous .
- Integration of X-Road APIs is more difficult that alternative private solutions and is often brought out as a negative. This needs addressing, if X-Road is to become a solution for not only domestic, but also cross-border communication

In the era of data analysis, more and more businesses demand such improvements. This project aims at taking the a firs step twoards X-ROAD  next version verifying the feasibility of important improvements like defining extensible and dedicated micrio-services enpoints and  asynchrnous yet trusted communications.

Assuming we start working July 15th for 4 months, with a window of 1 week, the project will divide on 4 sprints of aproximately 3 weeks each plus one week for revision.

### Sprint 0: Knowledge Acquisition 

The first sprint is researved to gain knowledge about X-ROAD (latest version). The relevant X-ROADS capabilities will be listed and the main component described in a document for internal use. 

### Sprint 1: Requirement Analysis 

The main outcome of this sprint is a requirement analysis that will drive the selection of which capabilities to consider for an extension to an event-driven architecture.

Success Metric: Approval
Outcome: Report

### Sprint 2: Architecture

The second sprint will focus on designing an new X-ROAD architecture based on the event-driven paradigm.
Existing use-cases will be used as blue print to asess the backwards compatibility of the design choice.

Success Metric: compliance to the requirement analysis
Outcome: Report

###  Sprint 3: Proof of Concept

The thirs and last sprint will focus at realizing a POC using Apache Kafka.
Assessment of Kafka security capabilities w.r.t. X-ROAD trusted protocols.
Assessment of the performance on a synthetic use case is also in the scope. 
Key Performance indicators will are Throughput/Latency. Additional ones should be discussed.

Success Metric: POC is delivered
Outcome: Report


[^1]: [[Next Generation Digital Government Architecture 1.0.pdf]]