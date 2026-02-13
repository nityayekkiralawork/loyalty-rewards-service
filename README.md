# Rewards App

A simple **Spring Boot REST API** that calculates customer reward points based on their monthly transactions.

## Features

- Calculates reward points per transaction:
  - $0–50: 0 points
  - $51–100: 1 point per $1 over $50
  - Above $100: 50 points + 2 points per $1 over $100
- Aggregates points per month for each customer
- Supports filtering by last N months**
- Global exception handling for smooth error messages

## Requirements

- Java 17+
- Maven 3+ (Maven Wrapper included)
- H2 database (in-memory for testing)


## How to Run

1. Clone the repository:
git clone https://github.com/<your-username>/loyalty-rewards-service.git

cd loyalty-rewards-service

API endpoints (examples):

GET /rewards/{customerId}?months=3
POST /transactions
Replace customerId and months as needed.

Notes
rewards-app.log is ignored and will not be pushed to GitHub.
Exception messages include customerId for easier debugging.
