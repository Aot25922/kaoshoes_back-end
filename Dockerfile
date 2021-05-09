FROM openjdk:16-alpine as build
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
RUN apk add --no-cache maven
WORKDIR /mysrc
COPY . /mysrc
RUN chown 777 spring:spring /mysrc
RUN mvn package
RUN apk del maven
EXPOSE 8080
ENTRYPOINT ["java","-jar","/mysrc/target/kaoshoes-0.0.1-SNAPSHOT.jar"]
