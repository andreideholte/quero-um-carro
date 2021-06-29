FROM openjdk:8-jdk-slim

RUN mkdir -p /opt/quc/config

ADD src/main/resources/application.properties /opt/quc/config

ADD target/quc.jar /opt/quc

EXPOSE 8000

WORKDIR /opt/quc

CMD ./quc.jar  -Xmx512m
