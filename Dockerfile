FROM openjdk:16-alpine as build
RUN apk add --no-cache maven
WORKDIR /mysrc
COPY . /mysrc
RUN mvn clean package
RUN apk del maven
EXPOSE 8081
ENTRYPOINT ["java","-jar","/mysrc/target/int221-project-0.0.1-SNAPSHOT.jar"]