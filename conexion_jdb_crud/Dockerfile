# Dockerfile
FROM openjdk:21-jdk-slim

# Instala Maven y Xvfb para soporte gráfico
RUN apt-get update && apt-get install -y maven xvfb

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos necesarios
COPY pom.xml /app
RUN mvn dependency:resolve
COPY src /app/src

# Construye el proyecto
RUN mvn clean package

# Ejecuta la aplicación con Xvfb
CMD ["xvfb-run", "-a", "mvn", "exec:java", "-Dexec.mainClass=org.example.Main"]
