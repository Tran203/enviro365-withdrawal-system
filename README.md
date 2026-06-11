# Enviro365 Investments — Withdrawal Management System

Junior Developer Assessment | eTalente 2026

## Overview
A full-stack withdrawal management system built for Enviro365 Investments. 
Investors can view their portfolios, submit withdrawal notices, and download CSV reports.

---

## Tech Stack
- **Backend:** Java 21, Spring Boot 3.5.14
- **Database:** H2 In-Memory Database
- **Frontend:** HTML, CSS, Vanilla JavaScript
- **Build Tool:** Maven

---

## How to Run

### Prerequisites
- Java 21
- Maven 3.9+

### Steps
1. Clone the repository:
   ```
   git clone https://github.com/Tran203/enviro365-withdrawal-system
   ```
2. Navigate to project folder:
   ```
   cd themba
   ```
3. Run the application:
   ```
   mvn spring-boot:run
   ```
4. Open the frontend:
   - Navigate to the `frontend` folder
   - Open `index.html` in your browser

5. Access H2 Console (optional):
   ```
   http://localhost:8080/h2-console
   
   JDBC URL: jdbc:h2:mem:enviro365db
   Username: sa
   Password: (leave empty)
   ```

---

## API Documentation

### Investors
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/investors` | Get all investors |
| GET | `/api/investors/{id}` | Get investor by ID |

### Investment Products
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products/investor/{id}` | Get products by investor |
| GET | `/api/products/{id}` | Get product by ID |

### Withdrawals
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/withdrawals` | Submit withdrawal notice |
| GET | `/api/withdrawals/investor/{id}` | Get withdrawal history |
| GET | `/api/withdrawals/export/{id}` | Export withdrawals as CSV |


---

## Business Rules
- Retirement withdrawals only allowed if investor age is over 65
- Withdrawal amount must not exceed available balance
- Withdrawal amount must not exceed 90% of balance
- All validation errors return descriptive messages to the user

---


## Test Data (Pre-loaded)
| Investor | Age | Products |
|----------|-----|----------|
| Themba Mabena | 67 | Retirement Fund (R500,000), Savings Plan (R150,000) |
| Sarah Johnson | 35 | Savings Account (R80,000), Retirement Fund (R200,000) |
| James Nkosi | 70 | Retirement Fund (R750,000) |

---

## AI Usage Disclosure
This project was developed with assistance from Claude AI (Anthropic). 
Claude assisted with:
- Spring Boot project structure and configuration
- README documentation


---

## Project Structure
```
themba/
├── frontend/
│   └── index.html
├── src/main/java/com/enviro/assessment/junior/themba/
│   ├── controller/
│   ├── dto/
│   ├── exception/
│   ├── model/
│   ├── repository/
│   ├── service/
│   └── ThembaApplication.java
├── src/main/resources/
│   ├── application.properties
│   └── data.sql
└── pom.xml
```

---
