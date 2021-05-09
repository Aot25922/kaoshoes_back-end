FROM openjdk:16-alpine as build
RUN apk add --no-cache maven
COPY . .
RUN chmod -R 777 /target
RUN mvn package
RUN apk del maven
EXPOSE 8080
ENTRYPOINT ["java","-jar","/target/kaoshoes-0.0.1-SNAPSHOT.jar"]
