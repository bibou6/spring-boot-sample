FROM tomcat:8.0.51-jre8-alpine

MAINTAINER "Adrien Durot"

RUN rm -rf /usr/local/tomcat/webapps/*

COPY real-estate-engine/target/real-estate-engine-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh","run"]

EXPOSE 8080