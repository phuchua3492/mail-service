FROM openjdk:20

EXPOSE 8080

ADD target/mail-service.jar mail-service.jar

ENTRYPOINT ["java","-jar","mail-service.jar"]