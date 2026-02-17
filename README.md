# Rate Aggregator Service

Simple Spring Boot service that consumes the Frankfurter public exchange rate API and exposes normalized finance data endpoints.

---

## ✨ Features

* Fetch **latest exchange rates**
* Fetch **historical rates** (date range)
* Fetch **supported currencies**
* In-memory data store
* Startup data loader
* Global exception handling

---

## 🛠 Tech Stack

* Java 11+
* Spring Boot
* RestTemplate
* Lombok
* JUnit 5
* Mockito

---

## 🌐 External API

Data source:

```
https://api.frankfurter.dev
```

Endpoints used:

* `/v1/latest`
* `/v1/{dateFrom}..{dateTo}`
* `/currencies`

---

## ⚙️ Configuration

Example `application-local.yml`:

```yaml
spring:
  application:
    name: rate-aggregator

server:
  port: 8080
  servlet:
    context-path: /api/finance

frankfurter:
  base-url: https://api.frankfurter.dev
  api-version: /v1
  endpoints:
    latest: /latest
    historical: ""
    currencies: /currencies
```

---

## 🚀 Running the App

```bash
mvn spring-boot:run
```

Or

```bash
mvn clean package
java -jar target/rate-aggregator.jar
```

---

## 📡 API Endpoints

Base URL:

```
http://localhost:8080/api/finance
```

### ✅ Get Finance Data

```
GET /data/{resourceType}
```

Supported resource types:

| Resource Type          | Description                |
| ---------------------- | -------------------------- |
| `latest_idr_rates`     | Latest IDR → USD rates     |
| `historical_idr_rates` | Historical IDR → USD rates |
| `supported_currencies` | List of currencies         |

---

## 📦 Example Requests

### Latest Rates

```
GET /data/latest_idr_rates
```

### Historical Rates

```
GET /data/historical_idr_rates
```

### Supported Currencies

```
GET /data/supported_currencies
```

---

## ❌ Error Handling

Invalid resource type:

```json
{
  "timestamp": "2026-02-13T20:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid resource type"
}
```

---

## 🧪 Testing

Run tests:

```bash
mvn test
```

Test coverage includes:

* Controller tests (MockMvc)
* API client tests (Mockito)
* Startup loader tests
* Fetcher tests

---

## 📁 Project Structure

```
client/
config/
constant/
controller/
dto/
exception/
filter/
runner/
service/
store/
```
