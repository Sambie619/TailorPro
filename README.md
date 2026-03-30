# 🧵 TailorPro – Smart Tailoring Management System

## 🚀 Overview

TailorPro is a full-stack ERP-style web application designed to streamline tailoring shop operations including customer management, order tracking, billing, and payment handling.

Built with **Spring Boot + React**, the system provides real-time workflow tracking and financial management.

---

## 🛠️ Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security (JWT Authentication)
* Spring Data JPA (Hibernate)
* MySQL
* Maven

### Frontend

* React.js
* Axios
* Tailwind CSS

---

## 🔐 Features

### 👤 Authentication

* JWT-based login system
* Protected routes
* Secure logout functionality

---

### 👥 Customer Management

* Add / Update / Delete customers
* Store contact details and measurements

---

### 📦 Order Management

* Create and manage tailoring orders
* Track order lifecycle:

  * PENDING → CUTTING → STITCHING → TRIAL → COMPLETED → DELIVERED
* Auto-update delivered date
* Filter orders by status

---

### 📊 Dashboard

* Total customers
* Active & completed orders
* Orders due today
* Revenue tracking

---

### 💰 Billing & Payments

* Generate bills linked to orders
* Support for partial payments
* Auto-calculation of:

  * Total amount
  * Advance paid
  * Remaining balance
* Dynamic payment status:

  * PENDING
  * PARTIAL
  * PAID
* Add payments incrementally
* Mark bills as fully paid

---

## 🔄 Workflow Highlights

* Real-time status updates for orders
* Automatic delivery tracking
* Financial logic-driven payment status
* Seamless frontend-backend integration via REST APIs

---

## 📂 Project Structure

### Backend (Spring Boot)

```
controller/
service/
repository/
entity/
dto/
config/
```

### Frontend (React)

```
pages/
components/
api/
```

---

## ⚙️ Setup Instructions

### 🔹 Backend

1. Clone repository
2. Open in STS / IntelliJ
3. Configure MySQL in `application.properties`
4. Run application

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tailorpro
spring.datasource.username=root
spring.datasource.password=yourpassword
```

---

### 🔹 Frontend

```bash
cd tailorpro-frontend
npm install
npm start
```

---

## 🔗 API Endpoints (Sample)

| Method | Endpoint                    | Description         |
| ------ | --------------------------- | ------------------- |
| POST   | /api/auth/login             | Login               |
| GET    | /api/customers              | Get customers       |
| POST   | /api/orders                 | Create order        |
| PUT    | /api/orders/{id}/status     | Update order status |
| POST   | /api/bills                  | Create bill         |
| PUT    | /api/bills/{id}/pay         | Mark bill paid      |
| PUT    | /api/bills/{id}/add-payment | Add payment         |

---

## 💡 Key Highlights

* 🔐 Stateless JWT authentication
* 📦 Workflow-based order system
* 💰 Real-time billing and payment tracking
* ⚡ RESTful API architecture
* 🎯 Clean UI with Tailwind CSS

---

## 📸 Screenshots

(Add screenshots of Dashboard, Orders, Billing UI here)

---

## 📌 Future Enhancements

* Charts & analytics dashboard
* Invoice PDF generation
* SMS/Email notifications
* Multi-user role system

---

## 👨‍💻 Author

Developed by [Your Name]

---

## ⭐ If you like this project, give it a star!

