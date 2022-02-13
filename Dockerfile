FROM openjdk:8
COPY ./target/drone-service-0.0.1-SNAPSHOT.jar  drone-service-0.0.1-SNAPSHOT.jar 
CMD ["java","-jar","drone-service-0.0.1-SNAPSHOT"]