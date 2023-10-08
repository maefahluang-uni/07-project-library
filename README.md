# Microservices with Spring Boot and Eureka

## Member
1.นางสาวญาดา กรึกโคกสูง 652110274
2.นางสาวณัฐฌญาภรณ์ จันทิมา 652110278
3.นายศิรัณย์ สุนันทศิลป์ 652110307

## Clip VDO
https://youtu.be/hrSeVryT9S0

This repository contains a set of Java Spring Boot microservices that work together in a microservices architecture. These microservices include:

- [naming-server](#naming-server)
- [members-service](#members-service)
- [books-service](#books-service)
- [genres-service](#genres-service)
- [greet-service](#greet-service)

## Naming Server

The `naming-server` is a Eureka Naming Server, responsible for service registration and discovery within the microservices ecosystem.

### Running the Naming Server

To run the Naming Server, navigate to the `naming-server` directory and execute the following command:

```shell
mvn spring-boot:run

Access the Eureka server's dashboard at http://localhost:8761 to view registered microservices.

Members Service
The members-service is a microservice for managing members in the system.

Running the Members Service
To run the Members Service, navigate to the members-service directory and execute the following command:
```shell
mvn spring-boot:run

Books Service
The books-service is a microservice for managing books and their information.

Running the Books Service
To run the Books Service, navigate to the books-service directory and execute the following command:

```shell
mvn spring-boot:run

Genres Service
The genres-service is a microservice for managing book genres.

Running the Genres Service
To run the Genres Service, navigate to the genres-service directory and execute the following command:

```shell
mvn spring-boot:run

The HTML and JavaScript code you provided is a simple web page for managing authors, specifically creating new authors and listing existing ones. It uses jQuery for AJAX requests to interact with a RESTful API running on `http://localhost:8083/authors`. Let's break down the code:

- `<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>`: This script tag includes the jQuery library, which is necessary for making AJAX requests and simplifying DOM manipulation.

- Inside the JavaScript code block:
  - `$(document).ready(function () { ... })`: This function is executed when the HTML document is fully loaded and ready for manipulation.

  - `loadAuthors()` Function: This function loads a list of authors from the RESTful API and displays them on the web page. It sends a GET request to `http://localhost:8083/authors`, retrieves the list of authors, and appends their names to the `#authorList` div.

  - `$("#reloadButton").click(function () { ... })`: This click event handler reloads the list of authors when the "Reload" button is clicked. It calls the `loadAuthors()` function to refresh the author list.

  - `$("#createAuthorForm").submit(function (event) { ... })`: This form submission handler intercepts the form submission event and sends a POST request to create a new author. It prevents the default form submission behavior using `event.preventDefault()`.

    - It extracts the author's name from the input field with `$("#authorName").val()`.
    
    - It sends a POST request to `http://localhost:8083/authors` with the author's data as JSON in the request body.

    - Upon successful creation of an author, it logs a success message, clears the input field, and reloads the author list to reflect the new author's addition.

    - If there's an error during the author creation process, it logs an error message.

The HTML structure includes a form for creating authors with an input field for the author's name, a "Create Author" button, and a section to list authors. It also has a "Reload" button to refresh the author list.

This code example is designed to work with a RESTful API on `http://localhost:8083/authors`, which should have appropriate endpoints for creating and retrieving authors. Additionally, it relies on the jQuery library for AJAX requests and DOM manipulation. Ensure that these dependencies are available and that the backend API is correctly configured for this code to work as intended. 

