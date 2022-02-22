# NGR-NewGamesReleased

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

Las distintas clases y sus relaciones se pueden ver en el siguiente diagrama UML realizado con la herramienta PlantUML:

![dPJVIyCm4CUVtLyH-gHY2xwMCIO-228oHlLbZ76qPwcaIKco3L7_TyrIdRUZ9VXwtnztdsLqRXqOjstbf9HWhNZ4_HsqQ9S84i5YTTDriYd1DLg9rUeLTf2NkiqKxcllCnXisLXmlHRlayz-mwrMpcW9bJOBg1i5exLbS4iq6GkIseMsRecTjetJvkqWy4rfEXF9TN9gpaL71dtb4boZwj](https://user-images.githubusercontent.com/98475235/155020756-d767adc9-2e05-4130-9bcc-d0b4e15f5e38.png)

Esquema de la relación 1:N entre el post y sus valoraciones, las cuales desaparecen si lo hace el post:

![RelaciónP-V](https://user-images.githubusercontent.com/93717547/155187285-86023824-7a4e-401c-8aed-4d4ad4d10475.png)

Esquema de navegación por las pestañas principales:

![Navegación](https://user-images.githubusercontent.com/93717547/155187418-27d70d6a-fa47-4d0a-99a4-9b8fdfd751c3.png)

Pestaña de inicio con todos los posts, su edición y eliminación y creación de nuevos posts:

![principal](https://user-images.githubusercontent.com/93717547/155187495-83b5cebc-1c8c-4595-9cbe-1b2e6fb77dc6.png)

Pestaña con todas las etiquetas, su edición y eliminación y creación de otras nuevas:

![etiquetas](https://user-images.githubusercontent.com/93717547/155187737-a806ba3b-266c-4c0d-b2fc-58ab96533c3f.png)

Pestaña con todos los usuarios, su edición y eliminación y creación de nuevos:

![usuarios](https://user-images.githubusercontent.com/93717547/155187845-552f31da-de60-4ed3-bdfe-73b442c38fb0.png)

Pestaña de búsqueda tras haber escrito en el recuadro lo que queremos buscar. En este ejemplo no se ha escrito nada, por lo que se devuelve todo lo existente en la base de datos en sus respectivos apartados. NOTA: En los posts se busca el título y el contenido, aunque sólo se refleje el link al post con su título.

![búsqueda](https://user-images.githubusercontent.com/93717547/155189517-d4f6b416-84d4-437c-af5c-557220f57ab6.png)

Vista del post tras entrar a él, con las valoraciones debajo, opción de crear una nueva y de eliminar alguna existente:

![post](https://user-images.githubusercontent.com/93717547/155188317-b5eb04eb-9731-4a38-b6a4-891b3bf7ad12.png)

Vista de creación de valoración en un post:

![crearvaloracion](https://user-images.githubusercontent.com/93717547/155188484-6c2747c8-f3b4-4d40-92af-740208fa33dd.png)
