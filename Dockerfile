FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /build
COPY . .
RUN mvn package -DskipTests

FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=build /build/target/BlogAPI.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]