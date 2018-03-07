FROM openjdk:8
EXPOSE 8081
ADD target/user-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.host=user-db","-Dspring.data.mongodb.port=27017","-jar","/app.jar"]
