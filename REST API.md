# REST API

![inline](https://www.seobility.net/en/wiki/images/f/f1/Rest-API.png)

## World Wide Web

International ecosystem of applications and services that allows us to search, aggregate, combine, transform, replicate, cache, and archive the information that underpins society. 

The Web is the result of millions of simple, small-scale interactions between agents and resources that use the founding technologies of HTTP and URI. [^121]

The Web is a set of widely commoditised servers, proxies, caches, and content delivery networks [an engineers]

[^121]: [Architecture of the World Wide Web, Volume One](https://www.w3.org/TR/webarch/)

### Resources

Resources are the fundamental building blocks

Anything we can expose, i.e., documents, images, videos, audio, devices, people, things…

We can represent them by abstracting the useful information and identifying using a Uniform Resource Identifier (URI)

### URIs

URL format (RFC 2396):
scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]


![inline](https://cdn-media-1.freecodecamp.org/images/oiGXAA5yQ5fSF8TySDue6-AM7PJrMeLU7Y4p)

E.G.:
git@github.com:nodejs/node.git
mongodb://root:pass@localhost:27017/TestDB?options?replicaSet=test
http://example.com

### HTTP

[.column]
- GET 
	- Uniform Interface 
	- read-only operation 
	- idempotent

- POST 
	- like a resource upload 
	- idempotent
- DELETE 
	- remove resources 
	- idempotent
	
[.column]
- HEAD 
	- HEAD is like GET except it returns only a response code 
- PUT 
	the only non-idempotent and unsafe operation is allowed to modify the service in a unique way 
- OPTIONS is used to request information about the communication options of the resource

### Representation

Access to a resource is mediated by a representation

This separation is convenient to promote loose-coupling between server (producers) and	 client (consumers)

Multiple Views and Content Negotiation are the basis for interoperability

### Web API 

> An API that uses the HTTP protocol. Non standard/unpredictable URL format.

E.g., ```POST http://example.com/getUserData.do?user=1234```

## Representational state transfer (REST) 

- A set of constraints that inform an architecture
	- Resource Identification
	- Uniform Interface
	- Self-Describing Messages
	- Stateless Interactions	
- Claims: scalability, mashup-ability, usability, accessibility


### RESTful API

> Resource based WebAPI. Standard/predictable URL format.

E.g., ```GET http://example.com/user/1234```

|     VERB      |     Collection                 |     Item                           |
|---------------|--------------------------------|------------------------------------|
|     POST      |     Create   a new item.       |     Not used                       |
|     GET       |     Get   list of elements.    |     Get the selected item.         |
|     PUT       |     Not used                   |     Update the selected item.      |
|     DELETE    |     Not used                   |     Delete   the selected item.    |

### Design Space

The RESTful API uses the available HTTP verbs to perform CRUD operations based on the “context”:
Collection: A set of items (e.g.: /users)
Item: A specific item in a collection (e.g.: /users/{id})

![right 95%](./attachments/restdesign.png)
