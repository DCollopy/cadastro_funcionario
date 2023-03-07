FROM openjdk:18-alpine

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} web-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/web-0.0.1-SNAPSHOT.jar"]
