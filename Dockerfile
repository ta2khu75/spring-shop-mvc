FROM maven:3.9.4-eclipse-temurin-21 AS build
COPY ...
RUN mvn clean package -DskipTests
FROM openjdk:21-jdk
COPY --from=build /target/java5assignment-0.0.1-SNAPSHOT.jar java5assignment.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","java5assignment.jar"]