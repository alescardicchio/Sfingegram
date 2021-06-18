#!/bin/bash

echo Building Sfingegram application...

gradle clean build &&
docker-compose up -d
