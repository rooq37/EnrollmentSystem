FROM openjdk:8
RUN mkdir -p /backend
WORKDIR /backend
COPY enrollment-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "-Dspring.config.location=/properties/application.properites", "/backend/backend.jar"] 