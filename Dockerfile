FROM --platform=linux/amd64 openjdk:21-jdk-slim
ADD target/livescore-0.0.1-SNAPSHOT.jar livescore-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","livescore-0.0.1-SNAPSHOT.jar"]