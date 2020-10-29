FROM gradle:6.7.0-jdk8 AS build
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build