FROM docker.io/maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy dependecies files
COPY libs/ ./libs
COPY pom.xml .

COPY src ./src

RUN mvn clean package  -DskipTests

FROM eclipse-temurin:21-jre-alpine AS runner

WORKDIR /app

ARG JAR_FILE=./app/target/*.jar

COPY --from=builder ${JAR_FILE} points-service.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","points-service.jar"]