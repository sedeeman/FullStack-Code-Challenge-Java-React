FROM adoptopenjdk:17-jre-hotspot

WORKDIR /app

COPY target/myapp.jar .

CMD ["java", "-jar", "myapp.jar", "--server.port=3000", "--server.servlet.context-path=/api"]
