# CarbonX Java Backend

Spring Boot 4 + JPA API that replaces the Node/Express mock server. Same routes, SQL-backed data.

## Requirements

- Java 21+ (Java 23 works)
- Maven Wrapper included (`mvnw.cmd`) — no global Maven install needed
- Optional: MySQL 8+ for the `mysql` profile

## Quick start (H2 file database — no MySQL install)

```powershell
cd backend-java
.\mvnw.cmd spring-boot:run
```

API: http://localhost:5000  
H2 console: http://localhost:5000/h2-console  
- JDBC URL: `jdbc:h2:file:./data/carbonx`  
- User: `sa`  
- Password: (empty)

## MySQL mode

1. Install MySQL and set root password (default in config: `root`).
2. Edit `src/main/resources/application-mysql.properties` if your username/password differ.
3. Run:

```powershell
cd backend-java
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=mysql"
```

Optional manual schema: `sql/schema.sql` (Hibernate also creates tables automatically).

## API endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/dashboard` | Dashboard stats |
| GET | `/api/projects` | Project list |
| GET | `/api/transactions` | Transactions |
| GET | `/api/monitoring` | Monitoring + sensor readings |
| GET | `/api/admin` | Users + settings |
| POST | `/api/marketplace/buy` | Buy credits `{ "project", "credits", "price" }` |
| POST | `/api/marketplace/sell` | Sell credits `{ "project", "credits", "price" }` |

### Example

```powershell
Invoke-RestMethod http://localhost:5000/api/dashboard
Invoke-RestMethod http://localhost:5000/api/projects
Invoke-RestMethod -Method Post -Uri http://localhost:5000/api/marketplace/buy -ContentType "application/json" -Body '{"project":"Mangrove Restoration","credits":10,"price":25.5}'
```

## Project layout

```
backend-java/
  src/main/java/com/carbonx/
    controller/   REST API
    service/      business logic
    model/        JPA entities
    repository/   Spring Data JPA
    dto/          request bodies
    config/       CORS, seed data, errors
  sql/schema.sql
```

Seed data matches the old `backend/data.json` values on first run.
