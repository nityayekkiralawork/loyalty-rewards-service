# Loyalty Rewards Service App

A simple **Spring Boot REST API** that calculates customer reward points based on their monthly transactions.

## Features

- Calculates reward points per transaction:
  - $0–50: 0 points
  - $51–100: 1 point per $1 over $50
  - Above $100: 50 points + 2 points per $1 over $100
- Aggregates points per month for each customer
- Supports filtering by last N months
- Global exception handling for smooth error messages

## Requirements

- Java 17+
- Maven 3+ (Maven Wrapper included, need not explicitly install)
- H2 database (in-memory for testing, need not explicitly setup)


## How to Run

Clone the repository:
git clone https://github.com/nityayekkiralawork/loyalty-rewards-service.git

cd loyalty-rewards-service

**Run using maven wrapper:**

./mvnw clean install

./mvnw spring-boot:run

**Run from IDE:**

Load Maven project & wait for dependencies to load.

Navigate to LoyaltyRewardsServiceApplication.java and click Run.

**API endpoints:**

applictaion runs on: http://localhost:8080

h2 Db console: http://localhost:8080/h2-console

GET http://localhost:8080/api/rewards/customer/{customerId}?months=3

**months** **is optional & if not provided will be defaulted to 3**

Replace customerId and months as needed.


## Sample Requests & Responses

### Get Customer Rewards (Default Months = 3)

**Request**
```
GET http://localhost:8080/api/rewards/customer/101
```

**Response – 200 OK**
```json
{
  "customerId": 101,
  "monthlyRewards": [
    {
      "monthYear": "December 2025",
      "points": 50
    },
    {
      "monthYear": "January 2026",
      "points": 20
    },
    {
      "monthYear": "February 2026",
      "points": 180
    }
  ],
  "totalPoints": 250
}
```

---

###  Get Customer Rewards (Custom Months)

**Request**
```
GET http://localhost:8080/api/rewards/customer/102?months=24
```

**Response – 200 OK**
```json
{
  "customerId": 102,
  "monthlyRewards": [
    {
      "monthYear": "February 2025",
      "points": 150
    },
    {
      "monthYear": "August 2025",
      "points": 90
    },
    {
      "monthYear": "January 2026",
      "points": 250
    },
    {
      "monthYear": "February 2026",
      "points": 180
    }
  ],
  "totalPoints": 670
}
```

---

### ❌ No Transactions Found

**Request**
```
GET http://localhost:8080/api/rewards/customer/10999?months=24
```

**Response – 400 Bad Request**
```
No transactions found for customerId=10999
```

---

### Invalid Customer ID

**Request**
```
GET http://localhost:8080/api/rewards/customer/ABC?months=24
```

**Response – 400 Bad Request**
```
Method parameter 'customerId': Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: "ABC"
```

---

### Invalid Months Parameter

**Request**
```
GET http://localhost:8080/api/rewards/customer/101000?months=24/0
```

**Response – 400 Bad Request**
```
Method parameter 'months': Failed to convert value of type 'java.lang.String' to required type 'int'; For input string: "24/0"
```

---

### Notes

- If `months` is not provided, the API defaults to **3 months**
- Rewards are calculated **month-wise**
- `totalPoints` represents the sum of all monthly rewards
- Invalid inputs return **400 Bad Request**
- rewards-app.log is ignored and will not be pushed to GitHub
- Exception messages include customerId for easier debugging

