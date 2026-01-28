FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine AS resource
WORKDIR /app
COPY --from=build /app/resource-service/target/*.jar app.jar
CMD ["java","-jar","app.jar"]

FROM eclipse-temurin:17-jdk-alpine AS process
WORKDIR /app
COPY --from=build /app/process-service/target/*.jar app.jar
CMD ["java","-jar","app.jar"]

FROM eclipse-temurin:17-jdk-alpine AS report
WORKDIR /app
COPY --from=build /app/report-service/target/*.jar app.jar
CMD ["java","-jar","app.jar"]
