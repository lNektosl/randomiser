FROM maven:3.8.3-openjdk-17 as builder
WORKDIR /src
COPY . .
RUN mvn clean install -Dmaven.test.skip

FROM openjdk:17-alpine3.14
COPY --from=builder /src/target/posts_demo-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java","-jar", "/application.jar"]