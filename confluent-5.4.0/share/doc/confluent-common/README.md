Confluent Commons
=================

Contains 3 libraries: [metrics](metrics), [config](config), [utils](utils).


# Development
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fconfluentinc%2Fcommon.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fconfluentinc%2Fcommon?ref=badge_shield)


## Overview

Typically you will run standard maven commands such as `mvn install` from the top-level directory (i.e. the directory
that contains this `README` file).  The sections below explain how to build specific sub-projects independently.


## Metrics

To build the `metrics` sub-project independently, run this from the parent directory:

    $ mvn -pl :common-metrics package


## Config

To build the `config` sub-project independently, run this from the parent directory:

    $ mvn -pl :common-config package


## Utils

To build the `utils` sub-project independently, run this from the parent directory:

    $ mvn -pl :common-utils package


## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fconfluentinc%2Fcommon.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Fconfluentinc%2Fcommon?ref=badge_large)