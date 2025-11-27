# Spring Boot Blog REST API

A robust RESTful API for a blog application built using Spring Boot, MySQL, and JPA/Hibernate. This API provides comprehensive endpoints for managing blog posts with features like pagination, sorting, and CRUD operations.

## 🛠 Technologies Used

- **Spring Boot** - Backend Framework
- **Spring Data JPA** - Data Access
- **MySQL** - Database
- **Lombok** - Reduces Boilerplate Code
- **Postman** - API Testing
- **MySQL Workbench** - Database Management

## 📋 Prerequisites

- JDK 17 or later
- Maven 3+
- MySQL 8+
- Postman (for testing)
- MySQL Workbench

## 🚀 Features

### Blog Post Management
- Create new blog posts
- Retrieve all posts with pagination and sorting
- Get single post by ID
- Update existing posts
- Delete posts
- Unique title constraint for posts

### Pagination & Sorting
- Customizable page size
- Page number navigation
- Sorting by any post attribute
- Ascending/Descending sort order

## 🔌 API Endpoints

### Posts API
```
Base URL: /api/posts
```

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/posts` | Create a new blog post |
| GET | `/api/posts` | Get all posts (with pagination & sorting) |
| GET | `/api/posts/{id}` | Get post by ID |
| PUT | `/api/posts/{id}` | Update post |
| DELETE | `/api/posts/{id}` | Delete post |

### Request Parameters for GET /api/posts

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| pageNo | int | 0 | Page number |
| pageSize | int | 10 | Number of items per page |
| sortBy | String | "id" | Field to sort by |
| sortDir | String | "asc" | Sort direction (asc/desc) |

## 🗄️ Database Structure

### Posts Table
```sql
CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    version INTEGER NOT NULL DEFAULT 0,
    title VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    content TEXT
);
```

## 🛠️ Setup & Installation

1. **Clone the Repository**
```bash
git clone https://github.com/froster02/springboot-blog-rest-api.git
cd springboot-blog-rest-api
```

2. **Configure MySQL**
- Open MySQL Workbench
- Create a new database
- Update `application.properties` with your database credentials

3. **Build the Project**
```bash
mvn clean install
```

4. **Run the Application**
```bash
mvn spring-boot:run
```

## 📝 API Usage with Postman

### Creating a New Post
```http
POST /api/posts
Content-Type: application/json

{
    "title": "Your Post Title",
    "description": "Post Description",
    "content": "Post Content"
}
```

### Getting Posts with Pagination
```http
GET /api/posts?pageNo=0&pageSize=10&sortBy=title&sortDir=desc
```

### Updating a Post
```http
PUT /api/posts/1
Content-Type: application/json

{
    "title": "Updated Title",
    "description": "Updated Description",
    "content": "Updated Content"
}
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/springboot/blog/
│   │       ├── controller/
│   │       │   └── PostController.java
│   │       ├── entity/
│   │       │   └── Post.java
│   │       ├── repository/
│   │       │   └── PostRepository.java
│   │       ├── service/
│   │       │   ├── PostService.java
│   │       │   └── impl/
│   │       │       └── PostServiceImpl.java
│   │       └── SpringbootBlogRestApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/springboot/blog/
            └── SpringbootBlogRestApiApplicationTests.java
