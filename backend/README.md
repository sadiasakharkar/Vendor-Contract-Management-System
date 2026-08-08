# Vendor Contract Management API

Spring Boot 3.2 / Java 17 API located alongside the existing frontend.

## Run

1. Create MySQL database credentials (defaults: `root` / `root`).
2. Set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, and a strong `JWT_SECRET` as appropriate.
3. Run `mvn spring-boot:run` from this directory.

Flyway provisions the schema and baseline roles/users. Swagger is available at `/swagger-ui.html`; health is `/api/health`.

Default accounts use `admin123`, `manager123`, and `viewer123` respectively.
