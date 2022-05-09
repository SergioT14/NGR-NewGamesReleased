# NGR-NewGamesReleased
Vídeo con el funcionamiento de la aplicación a continuación (redirección a Youtube)

[![NewGamesReleased](https://img.youtube.com/vi/ZW-W-05kXK0/0.jpg)](https://www.youtube.com/watch?v=ZW-W-05kXK0)

La aplicación web se trata de una especie de foro donde los administradores de la misma colgarán "posts" pequeños
relacionados con ofertas y novedades en las distintas plataformas de videojuegos.

La funcionalidad publica será poder ver las novedades y navegar por la aplicación para ver los distintos posts, pudiendo iniciar sesión para ver posts asociados a etiquetas en un feed personalizado y comentar junto a una valoración (en pocos caracteres) en los posts.
La funcionalidad privada será, como ya se ha explicado, el poder crear los posts y etiquetarlos, al mismo tiempo que crear nuevas etiquetas si fuese necesario, y moderar los comentarios.

Las entidades son las siguientes:
- Usuarios: Diferenciados por su nivel de permisos. Interactúan con la aplicación comentando los posts o siguiendo etiquetas.
- Posts: Creados por usuarios administradores. Asociados a etiquetas con posibilidad de ser comentados por cualquier usuario registrado.
- Etiquetas: Categorías para localizar más facilmente los posts. Los usuarios se pueden suscribir a estas, y los administradores crear más.
- Valoraciones: Valoración basada en puntuación de 0 a 5. Cualquier usuario registrado puede realizar una. Acompañada de un comentario de pocos caracteres.

El servicio interno que se ofrece es un servicio de newsletter, donde se enviarán al correo asociado al usuario novedades sobre las etiquetas que el usuario esté siguiendo.

Los archivos JAR se encuentran en el siguiente enlace por si no se quieren compilar: https://urjc-my.sharepoint.com/:f:/g/personal/d_alfonsel_2019_alumnos_urjc_es/ElvnUP4thNVBp48c16TPzSMB_0Z1OdjCg-KL6re7y9_0bw?e=e2nWg7

Para compilarlo a través de STS basta con hacer click derecho sobre el proyecto, buscar la opción 'Run as', y dentro de esta, la opción de 'Maven build...':

![image](https://user-images.githubusercontent.com/98475235/160368073-79376f66-4e92-44dd-a1a1-6b0a4ae70156.png)

Una vez en el menú, hay que añadir en el campo 'Goals' la palabra 'package'. Hay que desactivar los test, ya que dan error:

![image](https://user-images.githubusercontent.com/98475235/160370172-3419832d-7662-4baa-87f2-41eac89456bd.png)

Realizar el mismo proceso con el servicio interno.

Para desplegar la aplicación y el servicio interno es necesario instalar lo siguiente:

  - MySQL Community Installer: https://dev.mysql.com/downloads/installer/
    * Hay que hacer una instalación Custom (no se necesita todo). Basta con instalar MySQL Server y MySQL Workbench
    * Es posible que sea necesario instalar cualquier versión de Python 3.x: https://www.python.org/downloads/
  - Java 11 o superior
  - RabbitMQ: https://www.rabbitmq.com/
    * Es necesario instalar Erlang 23 o superior: https://www.erlang.org/downloads 

Tras instalar todo lo necesario, antes de arrancar la aplicación es necesario crear el esquema. Para ello simplemente hay que acceder a la base de datos a través del Workbench. Una vez se ha accedido (debería haber un perfil ya creado por el instalador de la comunidad), crear un esquema con el nombre 'bbdd' (sin las comillas). No es necesario configurar el servidor RabbitMQ, de eso ya se encarga el servicio interno cuando arranca.

Una vez realizado todo esto, abrir dos shells en las carpetas donde se encuentran los JAR. Para más comodidad se recomienda guardarlos juntos. En la primera shell introducir el comando

  java -jar ./NewGamesReleased.jar

Se sabrá que se ha iniciado correctamente porque aparece la siguiente linea al final:
![image](https://user-images.githubusercontent.com/98475235/160371225-cea9e38f-91e9-4756-8c05-15095d231d5e.png)

Una vez se ha visto esa linea, proceder a iniciar el servicio interno, con el comando

  java -jar ./InternalServiceNGR.jar

De igual forma, esta linea indica que se ha inciado correctamente:
![image](https://user-images.githubusercontent.com/98475235/160371995-6364f019-6023-4d50-a5a2-5bfab201a28d.png)

Cada vez que se cree un nuevo post, este se enviará al servicio interno. Para saber si el servicio interno ha recibido el post, en la shell aparece un mensaje de que se ha recibido un post y se puede visualizar su información. Por ejemplo:
![image](https://user-images.githubusercontent.com/98475235/160372438-86a3f479-a697-4763-bedf-58fee928a932.png)

La infraestructura de docker-compose es la siguiente:

![dsf (1)](https://user-images.githubusercontent.com/98475235/167470735-73f9eab3-d541-406f-bf90-f1be8835ffdc.jpg)

Como se observa, el usuario se enfrenta al HAProxy. Este balancea la carga entre las dos aplicaciones. Ambas aplicaciones envian y reciben datos de la base de datos, y envían mensajes a la cola de RabbitMQ. Por su parte, el servicio interno también envía y recibe mensajes de la base de datos y recoge los mensajes de la cola de mensajes.

Las distintas clases y sus relaciones se pueden ver en los siguiente diagrama UML:

![Controlador de página con los repositorios](https://user-images.githubusercontent.com/93717547/160298147-44255b05-520d-49c0-9f38-359ae36af60e.jpg)
![Clases model](https://user-images.githubusercontent.com/93717547/160298162-8c288f4e-1111-4d43-aafe-afd1eeea5fad.jpg)
![Servicio interno con la aplicación](https://user-images.githubusercontent.com/93717547/160298167-12e64bfe-44e5-414b-98a8-04740bb5b0ed.jpg)
![Configuraciones, usuarios y caché](https://user-images.githubusercontent.com/93717547/167473166-8630e655-df88-47cb-961a-738b7ba73188.png)

Esquema de las relaciones entre los modelos:

![41ff0b9081f4d1c070067736d10630e6](https://user-images.githubusercontent.com/93717547/160298170-7cdc0ac9-dd6a-4561-88ba-e614931019d8.png)

Esquema de navegación por las pestañas principales:

![UML](https://user-images.githubusercontent.com/93717547/160298196-d035ef9b-7ce6-41f8-85d2-4033ddc30998.png)

Pestaña de inicio con todos los posts, su edición y eliminación y creación de nuevos posts:

![principal](https://user-images.githubusercontent.com/93717547/155187495-83b5cebc-1c8c-4595-9cbe-1b2e6fb77dc6.png)

Pestaña con todas las etiquetas, su edición y eliminación y creación de otras nuevas:

![ad151a3fa1fa81482e0388a820867561](https://user-images.githubusercontent.com/93717547/160298302-c22050f7-3e3a-4768-960e-b96765b4981b.png)

Pestaña con todos los usuarios, su edición y eliminación y la lista de sus etiquetas suscritas:

![98dd3252f63da95c39edacfd1aded54b](https://user-images.githubusercontent.com/93717547/160298339-3e3ec23a-53ec-472d-8c89-bd2f5758ec71.png)

Pestaña de búsqueda tras haber escrito en el recuadro lo que queremos buscar. En este ejemplo no se ha escrito nada, por lo que se devuelve todo lo existente en la base de datos en sus respectivos apartados. NOTA: En los posts se busca el título y el contenido, aunque sólo se refleje el link al post con su título.

![b4e28d1543a7e1785790077c497e819a](https://user-images.githubusercontent.com/93717547/160298369-a1284103-3da9-44de-9252-d61a96f8aac8.png)

Vista del post tras entrar a él, con las valoraciones debajo, opción de crear una nueva y de eliminar alguna existente:

![image](https://user-images.githubusercontent.com/98475235/160363815-1c5bf1b8-658e-4e53-9936-1d9d7bf7687b.png)

Vista de creación de valoración en un post:

![image](https://user-images.githubusercontent.com/98475235/160363745-bd20e60b-0a3f-42ae-8acb-377a97190609.png)

Pestaña de registro:

![a6302fbd9582f06fb56f9318346d0040](https://user-images.githubusercontent.com/93717547/160298426-cdb17304-05aa-48c4-9892-2dde0e6a8cb2.png)

Pestaña de inicio de sesión:

![5cf3bb6c0b48f83c911b1f21290fd79c](https://user-images.githubusercontent.com/93717547/160298403-07fad53e-187b-4939-b064-d45b3e779e16.png)