```

## 🔒 Error Handling

The API includes robust error handling for common scenarios:
- Resource not found (404)
- Bad requests (400)
- Unique constraint violations
- Server errors (500)

## 📚 Service Layer Methods

- `createPost(PostDto postDto)` - Creates a new blog post
- `getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir)` - Retrieves paginated and sorted posts
- `getPostById(long id)` - Retrieves a specific post by ID
- `updatePost(PostDto postDto, long id)` - Updates an existing post
- `deletePostById(long id)` - Deletes a post by ID

<!-- AUTO-GENERATED-API-DOCS -->
<!-- END-AUTO-GENERATED-API-DOCS -->

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

<img width="4844" height="3278" alt="image" src="https://github.com/user-attachments/assets/cb3b1e16-5fbb-435e-a267-edb4e57a5600" />

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
## 📊 Detailed Project Analysis & Documentation
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

### 1. Project Flow / Architecture
- Layered Structure (MVC + Service Layer):
  - Controller Layer: Handles HTTP requests (`PostController`, `CommentController`, `AuthController`, etc.), receives input, and delegates business logic to the service layer.
  - Service Layer: Contains business logic (`PostService`, `PostServiceImpl`, etc.), processes data, and interacts with repository layer.
  - Repository Layer: Manages data persistence, typically interfaces with the database using JPA repositories (`PostRepository`, `UserRepository`, etc.).
  - Entity Layer: Java classes map to database tables (`Post.java`, `User.java`, etc.).
- Flow Example:
  1. An HTTP request (e.g., POST /api/v1/posts) is received by a Controller.
  2. Controller validates input using annotations (e.g., @Valid), then calls the relevant Service method.
  3. Service layer processes business rules, interacts with the Repository to fetch or persist data.
  4. Repository uses Spring Data JPA to execute queries against the database.
  5. Service returns response DTOs to the Controller.
  6. Controller wraps the result in a ResponseEntity and sends it back to the client.

### 2. Java Features Used
- OOP (Object-Oriented Programming):
  - Used throughout (entities, DTOs, service implementations, etc.).
  - Example: Post entity, PostServiceImpl as an implementation of PostService interface.
- Generics:
  - Used in Spring Data repositories (e.g., JpaRepository<Post, Long>).
  - Enables type-safe CRUD operations.
- Exception Handling:
  - Custom exceptions (ResourceNotFoundException), handled to return meaningful HTTP responses.
- Streams & Lambdas:
  - Typically used in DTO mapping and result processing (e.g., mapping lists of entities to DTOs).
- Annotations:
  - Used extensively for configuration, dependency injection, request mapping, and validation.

### 3. Spring Boot Features & Flow
- Auto-Configuration: @SpringBootApplication enables auto-configuration, component scanning, and configuration properties.
- Dependency Injection: Managed via @Autowired, constructor injection, and @Service/@Repository annotations.
- Beans: Defined using @Bean annotation (e.g., ModelMapper bean in main application class).
- Component Scanning: Automatically scans for classes annotated with @Component, @Service, @Repository, @RestController.
- Starter Dependencies: Uses Spring Boot starters (e.g., spring-boot-starter-data-jpa, spring-boot-starter-web, etc.) for out-of-the-box configuration.
- Validation: Uses @Valid annotation and Jakarta Validation API for input validation.
- Security: Uses @PreAuthorize for method-level access control, JWT authentication.

### 4. Database Connection & Persistence
- Configuration:
  - Database credentials configured in application.properties.
  - Example: MySQL setup instructions in README.
- Persistence Layer:
  - Spring Data JPA is used for ORM (Object-Relational Mapping).
  - Entities are annotated with @Entity and mapped to tables.
  - Repositories extend JpaRepository interface for CRUD operations.
- Entities & Repositories:
  - Example: Post.java (entity), PostRepository.java (repository).
  - Queries handled by method naming conventions or custom queries.
- Transactions:
  - Service layer methods can be annotated with @Transactional (if needed).

### 5. Annotations Used (Java + Spring Boot)
| Annotation            | What It Does                                                  | Why Used Here                             | How It Works Internally                       |
|-----------------------|--------------------------------------------------------------|-------------------------------------------|-----------------------------------------------|
| @SpringBootApplication | Main entry point, enables auto-configuration and scanning   | Bootstraps app                            | Aggregates @Configuration, @EnableAutoConfiguration, and @ComponentScan |
| @RestController     | Marks class as REST controller, returns JSON                  | Handles HTTP requests                     | Combines @Controller and @ResponseBody    |
| @RequestMapping     | Maps HTTP requests to handler methods/class                   | URL routing                               | Registers routes with Spring MVC dispatcher   |
| @PostMapping, @GetMapping, @PutMapping, @DeleteMapping | HTTP method-specific request mapping      | Exposes REST endpoints                       | Maps requests to handler methods              |
| @Autowired          | Injects bean dependencies                                    | Dependency injection                      | Uses reflection and Spring's bean factory     |
| @Service, @Repository | Marks service/repository beans for DI                   | Identifies layer components               | Registers components with Spring container    |
| @Entity             | Marks class as JPA entity                                    | Maps Java objects to DB tables            | Used by Hibernate to manage ORM mapping       |
| @Valid              | Validates method parameters                                  | Input validation                          | Triggers validation using Jakarta Bean Validation |
| @PreAuthorize       | Secures method access (Spring Security)                      | Role-based access control                 | Uses Spring AOP and Security context          |
| @Bean               | Declares a bean in Spring context                            | Custom bean definition                    | Calls annotated method to instantiate bean    |
| @PathVariable, @RequestBody, @RequestParam | Binds web request data to method params   | Handles incoming request data              | Uses argument resolvers in Spring MVC         |
| @Operation, @ApiResponse, @SecurityRequirement, @Tag | Swagger/OpenAPI documentation             | API documentation                          | Generates OpenAPI spec                        |

### 6. End-to-End Request Lifecycle Example
**Example: Creating a Blog Post (POST /api/v1/posts)**
1. Client sends HTTP POST request to /api/v1/posts with JSON body.
2. Controller (PostController#createPost) receives request:
   - Annotated with @PostMapping, validates with @Valid.
   - Delegates to PostService#createPost.
3. Service Layer (PostServiceImpl#createPost):
   - Maps DTO to entity, applies business logic.
   - Calls PostRepository.save(post).
4. Repository (PostRepository):
   - Persists entity using JPA/Hibernate.
   - Returns saved entity.
5. Service maps entity back to DTO.
6. Controller wraps DTO in ResponseEntity and returns HTTP 201 response.
7. Client receives confirmation and post details.

### 7. Additional Insights
- Potential Improvements:
  - Exception Handling: Consider implementing @ControllerAdvice for global exception handling.
  - DTO Usage: Consistently use DTOs to prevent exposing entities directly.
  - Validation: Add more granular validation (e.g., custom validators).
  - Logging: Integrate logging for service/repository actions.
  - Testing: Add unit and integration tests for controllers and services.
  - Pagination & Sorting: Already implemented; ensure customizable options for clients.
- Best Practices:
  - Use constructor injection for mandatory dependencies.
  - Use @Transactional where atomicity is required.
  - Avoid business logic in controllers.
- Design Patterns:
  - MVC (Model-View-Controller): Controller (request), Model (entity/DTO), Repository (data access).
  - Singleton: Beans are singletons by default in Spring.
  - Factory: Bean instantiation via configuration (e.g., @Bean).
  - Service Layer: Decouples business logic from web layer.

### Summary Table
| Layer          | Responsibility                        | Example Class         |
|----------------|---------------------------------------|----------------------|
| Controller     | Handle requests/responses              | PostController       |
| Service        | Business logic                        | PostServiceImpl      |
| Repository     | Data access                           | PostRepository       |
| Entity         | DB mapping                            | Post                 |

mic drop!
