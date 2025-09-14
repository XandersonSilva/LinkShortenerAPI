FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

WORKDIR /app

COPY pom.xml .

RUN apt-get install maven -y

RUN mvn dependency:go-offline

COPY src ./src

COPY . .


RUN mvn clean install -DskipTests

FROM openjdk:21-jdk-slim


WORKDIR /app


COPY --from=build /app/target/linkShortener-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]
