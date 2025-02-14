# Note Project Spring Boot Application

## Overview

The Note Project Spring Boot Application is a RESTful web service provides endpoints to create, read, update, and delete notes. The application is built using Spring Boot and follows best practices for RESTful API design.

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [API](#api)
- [Screenshots](#screenshots)

## Features

- Create, read, update, and delete notes.
- Input validation for note creation and updates.

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

## API
-Base API URL: `http://127.0.0.1:8000`
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

## Screenshots
- **Add Note**
![Image](https://github.com/user-attachments/assets/5e501134-bafd-4d84-90df-e0a8563187e0)
- **Get All Notes**
![Image](https://github.com/user-attachments/assets/40c1517b-f2dc-4044-9402-ec6418ba5ba1)
- **Update Note**
![Image](https://github.com/user-attachments/assets/6fadf1bb-0dae-44b2-a730-b6bfb2db49cf)
- **Update Note Evidence**
![Image](https://github.com/user-attachments/assets/7743c104-861a-4c7c-a894-03757f2d7509)
- **Delete Note**
![Image](https://github.com/user-attachments/assets/15a9444e-3c98-4699-83c8-8c60da8288b7)
- **Delete Note Evidence**
![Image](https://github.com/user-attachments/assets/b050577d-9d4d-49bf-bdbc-84a6b8c5200a)
- **Validation Length**
![Image](https://github.com/user-attachments/assets/bf07a0d8-4a43-4c11-855e-2493c5bf3907)
- **Validation Not Blank**
![Image](https://github.com/user-attachments/assets/277010c6-e583-4821-aa0a-71178cce5f08)
