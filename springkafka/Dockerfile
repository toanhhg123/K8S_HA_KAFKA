FROM eclipse-temurin:23-jre-ubi9-minimal
EXPOSE 8080
ARG JAR_FILE=target/springkafka-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]