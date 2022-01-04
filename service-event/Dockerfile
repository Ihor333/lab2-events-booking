FROM openjdk:17-alpine

WORKDIR /opt/server

COPY ./target/service-event-0.0.1-SNAPSHOT.jar eventService.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar","eventService.jar"]