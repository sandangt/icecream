# ConfigMap and Secret existing name:
- `auth.existingSecret`: icecream-credentials
- `config.existingConfigMap`: icecream-configmaps
# `auth.existingSecret` required fields:
```env
STORAGE_ACCESS_KEY=1234
STORAGE_SECRET_KEY=1234
AUTH_SECRET=1234
AUTH_KEYCLOAK_ID=1234
AUTH_KEYCLOAK_SECRET=1234
AUTH_KEYCLOAK_ISSUER=1234
```
# `config.existingConfigMap` required fields:
```env
STOREFRONT_PORT=1234
CONSUL_URL=http://icecream-consul:32001
ECHO_URL=http://icecream-echo:32002 
STORAGE_URL=http://garage:38004 
```
