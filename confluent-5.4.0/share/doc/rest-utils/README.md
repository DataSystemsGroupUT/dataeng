Confluent REST Utils
====================

Confluent REST Utils provides a small framework and utilities for writing Java
REST APIs using Jersey, Jackson, Jetty, and Hibernate Validator.

See the [`examples/`](examples/) directory for a simple demo service.

Security
--------
The REST `Application` can support both http and https. https is disabled by default. Use the `listeners` configuration
parameter to control which protocol is used.

The https implementation is similar to Kafka, where the server specifies a keystore and trust store. When SSL
client auth is configured, the client must authenticate -- the key in the client's keystore must be trusted by
the server's trust store.

Metrics
-------
One metrics group exists that spans all listeners. Meaning, when using multiple listeners and observing metrics, the
values of each metric are a combination of all listeners. For example, if the maximum latency for a http listener
is 50ms and the maximum latency for a https listener is 100ms, the `request-latency-max` metric will be 100ms, the max
across all listeners.

Contribute
----------

- Source Code: https://github.com/confluentinc/rest-utils
- Issue Tracker: https://github.com/confluentinc/rest-utils/issues

License
-------

The project is licensed under the Apache 2 license.
