# Dockerfile per l'applicazione enigmi

FROM openjdk:11-jdk

ADD build/libs/enigmi.jar enigmi.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "enigmi.jar"]