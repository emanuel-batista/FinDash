# FinDash

### FinDash Project: Financial Assets Simulation Platform

FinDash is a full-stack platform for simulating asset trading (stocks, cryptocurrencies), built to demonstrate a modern, robust, and scalable microservices architecture.

The main goal of this project is not to create a financial product, but to serve as an advanced technical portfolio, proving proficiency in distributed systems design, event-driven architecture (EDA), and DevOps practices (IaC).

Using Java (Spring Boot) on the backend and React on the frontend, the project is fully containerized with Docker and operates asynchronously with RabbitMQ.

### Core Architecture Concepts

* **Microservices:** The system is split into independent services, each with its own single responsibility:
    * **`account-service` (Account Service):** Manages users, authentication (register/login), and each user's portfolio (wallet).
    * **`transaction-service` (Transaction Service):** Processes and stores the history of buy and sell orders.
    * **`market-data-service` (Market Data Service):** Provides "live" asset prices.

* **Event-Driven Architecture (EDA):** Instead of direct coupling (where one service calls another via REST and waits), services communicate asynchronously through events on **RabbitMQ**. When a transaction is approved, the `transaction-service` publishes an event (`order.processed`), and the `account-service` listens to that event to update the user's balance and portfolio. This ensures resilience (if the wallet service is offline, the order is not lost) and scalability.

* **Security (Spring Security):** Authentication will be managed by the `account-service` using **JSON Web Tokens (JWTs)**. Spring Security is configured to protect specific endpoints while exposing public routes (such as `/auth/register` and the Swagger documentation).

* **Infrastructure as Code (IaC & DevOps):** The project is developed and operated in a cloud environment (GitHub Codespaces). All necessary infrastructure (PostgreSQL, RabbitMQ, and the microservices themselves) is defined as code using **Docker Compose** and the `.devcontainer.json` file, ensuring a reproducible development environment with a single click.

* **Documentation and Quality:** The API is documented in real time with **Swagger (OpenAPI)**, and the integrity of business flows will be validated with integration tests (via **Testcontainers**).

### Current Features (Work In Progress)

* **Account Service (`account-service`):**
    * Microservice structure using Spring Boot.
    * Connection to PostgreSQL (containerized).
    * Functional `POST /auth/register` endpoint with:
        * Existing email validation.
        * Secure password hashing with **BCrypt** (`PasswordEncoder`).
    * Security configuration (`SecurityConfig`) to expose public endpoints.
    * CORS configuration to allow browser access.
    * Automatic API documentation with **Swagger UI**.

### Future Features (Next Steps)

1.  **Access Token Generation (JWT):**
    * Implement the **JJWT** library to generate a token at the `POST /auth/login` endpoint.

2.  **Market Data Service (`market-data-service`):**
    * Create the second microservice to provide asset prices (initially simulated, later fetching from a public API).

3.  **Transaction Service (`transaction-service`):**
    * Create the third microservice to receive and validate buy (`POST /orders/buy`) and sell (`POST /orders/sell`) orders.

4.  **Asynchronous Order Flow (RabbitMQ):**
    * Integrate services with RabbitMQ to validate user balances and process orders in a decoupled way (the heart of the EDA architecture).

5.  **Portfolio Management (Wallet):**
    * Implement logic in the `account-service` to listen for transaction events and update the user's wallet (cash balance and asset quantities).

6.  **React Dashboard (Frontend):**
    * Build the user interface for login, market viewing, portfolio inspection, and executing transactions.

7.  **Integration Tests (Testcontainers):**
    * Write tests that validate the complete flow (e.g., API -> RabbitMQ -> Database) in a real Docker environment, ensuring system robustness.