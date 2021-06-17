# Dockerfile per l'applicazione connessioni

FROM openjdk:11-jdk

ADD build/libs/connessioni.jar connessioni.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "connessioni.jar"]