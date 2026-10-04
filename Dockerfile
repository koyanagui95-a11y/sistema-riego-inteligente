# Imagen base con Java 17 para compilar
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copiar todos los archivos del proyecto
COPY . .

# Dar permisos de ejecucion a Maven wrapper y compilar
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Imagen final ligera para ejecutar la API
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Puerto por defecto y ejecucion
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]