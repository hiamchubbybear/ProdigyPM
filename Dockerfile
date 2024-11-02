# First stage: build the application with OpenJDK 17
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
#  Copy all files from the current directory to the container
RUN mvn clean package  # Build the project, creating the WAR file

# Second stage: create the runtime image with OpenJDK 17
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Employer-0.0.1-SNAPSHOT.war demo.war
# Copy the WAR file

# Use java -jar only if the WAR is executable
ENTRYPOINT ["java", "-jar", "demo.war", "--server.port=19279"]
