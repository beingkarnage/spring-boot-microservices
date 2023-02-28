# spring-boot-microservices
spring boot microservice practice project

## Service details
### USER SERVICE
Responsible for CRUD operations on USER entity
### HOTEL SERVICE
Responsible for CRUD operations on HOTEL entity
### RATING SERVICE
Responsible for CRUD operations on RATING entity
### CONFIG SERVER
Responsible for providing global configs from "https://github.com/beingkarnage/microservice-config"
### SERVICE REGISTRY
Responsible for storing all the services, store their ports, to make easier for routing in-between services
### API GATEWAY
Responsible for providing a single URL for accessing all the registered services.
