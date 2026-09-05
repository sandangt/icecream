#!/usr/bin/env bash
# Installs codex/consul/memoir/echo/chronos/conflux/horus (all via the
# reusable spring-service chart) + storefront, into the icecream namespace.
set -euo pipefail
NS=icecream
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

for svc in codex consul memoir echo chronos conflux horus; do
  helm upgrade --install "$svc" "$ROOT/charts/spring-service" \
    -n $NS -f "$ROOT/environments/lan3/values-$svc.yaml"
done

helm upgrade --install storefront "$ROOT/charts/storefront" \
  -n $NS -f "$ROOT/environments/lan3/values-storefront.yaml"

kubectl apply -n $NS -f "$ROOT/gateway/apisixroute-storefront.yaml"
kubectl apply -n $NS -f "$ROOT/gateway/apisixroute-api.yaml"

echo "Services deployed. Check: kubectl -n $NS get pods,svc"
echo "Get APISIX LoadBalancer (MetalLB) VIP: kubectl -n $NS get svc apisix-gateway"
