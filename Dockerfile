# --- ETAPA 1: BUILD (Compilação) ---

# Usa uma imagem com Maven e Java 17 para criar o .jar
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copia os arquivos do projeto para dentro do container
COPY . .

# Roda o comando para gerar o .jar
RUN mvn clean package -DskipTests

# --- ETAPA 2: RUN (Execução) ---
# Usa uma imagem mais leve apenas com o Java (sem Maven) para rodar
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia o .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Libera a porta 8080 (padrão do Spring)
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]