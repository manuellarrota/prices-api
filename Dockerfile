FROM openjdk:8-jdk-alpine
MAINTAINER manuel.larrota@gmail.com
COPY ./target/prices-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]

