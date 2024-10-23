FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Second stage: create the runtime image
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Employer-0.0.1-SNAPSHOT.war demo.war
ENTRYPOINT ["java", "-jar", "demo.war", "--server.port=19279"]
