# Imagen base con Maven y JDK 17 para compilar
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copiar archivos del proyecto
COPY pom.xml .
COPY src ./src

# Compilar el proyecto con Maven omitiendo tests
RUN mvn clean package -DskipTests

# Imagen final ligera para ejecutar la API
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto y ejecutar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]