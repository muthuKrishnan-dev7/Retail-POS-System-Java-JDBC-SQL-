# Retail POS System (Java, JDBC, PostgreSQL)

Retail POS System is a console-based billing application developed using **Java**, **JDBC**, and **PostgreSQL**. It supports secure **admin login authentication**, product billing, automatic bill generation, and automatic stock reduction after purchase. Billing details are stored in the database for tracking and sales analysis.

## Features
- Admin Login Authentication
- Product Billing System
- Auto Bill Generation
- Automatic Stock Reduction
- Stores bills in `bills` table
- Stores purchased products in `bill_items` table
- Helps identify most-selling and least-selling products

## Technologies Used
- Java
- JDBC
- PostgreSQL
- SQL

## Database Tables
- `admin` (username, password)
- `products`
- `bills`
- `bill_items`

## How to Run
1. Clone or download the project
2. Import into Eclipse / IntelliJ
3. Create required tables in PostgreSQL
4. Update DB credentials in `DBConnection.java`
5. Run `MainApp.java`

## Demo Video
(Add your YouTube link here)

## Author
Muthu Krishnan R
