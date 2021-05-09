FROM openjdk:16-alpine as build
RUN apk add --no-cache maven
WORKDIR /mysrc
COPY . /mysrc
RUN mvn package
RUN apk del maven
RUN chmod -R 777 /mysrc
EXPOSE 8080
ENTRYPOINT ["java","-jar","/mysrc/target/kaoshoes-0.0.1-SNAPSHOT.jar"]
