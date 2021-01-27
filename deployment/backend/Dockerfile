FROM openjdk:8
WORKDIR ./deployment/backend/
ADD ./backend/target/enrollment-0.0.1-SNAPSHOT.jar backend.jar
ADD ./deployment/backend/application.properties  application.properties
CMD java -jar backend.jar application.properties