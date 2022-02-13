FROM openjdk:8
COPY ./drone-service/target/drone-service-0.0.1-SNAPSHOT drone-service-0.0.1-SNAPSHOT
CMD ["java","-jar","drone-service-0.0.1-SNAPSHOT"]