# ConfigMap and Secret existing name:
- `auth.existingSecret`: icecream-credentials
- `config.existingConfigMap`: icecream-configmaps
# `auth.existingSecret` required fields:
```env
MONGODB_PASSWORD=password1234
```
# `config.existingConfigMap` required fields:
```env
MONGODB_USER=admin
MONGODB_CHRONOS_DB=chronos
MONGODB_ECHO_DB=echo
MONGO_MULTIPLE_DATABASES=${MONGODB_ECHO_DB},${MONGODB_CHRONOS_DB}
```
