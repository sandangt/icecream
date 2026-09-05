#!/usr/bin/env bash
# Installs all shared infra (databases, brokers, search, storage, idp, gateway)
# into the `icecream` namespace on the LAN3 (sanlab-k8s-1) cluster.
set -euo pipefail
NS=icecream
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

kubectl apply -f "$ROOT/00-namespace.yaml"

# --- Databases (one bitnami/postgresql release per service that needs it) ---
helm upgrade --install consul-postgresql   bitnami/postgresql -n $NS -f "$ROOT/infra/values-postgresql-consul.yaml"
helm upgrade --install memoir-postgresql   bitnami/postgresql -n $NS -f "$ROOT/infra/values-postgresql-memoir.yaml"
helm upgrade --install keycloak-postgresql bitnami/postgresql -n $NS -f "$ROOT/infra/values-postgresql-keycloak.yaml"
helm upgrade --install echo-mongodb        bitnami/mongodb    -n $NS -f "$ROOT/infra/values-mongodb-echo.yaml"
helm upgrade --install chronos-mongodb     bitnami/mongodb    -n $NS -f "$ROOT/infra/values-mongodb-chronos.yaml"

# --- Shared platform infra ---
helm upgrade --install redis         bitnami/redis         -n $NS -f "$ROOT/infra/values-redis.yaml"
helm upgrade --install rabbitmq      bitnami/rabbitmq      -n $NS -f "$ROOT/infra/values-rabbitmq.yaml"
helm upgrade --install kafka         bitnami/kafka         -n $NS -f "$ROOT/infra/values-kafka.yaml"
helm upgrade --install elasticsearch bitnami/elasticsearch -n $NS -f "$ROOT/infra/values-elasticsearch.yaml"
helm upgrade --install minio         bitnami/minio         -n $NS -f "$ROOT/infra/values-minio.yaml"
helm upgrade --install keycloak      bitnami/keycloak      -n $NS -f "$ROOT/infra/values-keycloak.yaml"

# --- API Gateway ---
helm upgrade --install apisix apisix/apisix -n $NS -f "$ROOT/infra/values-apisix.yaml"

# --- Kafka Connect + Debezium (CDC: consul-postgresql -> kafka -> elasticsearch) ---
kubectl apply -n $NS -f "$ROOT/infra/kafka-connect/deployment.yaml"
kubectl apply -n $NS -f "$ROOT/infra/kafka-connect/service.yaml"
kubectl apply -n $NS -f "$ROOT/infra/kafka-connect/register-debezium-connector-job.yaml"

echo "Infra install triggered. Watch rollout with: kubectl -n $NS get pods -w"
