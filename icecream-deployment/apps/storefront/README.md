# ConfigMap and Secret existing name:
- `auth.existingSecret`: icecream-credentials
- `config.existingConfigMap`: icecream-configmaps
# `auth.existingSecret` required fields:
```env
STORAGE_ACCESS_KEY=password1234
STORAGE_SECRET_KEY=password1234
STOREFRONT_AUTH_SECRET=password1234
IDENTITY_STOREFRONT_CLIENT_ID=password1234
IDENTITY_STOREFRONT_CLIENT_SECRET=password1234
```
# `config.existingConfigMap` required fields:
```env
STOREFRONT_URL=https://icecream.sanlab.zone
IDENTITY_ISSUER=https://keycloak/realms/icecream
CONSUL_URL=http://gateway/consul
ECHO_URL=http://gateway/echo
STORAGE_URL=http://gateway/storage
```
