FROM eclipse-temurin:21-jdk
VOLUME /tmp
ARG JAR_FILE=build/libs/spring-boot-kotlin-cloud-run-0.0.1-SNAPSHOT-plain.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]