# Imagen base
FROM openjdk:11

# Se crea el directorio de trabajo
RUN mkdir /usr/src/NGRIntServ

# Se establece el directorio de trabajo
WORKDIR /usr/src/NGRIntServ

# Se copia el .jar al directorio
COPY ./InternalServiceNGR.jar /usr/src/NGRIntServ

# Se inicia la aplicacion
CMD [ "java", "-jar", "./InternalServiceNGR.jar"]
