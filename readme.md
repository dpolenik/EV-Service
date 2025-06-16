# EV‑API – Reactive Spring Boot Proof‑of‑Concept

A lightweight **Spring Boot 3.5 / WebFlux / R2DBC** service that exposes CRUD and batch‑mutation endpoints for Electric‑Vehicle data (Vehicles & Sales). Built for fast local experimentation: in‑memory **R2DBC‑H2** (PostgreSQL dialect), no external dependencies, and container‑ready via Cloud‑Native Buildpacks.

![alt text](/img/img.png "Flow of Data")
---

## ✨ Features

| Area           | Tech                              | Notes                                        |
| -------------- | --------------------------------- | -------------------------------------------- |
| Reactive stack | **Spring WebFlux + R2DBC**        | Non‑blocking I/O end‑to‑end.                 |
| Persistence    | **R2DBC‑H2 (PostgreSQL mode)**    | In‑memory, schema auto‑created at startup.   |
| Metrics        | Micrometer @Timed / @Counted      | Exposed at `/actuator/metrics`.              |
| Logs           | DEBUG SQL logging                 | Shows each compiled query + bind parameters. |
| Tests          | Postman collection                | Covers all Vehicle routes.                   |
| Container      | **Buildpacks** (`bootBuildImage`) | No Dockerfile required.                      |

---

## 🛠️ Prerequisites

* **JDK 17+** (Temurin / Zulu)
* **Gradle 8** wrapper provided (no local install needed) *or* Maven 3.9+
* **Docker** daemon (for container build/run)

---

## 🚀 Running Locally (IDE / CLI)

```bash
./gradlew bootRun    # http://localhost:8080
```

### Hot reload (IntelliJ)

1. Edit code → *Build ▸ Recompile* (⌘F9 / Ctrl‑F9)
2. Spring Devtools restarts context automatically

---

## 📦 Containerize & Run with Buildpacks (no Dockerfile)

```bash
# 1) Build the OCI image locally (tagged ev-api:latest)
./gradlew bootBuildImage

# 2) Run the container
docker run --name ev-api -p 8080:8080 ev-api:latest
```

> Boot’s Buildpacks detect Java 17, layer an optimized JRE and fat‑jar, then place the image in your local Docker cache.

### Using Docker Compose

```yaml
services:
  ev-api:
    image: ev-api:latest   # produced by bootBuildImage
    ports:
      - "8080:8080"
    restart: unless-stopped
```

```bash
docker compose up -d
```

---

## 🔌 API Quick‑Start (cURL)

```bash
# Create a vehicle
curl --location 'http://localhost:8080/vehicles' \
--header 'Content-Type: application/json' \
--data '{
  "make": "TESLA",
  "model": "MODEL 3",
  "modelYear": "2020",
  "evType": "Battery Electric Vehicle (BEV)",
  "cafvEligibility": "Clean Alternative Fuel Vehicle Eligible",
  "electricRange": 220,
  "baseMsrp": 0.00
}'

# List all vehicles
curl http://localhost:8080/vehicles | jq
```

Full request set lives in **EV API Smoke Tests.postman_collection.json**.

---

## 📊 Observability Endpoints

| Path                               | Description                                     |
| ---------------------------------- | ----------------------------------------------- |
| `/actuator/health`                 | Liveness / readiness                            |
| `/actuator/metrics`                | Micrometer metrics registry                     |
| `/actuator/metrics/vehicle.create` | Counter for create ops                          |
| `/actuator/prometheus`             | Prometheus scrape (enable in `application.yml`) |

---

## 🧩 Project Structure

```
src/main/java/com/example/evapi/
├── controller   # WebFlux REST controllers
├── model        # R2DBC entities + projections (Vehicle, Sale, IVehicle)
├── repository   # ReactiveCrudRepository interfaces
├── service      # Reactive services with metrics annotations
└── EvApiApplication.java
```

---

## ⚙️ Build Cheatsheet

| Command                    | Purpose                         |
| -------------------------- | ------------------------------- |
| `./gradlew test`           | (Add tests TBD)                 |
| `./gradlew bootRun`        | Run locally                     |
| `./gradlew bootJar`        | Build fat‑jar in `build/libs`   |
| `./gradlew bootBuildImage` | Buildpacks → local Docker image |

---

## 📂 License & Attribution

> For interview / PoC use only. Dataset: *Electric Vehicle Population Data* © State of Washington (open data).
