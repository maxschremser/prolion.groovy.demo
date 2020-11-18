# Getting Started

## Spring Boot
Start the spring-boot application by executing the following command:
```
./gradlew bootRun
```

It will start the Demo application with the H2 in memory database and scan the given path every 10 seconds with a depth of 8 directories by default.

Configure the FileWalker service:
* __prolion.service.dir__=.         # the directory to be scanned
* __prolion.service.interval__=30s  # the scheduled interval which runs the scan task
* __prolion.service.maxDepth__=3    # the depth to walk into the path

The application properties are defaulted in the application.yaml file of the application.

## Docker
In src/docker you find a docker-compose.yaml file, which composes the postgres database and the spring boot app.
First you need to create the docker image using:
```
./gradlew bootBuildImage
```
From within the directory bring up the containers:
```
docker-compose up -d
``` 

The postgres database uses the data.sql file to create the initial sequence and __file_walker__ table.
In the docker version of the application, the production profile is being used, which uses the postgres database instead of H2.



