# 🚩 Feature Flag Management System

A backend **Feature Flag Management System** built using **Spring Boot** and **MySQL** that allows applications to **enable or disable features at runtime without redeploying backend services**.

This project is designed to reflect **real-world backend practices** and is suitable for an **intern / fresher-level portfolio project**.

---

## 📌 What is a Feature Flag?

A **feature flag** is a software development technique used to control the availability of features dynamically.

Instead of deploying new code every time:

* Features can be turned **ON / OFF**
* Controlled per **environment** (DEV / PROD)
* Changed **at runtime**

### Why companies use feature flags:

* Safe feature rollouts
* Quick rollback of broken features
* A/B testing and controlled releases
* Reduced deployment risk

---

## 🧠 Project Overview

This system exposes REST APIs that:

* Read feature flag status from a database
* Allow admins to toggle feature flags
* Separate behavior by environment
* Store configuration in **MySQL**

The backend follows **clean layered architecture** with proper separation of concerns.

---

## 🏗 Architecture Overview

### High-Level Architecture Diagram

```
                ┌───────────────┐
                │   Client /    │
                │   Frontend    │
                └───────┬───────┘
                        │ HTTP (REST)
                        ▼
                ┌──────────────────┐
                │  Controller Layer│
                │ (REST Endpoints) │
                └───────┬──────────┘
                        │
                        ▼
                ┌──────────────────┐
                │   Service Layer  │
                │ (Business Logic) │
                └───────┬──────────┘
                        │
                        ▼
                ┌──────────────────┐
                │ Repository Layer │
                │ (Spring Data JPA)│
                └───────┬──────────┘
                        │
                        ▼
                ┌──────────────────┐
                │     MySQL DB     │
                │ feature_flags    │
                └──────────────────┘
```

### Flow Explanation (Simple)

1. Client calls an API (GET / PUT)
2. Controller receives request
3. Service validates input and applies business rules
4. Repository interacts with MySQL
5. Response is returned as JSON

---

## ✨ Features Implemented

* Runtime feature enable/disable
* Environment-based flags (DEV / PROD)
* RESTful API design
* Database-backed configuration
* Clean exception handling
* Layered backend architecture

---

## 🛠 Tech Stack

| Category        | Technology                  |
| --------------- | --------------------------- |
| Language        | Java 17                     |
| Framework       | Spring Boot                 |
| Database        | MySQL                       |
| ORM             | Spring Data JPA (Hibernate) |
| Build Tool      | Maven                       |
| API Testing     | Postman                     |
| Version Control | Git & GitHub                |

---

## 🧱 Database Design

### Table: `feature_flags`

| Column      | Type    | Description                  |
| ----------- | ------- | ---------------------------- |
| id          | BIGINT  | Primary key (auto-increment) |
| name        | VARCHAR | Feature name                 |
| environment | VARCHAR | DEV / PROD                   |
| enabled     | BOOLEAN | Feature status               |

The table is **automatically created by Hibernate** using JPA entity mapping.

---

## 🔗 API Endpoints

### 1️⃣ Get Feature Flag Status

**Method:** `GET`

```
/api/flags/{featureName}?env=DEV
```

**Example**

```
GET /api/flags/new_checkout?env=DEV
```

**Response**

```json
{
  "feature": "new_checkout",
  "environment": "DEV",
  "enabled": true
}
```

---

### 2️⃣ Toggle Feature Flag (Admin Operation)

**Method:** `PUT`

```
/api/flags/{featureName}/toggle?env=DEV&enabled=false
```

**Example**

```
PUT /api/flags/new_checkout/toggle?env=DEV&enabled=false
```

**Response**

```json
{
  "feature": "new_checkout",
  "environment": "DEV",
  "enabled": false
}
```

---

## 🔐 Error Handling

The application uses **global exception handling** to return meaningful HTTP responses.

Handled cases:

* Invalid environment → `400 Bad Request`
* Feature flag not found → `400 Bad Request`
* Clean JSON error responses instead of server crashes

Implemented using:

* `@RestControllerAdvice`
* Centralized error handling logic

---

## 🚀 How to Run the Project Locally

### 1️⃣ Prerequisites

* Java 17 or higher
* MySQL installed and running
* Maven

---

### 2️⃣ Create Database

```sql
CREATE DATABASE feature_flags_db;
```

---

### 3️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/feature_flags_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 4️⃣ Run the Application

```bash
mvn spring-boot:run
```

Server runs at:

```
http://localhost:8080
```

---

## 🧪 Testing the APIs

* **GET APIs** can be tested directly in the browser
* **PUT APIs** tested using **Postman** or `curl`

Example:

```bash
curl -X PUT "http://localhost:8080/api/flags/new_checkout/toggle?env=DEV&enabled=false"
```

---

## 📚 What I Learned from This Project

* Designing REST APIs using Spring Boot
* Implementing layered backend architecture
* Using Spring Data JPA and Hibernate for ORM
* Connecting Spring Boot applications to MySQL
* Handling runtime configuration using feature flags
* Writing clean, testable backend code
* API testing using Postman
* Publishing and documenting projects on GitHub

---

## 🎯 Future Improvements

* Role-based access control (`ROLE_ADMIN`)
* API to create new feature flags
* Audit logging for flag changes
* Swagger / OpenAPI documentation
* Caching layer for faster reads

