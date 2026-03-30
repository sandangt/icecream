# Icecream shop

## Description

Not so monolithic e-commerce system

---

## 🗺️ High‑Level System Overview

![Architecture Diagram](/readme-assets/architecture.png)

At a glance:

* **Transactional Core** backed by PostgreSQL
* **Asynchronous backbone** using message queues
* **CDC‑driven data propagation**
* **Dedicated services** for scheduling, auditing, notifications, and enrichment
* **Optimized read models** (search engine, full‑text search, cache)
* **Clear separation of concerns** between frontend, backoffice, and backend services

---

## Observability architecture

![Observability Architecture Diagram](/readme-assets/observability-architecture.png)

1. **Data Sources**

    Six core services — `codex`, `consul`, `conflux`, `memoir`, `chronos`, and `echo` — each run an OpenTelemetry Agent (otel-agent) that emits telemetry data tagged with their respective service_name (e.g., icecream-codex).

1. **Centralized Collection**

    All agents send data to a single OpenTelemetry Collector (otel-collector), which acts as the ingestion and processing hub.

1. **Processing & Exporting**

    The collector exports data to four specialized backends:
    - `Prometheus` + `Mimir` → Metrics
    - `Loki` → Logs
    - `Tempo` → Distributed Traces

1. **Long-Term Storage**

    An Object Storage layer (MinIO) is used for durable, scalable retention of raw or processed telemetry data, feeding into the processing systems as needed.

1. **Unified Visualization**

    All telemetry streams converge in Grafana, providing a single-pane-of-glass view for dashboards, alerts, and correlation across metrics, logs, and traces.

---

## Languages & frameworks

- Java 25.0 | Spring boot 3.5.x & Spring cloud 2025.1.x | Vaadin
- ReactJS 19.x.x | NextJS 15.4.x

---
## 🧩 Modules

### 🧠 Codex — Central Configuration Service

* Centralized configuration management
* Runtime configuration without redeploys
* Supports dynamic feature flags and environment‑based overrides

### 💼 Consul — Transactional Core Service

* Owns **business‑critical write operations**
* Strong consistency guarantees
* Exposes synchronous APIs for critical paths
* Publishes domain events for downstream consumers

### ⏱ Chronos — Scheduler Service

* Handles delayed and scheduled tasks
* Time‑based workflows (reminders, retries, expirations)
* Decoupled from business logic via events

### 🔔 Echo — Asynchronous Notification Service

* Email / webhook / external notifications
* Fully event‑driven
* Resilient to downstream failures

### 📝 Memoir — Audit Log Service

* Immutable audit trail
* Consumes domain events
* Optimized for full‑text search and compliance use cases

### 🔄 Conflux — Consumption & Enrichment Service

* Transforms raw events into enriched read models
* Aggregates data for search and analytics
* Ideal place for **CQRS read‑side logic**

### 🏢 Horus — Backoffice Application

* Internal admin and operational UI
* Secure access via IDP
* Built for support, ops, and business teams

---

## 🔄 Communication Patterns

- 🔴 Synchronous (REST and RSocket)
- 🔵 Asynchronous (Using message broker RabbitMQ, Kafka)

---

## 🧱 Data Strategy

### 🐘 Transactional Database (PostgreSQL)

- Source of truth
- Strict ownership per service
- ACID guarantees

### 🔍 Search Engine

- Optimized for complex queries
- Fed via CDC, not direct writes
- Zero impact on transactional latency

### ⚡ Cache (Redis)

- Hot data and session caching
- Read‑heavy optimization

### 🗂 Object Storage

- Large binary assets
- Event‑referenced, not embedded

---

## 🔐 Security & Identity

- Central **Identity Provider (IDP)** with **Keycloak**
- Token‑based authentication

---

## Infrastructure summary

- **Keycloak** IDP
- **PostgreSQL 16.8** transactional databases
- **MongoDB 8.0** transactional databases
- **Redis 7.4** cache
- **Minio** object storage
- **RabbitMQ** task queue broker
- **Kafka** message broker and CDC pipelines
- **Debezium connect** CDC connector  
- **Elasticsearch** search engine 
