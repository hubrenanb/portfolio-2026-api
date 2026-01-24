# --- ETAPA 1: BUILD (Compilação) ---
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# --- ETAPA 2: RUN (Execução) ---
# MUDANÇA AQUI: Trocamos a imagem antiga pela Eclipse Temurin (mais estável e leve)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Libera a porta 8080
EXPOSE 8080

# Comando para iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]