# Library API Take-Home Exercise (Backend)

## Instructions

Your task is to create an API for a simple book management system that allows users to manage information about authors and the books they have written. This exercise is intentionally open-ended, and you are welcome to implement your solution using the language and tech stack of your choice. The core functionality of the application should be expressed through your own original code.

You should aim to spend no more than 2 hours on this project. If you don't complete everything in 2 hours, please submit what you have - we value your time and want to see your prioritization skills.

### Application Description

Implement an API that allows users to perform CRUD (Create, Read, Update, Delete) operations on authors and books. Each author should have the following properties:

```
- name
- bio
```

Each book should have the following properties:

```
- title
- description
- author
- published date
```

The API should support the following operations:

1. Create a new author
2. Retrieve a list of all authors
3. Retrieve a specific author by ID
4. Update an author
5. Delete an author
6. Create a new book
7. Retrieve a list of all books
8. Retrieve a specific book by ID
9. Update a book
10. Delete a book

### Minimum Requirements

-   Implement all CRUD operations for authors and books
-   Use appropriate HTTP methods and status codes
-   Implement basic input validation
-   Authors and books do not need to persist across server shutdown/startup (i.e., setting up a DB isn't necessary - server memory should suffice)
-   Provide clear API documentation (can be in the README)

> Note: There is no need to implement any form of authentication or authorization for this exercise

If you have additional time, consider spending it on testing, error handling, or implementing more advanced features like searching, sorting, or filtering.

## Evaluation Criteria

We will be evaluating your submission based on the following:

1. Functionality: Does the API work as described?
2. Code quality: Is the code clean, well-organized, and following best practices?
3. API design: Are the endpoints intuitive?
4. Data modeling: How well are the relationships between authors and books represented?
5. Error handling: How does the application handle invalid inputs or errors?
6. Technical choices: Are the chosen technologies appropriate for the task?
7. Documentation: Is the code well-commented and the README clear?

## Deliverables

Please fill out the sections below in the _README.md_ of your project and submit according to the instructions you received with this project. Your code can be sent as a zip file or a link to a repository containing your project.

---

## Implementation Details

<!-- Provide a short description of your implementation (technologies used, brief overview of project architecture, etc.) -->

For this library application, I used Java w/ Spring Boot, as well as JPA, which facilitates persisting data to an H2 database. JPA provides annotations that allow for input validation as well as interaction with the underlying database. It provides basic CRUD methods as well as the ability to customize your own depending on the fields in classes marked with the @Entity annotation.

The architecture follows a basic MVC format. I've modeled the Author and Book object as well as provided Request DTOs for both (these are to separate the user's request from the actual author/book entity). The entry point of the application is LibraryapiApplication.java and a variety of apis are exposed in the LibraryController.java class.

For example:

1. A user makes a HTTP request with/withou a DTO body depending on the method
2. The controller handles the method and passes it to the service
3. The service queries the DB as needed and performs the required CRUD operations
4. If data is required, it is returned along with the HTTP response code

## How to Run

<!--
- Include instructions on how to run your implementation locally. Be sure to include any necessary setup steps, such as installing dependencies, as well as the commands to start the application.
-->

## API Documentation

<!--
- Provide clear documentation for your API endpoints, including:
  - HTTP method
  - URL
  - Request parameters (if any)
  - Request body format (if applicable)
  - Response format
  - Example curl commands or Postman collection (optional but appreciated)
-->

1. POST localhost:8080/api/v1/books (add book)
    - Example Request Body: {
      "title": "test",
      "description": "test",
      "author": {
      "firstName": "Dallin",
      "lastName": "Christensen",
      "bio": "test"
      },
      "publishedDate": "2024-01-01"
      }
    - Example Response Body: {
      "bookId": 1,
      "title": "test",
      "description": "test",
      "author": {
      "authorId": 1,
      "firstName": "Dallin",
      "lastName": "Christensen",
      "bio": "test"
      },
      "publishedDate": "2024-01-01"
      }
2. GET localhost:8080/api/v1/books (get all books)
    - Example Response Body: [
   {
   "bookId": 1,
   "title": "test",
   "description": "test",
   "author": {
   "authorId": 1,
   "firstName": "Dallin",
   "lastName": "Christensen",
   "bio": "test"
   },
   "publishedDate": "2024-01-01"
   }
   ]

3. GET localhost:8080/api/v1/books/1 (get book from id)
    - Path variable: bookId (1 in this case) 
    - Example Response Body: {
   "bookId": 1,
   "title": "test",
   "description": "test",
   "author": {
   "authorId": 1,
   "firstName": "Dallin",
   "lastName": "Christensen",
   "bio": "test"
   },
   "publishedDate": "2024-01-01"
   }

4. PUT localhost:8080/api/v1/books/1 (update book with body & id)
    - Path variable: bookId (1 in this case)
    - Example Request Body: {
      "title": "My Book",
      "description": "test",
      "author": {
      "firstName": "Dallin",
      "lastName": "Christensen",
      "bio": "test"
      },
      "publishedDate": "2024-01-02"
      }
    - Example Response Body: {
      "bookId": 1,
      "title": "My Book",
      "description": "test",
      "author": {
      "authorId": 2,
      "firstName": "Dallin",
      "lastName": "Christensen",
      "bio": "test"
      },
      "publishedDate": "2024-01-02"
      }

5. DELETE localhost:8080/api/v1/books/2 (delete book by id)

    - Path variable: bookId

6. POST localhost:8080/api/v1/authors (create author)
    - Example Request body: {
    "firstName": "Bill",
    "lastName": "Hader",
    "bio": "Comedian"}
    - Example Response Body: {
    "authorId": 1,
    "firstName": "Bill",
    "lastName": "Hader",
    "bio": "Comedian"}
7. GET localhost:8080/api/v1/authors (get all authors)
    - Example Response Body: [
    {
        "authorId": 1,
        "firstName": "Bill",
        "lastName": "Hader",
        "bio": "Comedian"
    },
    {
        "authorId": 2,
        "firstName": "Dallin",
        "lastName": "Christensen",
        "bio": "test"
    }
]
8. GET localhost:8080/api/v1/authors/1 (get author from id)
    - Path Variable: authorId
    - Example Response Body: {
    "authorId": 1,
    "firstName": "Bill",
    "lastName": "Hader",
    "bio": "Comedian"
}
9. PUT localhost:8080/api/v1/authors/1 (update author with body & id)
    - Path variable: authorId
    - Example Request Body: {
    "firstName": "Bill",
    "lastName": "Hader",
    "bio": "Actor"
}
    - Example Response Body:{
    "authorId": 1,
    "firstName": "Bill",
    "lastName": "Hader",
    "bio": "Actor"
}
10. DELETE localhost:8080/api/v1/authors/1 (delete author by id)
    - Path variable: authorId


## Testing

<!-- Describe how you tested your solution (automated testing, manual testing process, etc.) -->

## Tools Used

<!--
- Describe any tools you used in developing your solution (e.g. ChatGPT for generating ideas)
- Note: The use of AI tools is not discouraged, but they should be used judiciously.
-->

---

Good luck, and we look forward to reviewing your submission!
