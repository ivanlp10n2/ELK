## ELK (in progress)

PoC for **_centralized logging_** using Filebeat + Logstash + Elastic Search + Kibana 

## Building idea
This centralized logging will run in Docker containers with docker-compose. 
<br>
_Filebeat_ will consume stdout logs from docker (/var/lib/docker/containers/), ship to _logstash_, store in _elastic_ and visualize in _kibana_.

- app: logging api.
- logging: customizations for logging.
- filebeat: shipping logs from stdout to logstash
- logstash: consuming logs, filter and save with indexes.
- elasticsearch: store documents.
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

## API
All the endpoints are HTTP secured with basic auth. Credentials are hardcoded (application.properties). It can be implemented with env variables if needed. 
```
username: root
password: root 
```

### Endpoints:
##### Starts to log continuously the message provided every second defined
```
POST /logger/start
Payload : { 
    "seconds": int, 
    "message" : string 
}  
```

##### Stops all the loggers started in the application
```
POST /logger/stopAll
```

##### Shutdown procedure built-in with actuators
```
POST /actuator/shutdown
```

## Elastic stack configurations
Credentials
```
username: elastic
password: root
```

####Endpoints
**Kibana**
```
http://localhost:5601
```

You can filter in messages to see the logging applications
![Image of kibana](https://i.imgur.com/3TRqMNy.png)