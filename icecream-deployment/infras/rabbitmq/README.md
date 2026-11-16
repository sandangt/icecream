# ConfigMap and Secret existing name:
- `auth.existingSecret`: icecream-credentials
- `config.existingConfigMap`: icecream-configmaps
# `auth.existingSecret` required fields:
```env
RABBITMQ_PASSWORD=password1234
```
# `config.existingConfigMap` required fields:
```env
RABBITMQ_USERNAME=admin
```
