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
Load Maven project & wait for dependencies to load
Navigate to LoyaltyRewardsServiceApplication.java and click Run.

**API endpoints:**
applictaion runs on: http://localhost:8080
h2 Db console: http://localhost:8080/h2-console
GET htpp://localhost:8080/api/rewards/customer/{customerId}?months=3
**months** **is optional & if not provided will be defaulted to 3**
Replace customerId and months as needed.

Notes
rewards-app.log is ignored and will not be pushed to GitHub.
Exception messages include customerId for easier debugging.
