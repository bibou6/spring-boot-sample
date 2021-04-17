#Build


FROM maven:3.6.3-jdk-11-slim AS build

ENV SERVICE_ENGINE spring-boot-sample-engine
ENV SERVICE_MODEL spring-boot-sample-model

RUN mkdir -p /workspace/${SERVICE_ENGINE}
RUN mkdir -p /workspace/${SERVICE_MODEL}
WORKDIR /workspace

COPY pom.xml /workspace
COPY ${SERVICE_ENGINE}/pom.xml /workspace/${SERVICE_ENGINE}
COPY ${SERVICE_MODEL}/pom.xml /workspace/${SERVICE_MODEL}

COPY src /workspace/src
COPY ${SERVICE_ENGINE}/src /workspace/${SERVICE_ENGINE}/src
COPY ${SERVICE_MODEL}/src /workspace/${SERVICE_MODEL}/src

RUN mvn -B -f pom.xml clean package -DskipTests 



FROM tomcat:8.0.51-jre8-alpine

MAINTAINER "Adrien Durot"

RUN rm -rf /usr/local/tomcat/webapps/*

ENV SERVICE_NAME spring-boot-sample-engine

COPY --from=build /workspace/${SERVICE_NAME}/target/${SERVICE_NAME}*.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh","run"]

EXPOSE 8080