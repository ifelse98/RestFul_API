FROM openjdk:8
EXPOSE 8082
ADD target/crudBlog-0.0.1-SNAPSHOT.jar crudBlog-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/crudBlog-0.0.1-SNAPSHOT.jar"]