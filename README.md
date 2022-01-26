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
