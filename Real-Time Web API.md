# Real-Time Web API

[Websocket](https://levelup.gitconnected.com/websockets-lesser-known-pattern-in-data-engineering-200329e90331)

## The Problem with HTTP

-  The communication is only in one direction at any point. The request comes from the client; the API processes it, responds and that’s the end of the communication.
-  The client continuously polls the API to get the most recent data. Not only it wastes bandwidth, but it also adds latency to the process

---

> We want to let the server push some data to the client over http (or https) without the client explicitly requesting it.

### Options

- HTTP long poll: keep the connection between the client and API open for a long time, allowing the client to get the update in realtime. 
- Need of a standardized process to enable full duplex data transfer over the http protocol
	- WebSockets is a protocol, allowing full duplex data transfer.
	- Server-Sent Events

HTTP allows you to upgrade the requested connection to something else. There are only a handful of upgrades allowed in HTTP 1.1, which are h2c, HTTPS/1.3, IRC/6.9, RTA/x11, websocket. Upgrades allow the request to come as a normal http one but then upgraded to something else in those list of protocols.