# 🎓 Student Management App – Initial Release (v1.0.0)

We are excited to release the **first stable version** of the Student Management App — a **Spring Boot-based backend system** for managing educational institution data efficiently. This release includes full CRUD support for core academic entities.

---

## 🚀 Features Included

### 🧑‍🎓 Student Module
- Create, read, update, and delete student records.
- **Fields:** `studentId`, `studentName`, `address`, `phoneNumber`

### 🧑‍🏫 Teacher Module
- Create, read, update, and delete teacher records.
- **Fields:** `teacherId`, `teacherName`, `address`, `phoneNumber`

### 📘 Course Module
- Create and manage courses with:
- **Fields:**  `courseId`, `courseName`, `syllabus`, `duration`

### 🏫 Batch Module
- Link batches to courses using `@ManyToOne` relationship.
- **Fields:** `batchId`, `batchName`, `courseId`, `startDate`

### 📚 Enrollment *(optional - to be expanded)*
- Link batches, students to enrollments using `@ManyToOne` relationship.
- **Fields:**  `enrollmentId`, `studentId`, `batchId`, `joiningDate`, `fee`

---

## ⚙️ Tech Stack

- **Java 21**
- **Spring Boot 3.5.0**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **REST API** with JSON support

---

## 🔧 Configuration

Make sure your `application.properties` file contains:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/munikiran?createDatabaseIfNotExist=true
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
