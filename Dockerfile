FROM openjdk:17-jdk-slim

COPY routenet-api/target/*.jar app.jar
COPY routenet-api/src/main/resources/application.properties application.properties

EXPOSE 8080

CMD ["java", "-cp", "app.jar", "com.caravelo.api.CaraveloRouteNetworkApplication"]
