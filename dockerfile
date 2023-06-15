FROM openjdk:8
COPY . /app
WORKDIR /app
RUN apt-get update && apt-get install -y maven
COPY pom.xml .
RUN mvn dependency:resolve
COPY src ./src
RUN mvn package
EXPOSE 8082
CMD ["java", "-jar", "target/crudBlog-0.0.1-SNAPSHOT.jar"]