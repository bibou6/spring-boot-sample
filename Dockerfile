FROM tomcat:8.0.51-jre8-alpine

MAINTAINER "Adrien Durot"

RUN rm -rf /usr/local/tomcat/webapps/*

ENV SERVICE_NAME spring-boot-sample-engine

COPY ${SERVICE_NAME}/target/${SERVICE_NAME}-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh","run"]

EXPOSE 8080