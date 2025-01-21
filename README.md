📝 Proyecto LibroConectado

Este proyecto es una aplicación de consola creada en Java utilizando el framework Spring Boot. La aplicación se conecta a una API externa para obtener datos relacionados con libros, los cuales se almacenan en una base de datos usando JPA e Hibernate para la persistencia de la información.

🔍 Características Principales

Conexión API Externa: La aplicación obtiene información sobre libros a través de una API pública.
Persistencia de Datos: Los datos obtenidos se almacenan en una base de datos con la ayuda de JPA y Hibernate.
Entidades: Maneja dos entidades principales: Book y Author.
⚙️ Requisitos del Sistema

Java 17 o superior
Maven para la gestión de dependencias
Base de datos MySQL o compatible con JPA
Spring Boot para desarrollo rápido
JPA Hibernate para persistencia
🚀 Instrucciones de Instalación

Clona el repositorio en tu máquina local:
bash
Copiar
Editar
git clone https://github.com/tu_usuario/LibroConectado.git
cd LibroConectado
Configura las variables de entorno o ajusta el archivo application.properties con la configuración de tu base de datos:
properties
Copiar
Editar
spring.datasource.url=jdbc:mysql://localhost/libro_db
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
Ejecuta la aplicación con Maven:
bash
Copiar
Editar
mvn spring-boot:run
🛠️ Tecnologías y Herramientas

Java: El lenguaje principal para el desarrollo.
Spring Boot: Framework para aplicaciones Java de rápida implementación.
Spring Data JPA: Proporciona acceso a la base de datos y mapeo objeto-relacional.
Hibernate: ORM usado para la persistencia de objetos.
API REST: Conexión con una API externa para recuperar información.
MySQL: Base de datos utilizada para almacenar los datos.
📂 Estructura del Proyecto

src/main/java: Contiene el código fuente de la aplicación.
controller: Controladores que gestionan las peticiones.
service: Lógica de negocio y servicios.
repository: Interfaces para acceder a la base de datos.
model: Contiene las entidades Book y Author.
src/main/resources: Archivos de configuración y recursos.
🔧 Uso de la Aplicación La aplicación consulta información de libros desde una API externa y la guarda en una base de datos local. Puedes acceder a estos datos utilizando los repositorios de JPA que están disponibles, o bien, extender el proyecto para agregar interfaces gráficas o web para interactuar con la base de datos de manera más visual.
