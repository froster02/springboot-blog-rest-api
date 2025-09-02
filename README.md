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

---

## 📌 README Version
<!-- README-VERSION -->
Current version: v5
<!-- END-README-VERSION -->

