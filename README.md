# Contact Book

Aplicación de libreta de contactos.

## Tecnologías

-   _Backend_:
    -   **Java Spring Boot**.
    -   **JPA**, el ORM **Hibernate** y **JPQL** para el gestión de la base de datos.
    -   **JUnit 5** y **Mockito** para la integración de pruebas unitarias.
-   _Frontend_:
    -   **Vue JS 3** con **TypeScript**.
    -   **TailwindCSS** para estilos.
    -   **vuex** para gestión de estados globales.
    -   **vue-toastify** para notificaciones toast.
    -   **vitest** y **vue-test-utils** para la integración de pruebas unitarias.
-   _Base de datos_:
    -   **MySQL**.

## Herramientas extras

-   **IntelliJ IDEA** como IDE.
-   **VSCode** como editor de código para desarrollar el frontend con Vue.
-   **Postman** para el testing y documentación de los servicios backend.
-   **Asana** para la gestión de las tareas del proyecto.

## Metodología

-   El proyecto aplicó prácticas de **Scrum** para el desarrollo del proyecto.

## Diseño UX/UI

-   Prototipado en Figma: https://www.figma.com/file/JDXrQJDE1VONRWygN50X4u/Contact-Book-Application

## Product Backlog

| Id  | Requerimiento                            | Descripción                                                                                                                                                                                                              | Estimación |
| --- | ---------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ---------- |
| 1   | Listado de contactos                     | El usuario debe visualizar el listado de sus contactos en orden alfabético según sus nombres y con una previsualización de su nombre de contacto, el número telefónico principal y un ícono con la inicial de su nombre. | 5          |
| 2   | Búsqueda de contactos                    | El usuario debe ser capaz de buscar contactos por nombre de contacto o número telefónico.                                                                                                                                | 8          |
| 3   | Agregado de contactos                    | El usuario debe ser capaz de agregar contactos ingresando su nombre, categoría (familia, amigos, trabajo), email (no obligatorio) y hasta 3 números telefónicos.                                                         | 21         |
| 4   | Edición de contactos                     | El usuario debe ser capaz de editar los datos de cada contacto.                                                                                                                                                          | 21         |
| 5   | Eliminación de contacto                  | El usuario debe ser capaz de eliminar cualquier contacto cuando lo requiera.                                                                                                                                             | 13         |
| 6   | Listado de contactos favoritos           | El usuario debe ser capaz de visualizar su lista de contactos favoritos.                                                                                                                                                 | 8          |
| 7   | Anexo / Desanexo de contacto a favoritos | El usuario debe ser capaz de anexar / desanexar contactos favoritos.                                                                                                                                                     | 8          |
| 8   | Números telefónicos únicos               | El usuario no debe poder registrar números telefónicos que ya existan.                                                                                                                                                   | 8          |

## Capturas de pantalla de la aplicación (Application screenshoots)

#### Cargando contactos (Loading contacts)

<img src="./screenshoots/foto-1.png" alt="Cargando contactos"/>

#### Buscando contactos (Search contacts)

<img src="./screenshoots/foto-2.png" alt="Cargando contactos"/>

#### Sin contactos (No contacts)

<img src="./screenshoots/foto-3.png" alt="Cargando contactos"/>

#### Formulario para agregar contacto (Add contact form)

<img src="./screenshoots/foto-4.png" alt="Formulario para agregar contacto"/>

#### Formulario para editar contacto (Edit contact form)

<img src="./screenshoots/foto-5.png" alt="Formulario para editar contacto"/>

#### Validación de formulario (Form validation)

<img src="./screenshoots/foto-6.png" alt="Validación de formulario"/>

#### Confirmación para eliminar contacto (Remove conact confirmation)

<img src="./screenshoots/foto-7.png" alt="Confirmación para eliminar contacto"/>

#### Vista de página no encontrada (Page not found view)

<img src="./screenshoots/foto-8.png" alt="Vista de página no encontrada"/>
