# confluent-hub-client
It's a client for interacting with Confluent Hub.
## Building and running
### Build
`mvn clean package`
### Running from IDE
Add `-Dbin.abs.path=-Dbin.abs.path=<project-directory>/target/confluent-hub-client-5.0.0-SNAPSHOT-package/bin/` to VM options.
#### Show help for the whole CLI
`bin/confluent-hub help`
#### Show help for the `install` command
`bin/confluent-hub help install`
#### Install the latest version of a component from Confluent Hub
```
bin/confluent-hub install microsoft/kafka-connect-iot-hub:latest
```

## Dependencies
* `/bin/bash`
* `grep`
* `ps`

## Synopsis
```
confluent-hub install [ --component-dir <componentDir> ] [ --dry-run ]
        [ --no-prompt ] [ --verbose ] [ --worker-configs <workerConfigs> ] [--]
        <id>
```

## Autopiloted vs assisted mode
`--no-prompt` option turns on autopiloted mode with no input from user needed. 
Otherwise script runs in assisted mode, and it prompts user if/when more information is needed.
