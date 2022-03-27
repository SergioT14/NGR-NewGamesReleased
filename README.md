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

Las distintas clases y sus relaciones se pueden ver en el siguiente diagrama UML:

![c5dd065e-66b7-4e9e-860d-42e5bfee5e1d](https://user-images.githubusercontent.com/93717547/160298147-44255b05-520d-49c0-9f38-359ae36af60e.jpg)
![c44a56b8-4e01-43f6-abb3-84c709fd3db6](https://user-images.githubusercontent.com/93717547/160298162-8c288f4e-1111-4d43-aafe-afd1eeea5fad.jpg)
![b1f85d79-e184-470e-986b-3cfae604d5ed](https://user-images.githubusercontent.com/93717547/160298167-12e64bfe-44e5-414b-98a8-04740bb5b0ed.jpg)

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

![post](https://user-images.githubusercontent.com/93717547/155188317-b5eb04eb-9731-4a38-b6a4-891b3bf7ad12.png)

Vista de creación de valoración en un post:

![crearvaloracion](https://user-images.githubusercontent.com/93717547/155188484-6c2747c8-f3b4-4d40-92af-740208fa33dd.png)

Pestaña de registro:

![a6302fbd9582f06fb56f9318346d0040](https://user-images.githubusercontent.com/93717547/160298426-cdb17304-05aa-48c4-9892-2dde0e6a8cb2.png)

Pestaña de inicio de sesión:

![5cf3bb6c0b48f83c911b1f21290fd79c](https://user-images.githubusercontent.com/93717547/160298403-07fad53e-187b-4939-b064-d45b3e779e16.png)
