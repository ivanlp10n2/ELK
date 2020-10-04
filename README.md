# ELK (in progress)

This project is about building a PoC **_centralized logging_** using Logstash + Elastic Search + Kibana 

It will be built using two images of the same application -> demo-one



## Run
from root project: ./gradle bootRun

## REST endpoints

###### GET /engine/start
It starts the logging service with the message "test" each two seconds.

###### GET /engine/stop
It stops the logging service (stops all the threads started with logging service)
