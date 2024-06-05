FROM ubuntu:latest AS build 
RUN apt-get update 
RUN apt-get install openjdk-17-jdk -y 
COPY . .
# RUN mvn clean build
FROM openjdk:17.0.1-jdk-slim
COPY --from=build  /target/Employer-0.0.1-SNAPSHOT.war demo.war
EXPOSE 8080 
ENTRYPOINT [ "java" , "-jar" , "demo.war" ]