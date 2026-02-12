# 📦 Stock & Order Management System (Microservices Architecture)

A backend-based Stock and Order Management System built using Spring Boot microservices architecture.

This system manages product inventory, customer orders, automated stock alerts, and real-time notifications using  
third-party services like Twilio (WhatsApp) and SendGrid (Email).

## 🚀 Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- REST APIs
- Twilio API (WhatsApp Notifications)
- SendGrid API (Email Service)
- Postman (API Testing)

## 🏗 Architecture
This project contains two backend microservices:

### 1️⃣ Order Service
Handles:
- New Customer Creation
- New Order Placement
- Purchase Success Handling
- Bill Generation
- Sending Bill to Customer via WhatsApp

### 2️⃣ Stock Service
Handles:
- Add New Product
- Update Product Details
- Add Stock
- Check Stock Availability
- Generate Stock Report
- Send Stock Report to Admin via Email

Send WhatsApp Alert if:
- Stock is unavailable
- Stock goes below threshold

## 🔄 System Workflow
### 🛒 When Customer Places Order:
- Order is created
- Stock is validated
- If stock available:
- Purchase success
- Bill generated
- Bill sent to customer WhatsApp (Twilio)

### If stock not available:
- Admin notified via WhatsApp

### 📉 When Stock Falls Below Threshold:
- Automatic alert sent to Admin via WhatsApp (Twilio)

### 📊 Stock Report:
Admin receives stock summary via Email (SendGrid)

### 🔗 Third Party Integrations
### 📧 SendGrid
- Used to send stock reports to admin email
- Configured via environment variables

### 📱 Twilio
- Used to send:
- Purchase confirmation to customer
- Low stock alert to admin
- Out-of-stock alert

## ⚙️ Configuration
### Add the following in application.properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/stockdb
spring.datasource.username=root
spring.datasource.password=yourpassword

sendgrid.api.key=YOUR_SENDGRID_API_KEY
twilio.account.sid=YOUR_TWILIO_SID
twilio.auth.token=YOUR_TWILIO_TOKEN
twilio.whatsapp.number=YOUR_TWILIO_WHATSAPP_NUMBER
```
⚠️ Do not commit API keys to GitHub. Use environment variables.

## 🛠 How to Run
```
1️⃣ Clone Repository
git clone https://github.com/your-username/Stock-MS.git
```

```
2️⃣ Run Stock Service
mvn spring-boot:run
```
(Default Port: 8081)

```
3️⃣ Run Order Service
mvn spring-boot:run
```
(Default Port: 8080)


## 🧠 Features Implemented
- Microservices Architecture
- RESTful APIs
- Layered Architecture (Controller → Service → Repository)
- Exception Handling
- Threshold-Based Stock Alert
- Email Notifications
- WhatsApp Notifications
- Bill Generation
- Real-world API Integration
