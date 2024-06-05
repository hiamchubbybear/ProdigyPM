FROM ubuntu:latest AS build 
RUN apt-get update 
RUN apt-get install openjdk-17-jdk -y 
COPY . .
ENTRYPOINT [ "java" , "-jar" , "target/Employer-0.0.1-SNAPSHOT.war" ]