FROM openjdk:8-jre-alpine
COPY ./build/libs/metrics-workbench-1.0-SNAPSHOT.jar /app/
WORKDIR /app
EXPOSE 8081
CMD ["java", "-jar", "metrics-workbench-1.0-SNAPSHOT.jar"]