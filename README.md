# 🎓 Student Marks Manager

A simple Spring Boot REST API to manage students and their subject marks — built to practice full-stack backend development.

---

## 🚀 Features

- Add a new student with marks in Math, Science, and English  
- View all students  
- View a specific student by ID  
- Delete a student by ID  
- In-memory H2 database with console access  
- Clean architecture using MVC pattern  
- JWT-based authentication for secured endpoints  
- API documentation with Swagger UI  

---

## 🛠️ Tech Stack

- **Java 17**  
- **Spring Boot 3**  
- **Spring Data JPA**  
- **Spring Security + JWT**  
- **H2 Database (In-memory)**  
- **Lombok**  
- **Maven**  
- **Swagger / OpenAPI**  

---

## 📂 Project Structure

src/main/java/com/student/StudentMarks
├── controller # REST controllers
├── model # Entity classes
├── repository # JPA repositories
├── service # Business logic
├── util # Utility classes (e.g., JWT filter)
└── StudentMarksManagerApplication.java





---

## ▶️ Running the Project

1. **Clone the repo**  
   ```bash
   git clone https://github.com/<your-username>/<your-repo>.git
   cd StudentMarks
2.**Build the project** 
   
    mvn clean install -DskipTests
3.**Run the project**
   
    mvn spring-boot:run
or run StudentMarksManagerApplication directly from IntelliJ.

The application will start on http://localhost:8085

---

📖 API Documentation (Swagger)

Once the app is running, explore APIs here:

Swagger UI → http://localhost:8085/swagger-ui/index.html

OpenAPI JSON → http://localhost:8085/v3/api-docs

---

🔐 Authentication

POST /auth/login → open (no authentication needed).

All other endpoints → secured with JWT.

Swagger UI is also accessible without authentication.

---
Example: 

Login and Get Token

curl -X POST http://localhost:8085/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "password": "testpassword"}'

Response:
{ "token": "eyJhbGciOiJIUzI1NiJ9..." }



Example: Access Secured Endpoint

curl -X GET http://localhost:8085/students \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."


----

🗂️ H2 Database Console

URL → http://localhost:8085/h2-console

JDBC URL → jdbc:h2:mem:testdb

Username → sa

Password → (leave blank)

---

🤝 Contribution

Fork, raise issues, and submit PRs to improve this project 🚀


---

✅ This makes your project **demo-ready** for interviews or GitHub visitors.  

Do you also want me to create a **GitHub badge section** at the top (Build Passing, Java Version, Spring Boot Version, License etc.) to make it look even more professional?

 
