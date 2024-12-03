
- [English](#english)
- [Español](#español)

# LiterAlura
## English
## Description
**LiterAlura** is an exciting Java programming challenge where you build a **Book Catalog**.
It allows users to interact through a text-based console, search for books
using a **Gutendex** API, store them in a database, and analyze or filter data based on various criteria.

This project was developed as part of the [**Oracle Next Education (ONE)**](https://www.oracle.com/co/education/oracle-next-education/) & [**Alura Latam**](https://www.aluracursos.com/)

## Features
- Search for books by title using an API.
- List registered books and authors.
- Filter books by language (English, Spanish, French, Portuguese).
- List living authors based on a specific year.
- Display the top 10 most downloaded books.
- View statistics about authors and books in the database.

## Pre-requisites
To run this application, ensure the following are installed:
1. **Java JDK 17 or higher** - [Download](https://www.oracle.com/java/technologies/downloads/#java17?er=221886)
2. **Spring Boot 3.2.3** - Project configured via [**Spring Initializr**](https://start.spring.io/)
3. **Maven 4 or higher**  - [Download](https://maven.apache.org/download.cgi)
4. **PostgreSQL 16 or higher** -[Download](https://www.postgresql.org/download/)
5. Optional: IntelliJ IDEA for development - [Download](https://www.jetbrains.com/idea/download/?section=windows)

## Installation and Configuration
1. **Clone the repository:**
```bash
   git clone https://github.com/JuanJoseVillamizar/Challenge-Literalura.git
    cd Challenge-Literalura
```
2. **Configuration de database:**
- Set up a PostgreSQL database.
- Update environment variables with
```bash
   DB_HOST=localhost
   DB_NAME=your_database_name
   DB_USER=your_username
   DB_PASSWORD=your_password
```
3. **Build the project using Maven:**

```bash
   mvn clean install
```
4. **Run the application:**
```bash
   mvn spring-boot:run
```

## Usage
Once the application is running, interact via the console menu. Example:
```bash
   ================= LiterAlura ===============================
1 - Find book by title
2 - List registered books
3 - List registered authors
...
0 - Exit
=============================================================
Enter your choice:
```
For each option, follow the prompts displayed in the console.
Searching for a Book by Title (Option 1)

This option searches for a book by its title using the Gutendex API.
If the book is found, the book details are saved in the database for future use.
If the book already exists in the database, it will not be duplicated. The same applies to authors.

# LiterAlura
## Español
## Introducción
**LiterAlura** es un emocionante desafío de programación en Java en el que construyes un **Catalogo de libros**.
Permite a los usuarios interactuar a través de una consola de texto, buscar libros mediante **Gutendex** API, almacenarlos en una base de datos, y analizar o filtrar datos según diversos criterios.

Este proyecto fue desarrollado como parte del curso [**Oracle Next Education (ONE)**](https://www.oracle.com/co/education/oracle-next-education/) & [**Alura Latam**](https://www.aluracursos.com/)

## Funcionalidades
- Buscar libros por título usando una API.
- Listar libros y autores registrados.
- Filtrar libros por idioma (inglés, español, francés, portugués).
- Listar autores vivos basándose en un año específico.
- Mostrar los 10 libros más descargados.
- Ver estadísticas sobre autores y libros en la base de datos.

## Requistos previos
Para ejecutar esta aplicación, asegúrate de tener instalado:
1. **Java JDK 17 o superior** - [Descargar](https://www.oracle.com/java/technologies/downloads/#java17?er=221886)
2. **Spring Boot 3.2.3** - Proyecto configurado mediante [**Spring Initializr**](https://start.spring.io/)
3. **Maven 4 o superior**  - [Descargar](https://maven.apache.org/download.cgi)
4. **PostgreSQL 16 o superior** -[Descargar](https://www.postgresql.org/download/)
5. Opcional: IntelliJ IDEA para desarrollo - [Descargar](https://www.jetbrains.com/idea/download/?section=windows)

## Instalación y Configuración
1. **Clona el repositorio:**
```bash
   git clone https://github.com/JuanJoseVillamizar/Challenge-Literalura.git
    cd Challenge-Literalura
```
2. **Configura la base de datos:**
- Configura una base de datos en PostgreSQL.
- Actualiza las variables de entorno con:
```bash
   DB_HOST=localhost
   DB_NAME=your_database_name
   DB_USER=your_username
   DB_PASSWORD=your_password
```
3. **Construye el proyecto con Maven:**

```bash
   mvn clean install
```
4. **Ejecuta la aplicación:**
```bash
   mvn spring-boot:run
```

## Uso
Una vez ejecutada la aplicación, interactúa mediante el menú de la consola. Ejemplo:
```bash
   ================= LiterAlura ===============================
1 - Find book by title
2 - List registered books
3 - List registered authors
...
0 - Exit
=============================================================
Enter your choice:
```
Sigue las indicaciones mostradas en la consola para cada opción.

Al buscar Libro por Título (Opción 1)

Esta opción busca un libro por su título utilizando la API de Gutendex.
Si el libro es encontrado, los detalles del libro se guardan en la base de datos para un uso futuro.
Si el libro ya existe en la base de datos, no se duplicará. Lo mismo sucede con los autores.
