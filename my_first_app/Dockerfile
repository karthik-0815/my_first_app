FROM openjdk:20
MAINTAINER Karthik
COPY target/my_first_app-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
COPY main/resources/download1.png image.png
ENTRYPOINT ["java","-jar","/app.jar"]