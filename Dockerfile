FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/BankApp-0.0.1-SNAPSHOT.jar
#VOLUME /tmp
#EXPOSE 8099
WORKDIR /PROJECT
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]