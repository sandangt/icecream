# ConfigMap and Secret existing name:
- `auth.existingSecret`: icecream-credentials
- `config.existingConfigMap`: icecream-configmaps
# `auth.existingSecret` required fields:
```env
POSTGRESQL_PASSWORD=password1234
```
# `config.existingConfigMap` required fields:
```env
POSTGRESQL_USER=admin
POSTGRESQL_CONSUL_DB=consul
POSTGRESQL_MEMOIR_DB=memoir
POSTGRESQL_HORUS_DB=horus
POSTGRESQL_IDENTITY_DB=identity
POSTGRESQL_PAYMENT_DB=payment
POSTGRES_MULTIPLE_DATABASES=${POSTGRESQL_CONSUL_DB},${POSTGRESQL_MEMOIR_DB},${POSTGRESQL_IDENTITY_DB}
```
