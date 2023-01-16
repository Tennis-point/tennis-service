
FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/tenis-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]