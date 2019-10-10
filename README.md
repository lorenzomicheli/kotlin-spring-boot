# Getting Started

The purpose of this pet project is to assess the integration between Kotlin and Spring Boot 2.

This simple service, reads a name of a person from a kafka topic and persist it on a relational database. 
It expose a REST endpoint to list all people saved in the database. 

System and application metrics are exposed and ready to be parsed by prometheus. It also shows how to introduce custom metrics.

Logs are printed in JSON format when it's run with the spring profile "prod"

### Build and Run 

To run the build and tests execute:

`./gradlew build`

To run the application:

`docker-compose up --build`

### Enpoints

The following HTTP endpoints are exposed:

* http://localhost:8080/people - list of people registered 
* http://localhost:8080/people/1 - access a specific person
* http://localhost:8080/actuator/health - health endpoint
* http://localhost:8080/actuator/prometheus - Prometheus metrics

### Play Around

The application listens to the `people` topic on the Kafka cluster started with docker-compose. The cluster is accessible also outside Docker network at localhost:9093.

To publish a new value in the kafka topic 

`echo "Lorenzo" | kafkacat -b localhost:9093 -t people`
