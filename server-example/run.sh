#!/bin/bash
java -jar -Dapplication.info.hostUrl=localhost:$1 target/server-example-1.0-SNAPSHOT.jar
