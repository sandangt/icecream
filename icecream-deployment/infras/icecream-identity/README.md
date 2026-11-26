# ConfigMap and Secret existing name:
- `auth.existingSecret`: icecream-credentials
- `config.existingConfigMap`: icecream-configmaps
# `auth.existingSecret` required fields:
```env
KEYCLOAK_ADMIN_PASSWORD=keycloakadmin1234
KEYCLOAK_DB_PASSWORD=password1234
```
# `config.existingConfigMap` required fields:
```env
KEYCLOAK_ADMIN=keycloakadmin
KEYCLOAK_DB_USER=dbadmin
KEYCLOAK_DB_URL=jdbc:postgresql://postgresql:5432/icecream-identity
```
# Visiting admin site
- Access `icecream-auth-admin.sanlab.zone/admin/master/console`
