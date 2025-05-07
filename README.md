# MovieFlix

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring](https://img.shields.io/badge/Spring_Boot-3.4.5-green)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen)

Movie catalog and streaming platform management system built with Spring Boot. This API allows registration, querying, updating, and deletion of movies with their respective categories and streaming platforms.

## 📋 Table of Contents

- [Overview](#-overview)
- [Main Features](#-main-features)
- [Technologies](#-technologies)
- [Installation and Setup](#-installation-and-setup)
- [API Usage](#-api-usage)
- [Database Structure](#%EF%B8%8F-database-structure)
- [API Documentation](#-api-documentation)
- [License](#-license)

## 🔎 Overview

MovieFlix is a backend system for managing movies and their associated streaming platforms. It allows you to register movies with details such as title, description, release date, and rating, as well as associate them with categories and streaming platforms.

## 🚀 Main Features

### Movies

- Registration of movies with title, description, release date, and rating
- Listing of all movies
- Query by ID
- Search by category
- Full and partial updates
- Deletion

### Categories

- Registration and management of movie categories
- Linking categories to movies
- Listing and querying

### Streaming Platforms

- Registration of streaming platforms
- Linking streaming platforms to movies
- Listing and querying

### Authentication and Security

- JWT-based authentication
- Token management
- Secured API endpoints
- Role-based access control
- Token expiration and refresh mechanism
- Password encryption with BCrypt
- Protection against common web vulnerabilities

## 💻 Technologies

- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Data JPA**: For data persistence
- **Spring Security**: For authentication and authorization
- **JWT**: For token-based authentication
- **PostgreSQL**: Main database
- **Flyway**: For database migrations and versioning
- **Lombok**: For reducing boilerplate code
- **SpringDoc OpenAPI**: For automatic API documentation
- **Maven**: For dependency management

## 🔧 Installation and Setup

### Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- PostgreSQL

### Installation Steps

1. Clone the repository:

```bash
git clone https://github.com/yourusername/movieflix.git
cd movieflix
```

2. Configure environment variables using the `.env` file (use the provided `.envexample` as a template):

```
DATABASE_URL=jdbc:postgresql://localhost:5432/movieflix
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password
JWT_SECRET=your_jwt_secret_key_here
```

3. Compile and run the application:

```bash
mvn clean install
mvn spring-boot:run
```

## 📝 API Usage

### Movie Endpoints

#### Create a movie

```http
POST /api/movies
```

#### List all movies

```http
GET /api/movies
```

#### Find movie by ID

```http
GET /api/movies/{id}
```

#### Update movie

```http
PUT /api/movies/{id}
```

#### Partial update movie

```http
PATCH /api/movies/{id}
```

#### Delete movie

```http
DELETE /api/movies/{id}
```

### Category Endpoints

#### Create a category

```http
POST /api/categories
```

#### List all categories

```http
GET /api/categories
```

#### Find category by ID

```http
GET /api/categories/{id}
```

### Streaming Platform Endpoints

#### Create a streaming platform

```http
POST /api/platforms
```

#### List all streaming platforms

```http
GET /api/platforms
```

#### Find streaming platform by ID

```http
GET /api/platforms/{id}
```

### Authentication Endpoints

#### Register user

```http
POST /api/auth/register
```

#### Login

```http
POST /api/auth/login
```

#### How to use authentication token

For protected endpoints, include the JWT token in the Authorization header:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

#### Security Implementation

The API uses Spring Security with JWT (JSON Web Tokens) for stateless authentication:

- **Token-based Authentication**: All protected endpoints require a valid JWT token
- **Token Duration**: Tokens expire after 24 hours by default
- **Password Storage**: Passwords are stored using BCrypt hashing algorithm
- **CORS Configuration**: API has proper CORS settings for web clients
- **Role-based Authorization**: Different endpoints require different roles (ADMIN, USER)

## 🗃️ Database Structure

The system uses Flyway migrations to create and update the database structure. The main tables include:

- `tb_movies`: Stores movie information
- `tb_categories`: Stores category information
- `tb_platforms`: Stores streaming platform information
- `tb_movies_categories`: Join table for movies and categories
- `tb_movies_platforms`: Join table for movies and platforms
- `tb_users`: Stores user information for authentication

## 📚 API Documentation

Complete API documentation is available through Swagger UI, accessible after starting the application:

```html
http://localhost:8080/swagger-ui/index.html
```

## 📄 License

This project is licensed under the MIT License terms.

---

Developed by [Raniery](https://github.com/Ranieeery)