FROM openjdk:16-alpine as build
RUN apk add --no-cache maven
WORKDIR /mysrc
COPY . /mysrc
RUN mvn clean package
RUN apk del maven
EXPOSE 80
ENTRYPOINT ["java","-jar","/mysrc/target/sit-int221-kaofood-0.0.1-SNAPSHOT.jar"]