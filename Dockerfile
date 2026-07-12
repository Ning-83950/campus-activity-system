# ---- build stage ----
FROM maven:3.8.6-eclipse-temurin-8 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

# ---- run stage ----
FROM eclipse-temurin:8-jdk
WORKDIR /app
COPY --from=build /app/target/project-spring_boot-1.0-SNAPSHOT.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "app.jar"]
