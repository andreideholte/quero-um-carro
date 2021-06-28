FROM openjdk:8-jdk-slim

RUN mkdir -p /opt/quc/config

ADD application.properties /opt/quc/config

ADD playout-api-1.0.jar /opt/quc

EXPOSE 8000

WORKDIR /opt/quc

CMD ./quc-api-1.0.jar  -Xmx512m
