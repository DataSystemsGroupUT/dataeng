### Event Sourcing[^51]

> The fundamental idea of Event Sourcing is ensuring that every change to the state of an application is captured in an event object, 

> Event objects are immutable and stored in the sequence they were applied for the same lifetime as the application state itself.

[^51]: Martin Fowler, [link](https://martinfowler.com/eaaDev/EventSourcing.html)

### Events

Events are both a fact and a notification. 

They represent something that happened in the real world but include no expectation of any future action. 

They travel in only one direction and expect no response (sometimes called “fire and forget”), but one may be “synthesized” from a subsequent event.
