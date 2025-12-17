#!/usr/bin/env bash
set -euo pipefail

/etc/confluent/docker/run &
kafka_pid=$!

cleanup() {
  kill -TERM "$kafka_pid"
  wait "$kafka_pid"
}
trap cleanup SIGINT SIGTERM

/opt/kafka/init-scripts/create-topics.sh

wait "$kafka_pid"
