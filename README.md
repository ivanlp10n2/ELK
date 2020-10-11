## ELK (in progress)

PoC for **_centralized logging_** using Logstash + Elastic Search + Kibana 

It will be built using two images of the same application -> demo-one

## Building idea
This centralized logging will run in Docker containers with docker-compose

- app: logging api.
- logging: customizations for logging.
- logstash: engine for log consuming and storing in nosql database.
- elastic-search: save files created by logstash and gathered by kibana.
- kibana: dashboard with filter options.

## Run
from **app** project:
- build application : ./gradlew build
- build docker image: docker build -t logging-app:1.0 .
- build and run docker container. Expose port 8080 and use logging prefix: docker run --name -p 8080:8080 --env LOG_PREFIX=app logging-app:1.0 

``` 
All in one command: 
./gradlew build && docker build -t logging-app:1.0 . && docker run -p 8080:8080 --env LOG_PREFIX=app logging-app:1.0
```
#### docker-compose
from **root** project:
- build application : ./app/gradlew build
- build docker image: docker build -t logging-app:1.0 app/.
- run docker-compose for binding two running instances on port 8080 and 8082: docker-compose up
``` 
All in one command: 
./app/gradlew build && docker build -t logging-app:1.0 app/. && docker-compose up
```

## REST endpoints

##### Starts the logging service with the message "test" each two seconds.
```
POST /engine/start
Payload : { "seconds": int, "message" : string }  
```

##### It stops the logging service (stops all the threads started with logging service)
```
POST /engine/stop
```

##### Shutdown procedure built-in with actuators
```
POST /actuator/shutdown
```
