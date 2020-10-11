# ELK (in progress)

This project is about building a PoC **_centralized logging_** using Logstash + Elastic Search + Kibana 

It will be built using two images of the same application -> demo-one

## Run
from root project: APP_NAME=nameForLogging ./gradlew bootRun

## REST endpoints

#####Starts the logging service with the message "test" each two seconds.
```
POST /engine/start
Payload : { "seconds": int, "message" : string }  
```

#####It stops the logging service (stops all the threads started with logging service)
```
POST /engine/stop
```

#####Shutdown procedure built-in with actuators
```
POST /actuator/shutdown
```
