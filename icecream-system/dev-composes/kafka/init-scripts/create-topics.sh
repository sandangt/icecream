#!/usr/bin/env bash
set -euo pipefail

BROKER="${KAFKA_BROKER:-kafka:29092}"
PARTITIONS="${TOPIC_PARTITIONS:-1}"
REPLICATION_FACTOR="${TOPIC_REPLICATION_FACTOR:-1}"
RETRY_INTERVAL="${TOPIC_INIT_RETRY_INTERVAL:-5}"
MAX_ATTEMPTS="${TOPIC_INIT_MAX_ATTEMPTS:-60}"

echo "Waiting for Kafka at ${BROKER}..."
attempt=0
until kafka-topics --bootstrap-server "${BROKER}" --list >/dev/null 2>&1; do
  if [[ "${attempt}" -ge "${MAX_ATTEMPTS}" ]]; then
    echo "Kafka did not become ready in time." >&2
    exit 1
  fi
  attempt=$((attempt + 1))
  sleep "${RETRY_INTERVAL}"
done

topics=(
  "icecream.consul.product"
  "icecream.consul.category"
  "icecream.consul.image"
  "icecream.consul.product_category"
  "icecream.consul.product_image"
  "icecream.consul.feedback"
  "icecream.consul.stock"
  "icecream.conflux.enriched.product"
)

for topic in "${topics[@]}"; do
  kafka-topics \
    --bootstrap-server "${BROKER}" \
    --create \
    --if-not-exists \
    --topic "${topic}" \
    --partitions "${PARTITIONS}" \
    --replication-factor "${REPLICATION_FACTOR}" \
    --config cleanup.policy=compact \
    --config min.cleanable.dirty.ratio=0.01 \
    --config segment.ms=600000
done

echo "Kafka topics are ready."
