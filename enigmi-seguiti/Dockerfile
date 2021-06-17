# Dockerfile per l'applicazione enigmi-seguiti

FROM openjdk:11-jdk

ADD build/libs/enigmi-seguiti.jar enigmi-seguiti.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "enigmi-seguiti.jar"]