# Imagen base
FROM openjdk:11

# Se crea el directorio de trabajo
RUN mkdir /usr/src/NGRWeb

# Se establece el directorio de trabajo
WORKDIR /usr/src/NGRWeb

# Se copia el .jar al directorio
COPY ./NewGamesReleased.jar /usr/src/NGRWeb

# Indicamos un puerto de apertura para conectarnos
EXPOSE 8080

# Se inicia la aplicacion
CMD [ "java", "-jar", "./NewGamesReleased.jar"]


