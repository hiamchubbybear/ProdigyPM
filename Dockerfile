# FROM ubuntu:latest AS build 
# RUN apt-get update 
# RUN apt-get install openjdk-17-jdk -y 
# COPY . .
# Base Image
# FROM ubuntu:latest

#Update APT repository & Install gnupg
# RUN apt-get update && apt-get install -y gnupg

# #Add an account for running MySQL
# RUN groupadd -r mysql && useradd -r -g mysql mysql

# #Add the MySQL APT repository & Install necessary programs
# RUN apt-get update \
#     && echo "deb http://repo.mysql.com/apt/ubuntu/ bionic mysql-8.3.0" > \
#       /etc/apt/sources.list.d/mysql.list \
#     && apt-key adv --keyserver pgp.mit.edu --recv-keys 5072E1F5 \
#     && apt-get update \
#     && apt-get install -y --no-install-recommends perl pwgen

# #Install MySQL
# RUN { \
#     #Set MySQL root password for silent installation
#     echo mysql-community-server mysql-community-server/root-pass password '160304'; \
#     echo mysql-community-server mysql-community-server/re-root-poss password '160304'> \
#     } | debconf-set-selections \
#     && apt-get install -y mysql-server \
#     && mkdir -p /var/lib/mysql /var/run/mysqld \
#     && chown -R mysql:mysql /var/lib/mysql /var/run/mysqld \
#     && chmod 777 /var/run/mysqld

#Solve the problem that ubuntu cannot log in from another container
# RUN sed -i 's/bind-address/#bind-address/' /etc/mysql/mysql.conf.d/mysqld.cnf

#Mount Data Volume
# VOLUME /var/lib/mysql

#Expose the default port
# EXPOSE 3306

#Start MySQL
# CMD ["mysqld","--user","mysql"]
# RUN mvn clean build
# FROM openjdk:17.0.1-jdk
# COPY --from=build  /target/Employer-0.0.1-SNAPSHOT.war demo.war
# EXPOSE 8080 
# ENTRYPOINT [ "java" , "-jar" , "demo.war" ]





# =========================
# First stage: build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Second stage: create the runtime image
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Employer-0.0.1-SNAPSHOT.war demo.war
EXPOSE 19279
ENTRYPOINT ["java", "-jar", "demo.war"]
