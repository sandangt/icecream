# Icecream shop

## Description

Not so monolithic e-commerce system

---

## 🗺️ High‑Level System Overview

![Architecture Diagram](/assets/architecture.png)

At a glance:

* **Transactional Core** backed by PostgreSQL
* **Asynchronous backbone** using message queues
* **CDC‑driven data propagation**
* **Dedicated services** for scheduling, auditing, notifications, and enrichment
* **Optimized read models** (search engine, full‑text search, cache)
* **Clear separation of concerns** between frontend, backoffice, and backend services

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
