# Note Project Spring

## Overview

Note Project is a RESTful web service that allows users to manage notes. It provides endpoints to create, read, update, and delete notes. The application is built using Spring Boot and follows best practices for RESTful API design.

## Features

- Create, read, update, and delete notes.
- Input validation for note creation and updates.

## Technologies Used

- **Java 11**: Programming language.
- **Spring Boot 2.7.18**: Framework for building the application.
- **Lombok**: Library to reduce boilerplate code.
- **ModelMapper**: For object mapping between DTOs and entities.
- **JUnit & Mockito**: For unit testing.

## Project Structure
src
├── main
│ ├── java
│ │ └── com
│ │ └── astrapay
│ │ ├── controller
│ │ │ └── NoteController.java
│ │ ├── dto
│ │ │ ├── APIResponse.java
│ │ │ ├── NoteDto.java
│ │ │ └── NoteRequestDto.java
│ │ ├── entity
│ │ │ └── Note.java
│ │ └── service
│ │ └── NoteService.java
│ └── resources
│ └── application.properties
└── test
└── java
└── com
└── astrapay
└── NoteControllerTest.java
## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/sheanmad/astrapay-spring-boot-external.git
   cd astrapay-spring-boot-external
   ```

2. **Build the project**:

3. **Run the application**:
   You can run the application using the following command via your IDE.
   The application will start on port `8000` as specified in `application.properties`.

## Usage

### API Endpoints

- **Create a Note**
  - **Endpoint**: `POST /v1/notes/add`
  - **Request Body**:
    ```json
    {
      "title": "Your Note Title",
      "content": "Your Note Content"
    }
    ```

- **Get All Notes**
  - **Endpoint**: `GET /v1/notes`

- **Get Note by ID**
  - **Endpoint**: `GET /v1/notes/{id}`

- **Update a Note**
  - **Endpoint**: `PUT /v1/notes/{id}`
  - **Request Body**:
    ```json
    {
      "title": "Updated Note Title",
      "content": "Updated Note Content"
    }
    ```

- **Delete a Note**
  - **Endpoint**: `DELETE /v1/notes/{id}`

### Screen Shots


The API documentation is available via Swagger. Once the application is running, you can access it at: http://localhost:8000/swagger-ui/
