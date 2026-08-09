# Vendor Contract Management System

One Next.js frontend and one Spring Boot 3.2 backend backed by MySQL 8. Flyway owns the schema; Hibernate runs in validation mode.

## Prerequisites

- Node.js 18+ and pnpm
- Java 21 and Maven (or the bundled Maven Wrapper)
- MySQL 8+

## Local configuration

Copy `.env.example` to `.env.local` for the frontend and `backend/.env.example` to your local shell/environment. Do not commit either real environment file.

Create a MySQL database named `vendor_contract_db`, then provide `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, and a random 32-byte-or-longer `JWT_SECRET`. Flyway applies `V1` on a new database and follow-on migrations automatically.

```powershell
# frontend, from repository root
pnpm install
pnpm dev

# backend, from backend/
./mvnw.cmd spring-boot:run
```

The frontend uses `NEXT_PUBLIC_API_URL` (default: `http://localhost:8080/api`). The backend uses `PORT` (default: `8080`), `CORS_ALLOWED_ORIGINS`, `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`, and `JWT_EXPIRATION_MS`.

Swagger UI is available at `http://localhost:8080/swagger-ui.html`; the unauthenticated Render health check is `GET /api/health`.

## Architecture

`app/` is the frontend. `backend/` contains the Spring Boot application using controller → service → repository → MySQL layers. It provides vendor, contract, department, dashboard/report, JWT authentication, and audit-log modules.

## Deploying the backend to Render

Create a Render Web Service with `backend` as the Docker build context and use `backend/Dockerfile`. Set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`, and `CORS_ALLOWED_ORIGINS` in Render’s environment settings. Render supplies `PORT`; no database credentials or JWT secrets are stored in this repository. Point the deployed frontend’s `NEXT_PUBLIC_API_URL` at `https://<render-service>.onrender.com/api`.
