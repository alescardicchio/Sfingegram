#!/bin/bash

echo Building Sfingegram application...

./gradlew clean build &&
docker-compose up -d --scale enigmi=2 --scale enigmi-seguiti=3 --scale connessioni=2
