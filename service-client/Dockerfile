FROM openjdk:17-alpine

WORKDIR /opt/server

COPY ./target/service-client-0.0.1-SNAPSHOT.jar clientService.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar","clientService.jar"]