# To Do Application BackEnd Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-green?style=plastic
)

This project is an API using **Java and Spring Boot**

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)

## Installation
1. Clone the repository
```bash
git clone https://github.com/rcarvalho-pb/backend-todo-app-java.git
```
## Usage
1. Start the application with:
```bash
./mvnw spring-boot:run
```
2. The API will be accessible at http:localhost:`8080`

## API Endpoints
Documentation via swagger can be accessed by the url: `localhost:8080/swagger-ui/index.html`
  
The API provides the following endpoints:

###**Create To Do activity**
```markdown
POST /api/v1/todos/create - Create and save a new to do activity
```

**Body**
```json
{
    "name": "Test",
    "description": "Test",
    "done": false,
    "priority": 0
}
```

###**Get All To Do Activities**
```markdown
GET /api/v1/todos - Get All saved to do activities
```

###**Find one To Do Activity by ID**
```markdown
GET /api/v1/todos/{id} - Find a saved to do activity by it's id
```

###**Update a To Do Activity**
```markdown
PUT /api/v1/todos/{id} - Update a To Do activity by it's id
```

**Body**
```json
{
    "name": "Test",
    "description": "Test",
    "done": false,
    "priority": 0
}
```

###**Delete To Do Activity by ID**
```markdown
DELETE /api/v1/todos/{id} - Delete a To Do activity by id
```
