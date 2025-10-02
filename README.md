# Bakery Payment Service

## Overview
Processes payments and refunds, manages transaction status with third-party payment gateways.

## Features
- Payment gateway integration
- Payment confirmation and refund handling
- Secure payment processing workflows

## Dependencies
- Spring WebFlux
- Spring Data JPA
- Spring Security
- Payment SDKs (e.g., Stripe, PayPal)
- Spring Boot Actuator

## Key Endpoints
- `/api/payments/`
- `/api/payments/refund`

## Running
./gradlew bootRun

Runs on port 8085 by default.

## Documentation
Swagger UI: `http://localhost:8085/swagger-ui.html`

---