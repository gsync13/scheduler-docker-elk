FROM openjdk:17-alpine
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/api.jar
EXPOSE 8002
CMD ["java", "-jar", "api.jar"]
