FROM openjdk:8-alpine

#WORKDIR /
#ADD enrollment-0.0.1-SNAPSHOT.jar backend.jar
#ADD application.properties  application.properties
#CMD java -jar backend.jar application.properties

RUN mkdir -p /app
WORKDIR /app
COPY . /app
RUN sh ./mvnw clean install -DskipTests
COPY src/main/resources/application.properties application.properties
ENTRYPOINT ["java", "-jar", "target/enrollment-0.0.1-SNAPSHOT.jar", "application.properties"]
