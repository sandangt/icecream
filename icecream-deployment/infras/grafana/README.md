# ConfigMap and Secret existing name:
- `auth.existingSecret`: icecream-credentials
- `config.existingConfigMap`: icecream-configmaps
# `auth.existingSecret` required fields:
```env
GRAFANA_ADMIN_PASSWORD=password123
```
# `config.existingConfigMap` required fields:
```env
GRAFANA_ADMIN_USER=admin
```
