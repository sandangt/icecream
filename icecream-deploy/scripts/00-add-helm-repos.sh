#!/usr/bin/env bash
set -euo pipefail
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo add apisix https://charts.apiseven.com
helm repo update
