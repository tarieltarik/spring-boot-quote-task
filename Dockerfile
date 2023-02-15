FROM openjdk:8
EXPOSE 8080
ADD target/spring-boot-quote-app-docker.jar spring-boot-quote-app-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-quote-app-docker.jar"]