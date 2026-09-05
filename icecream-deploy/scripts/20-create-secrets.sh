#!/usr/bin/env bash
# Fill in real values via env vars or edit this file locally (DO NOT commit
# real secrets - this file is meant to be run once per cluster, not stored
# with plaintext passwords in git).
set -euo pipefail
NS=icecream

kubectl -n $NS create secret generic consul-postgresql-secret \
  --from-literal=postgres-password="${CONSUL_PG_ADMIN_PW:?set me}" \
  --from-literal=password="${CONSUL_PG_APP_PW:?set me}" \
  --dry-run=client -o yaml | kubectl apply -f -

kubectl -n $NS create secret generic consul-db-secret \
  --from-literal=SPRING_DATASOURCE_USERNAME=consul \
  --from-literal=SPRING_DATASOURCE_PASSWORD="${CONSUL_PG_APP_PW:?set me}" \
  --from-literal=SPRING_RABBITMQ_USERNAME=icecream \
  --from-literal=SPRING_RABBITMQ_PASSWORD="${RABBITMQ_PW:?set me}" \
  --dry-run=client -o yaml | kubectl apply -f -

# ... repeat the same pattern for memoir-*, echo-*, chronos-*, keycloak-*,
#     redis-secret, rabbitmq-secret, minio-secret, storefront-secret.
# See secrets/README.md for the full list of expected secret names/keys.
echo "NOTE: this script only shows the pattern for 'consul'. Copy the block"
echo "above for every secret listed in secrets/README.md before installing services."
