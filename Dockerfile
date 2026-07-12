# ---- build stage ----
FROM maven:3.8-openjdk-8 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

# ---- run stage ----
FROM openjdk:8-jdk-slim
WORKDIR /app
COPY --from=build /app/target/project-spring_boot-1.0-SNAPSHOT.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "app.jar"]
