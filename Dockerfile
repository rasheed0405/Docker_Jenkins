FROM openjdk:17
EXPOSE 9090
COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]