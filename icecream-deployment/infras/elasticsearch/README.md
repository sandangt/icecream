# ConfigMap and Secret existing name:
- `auth.existingSecret`: icecream-credentials
- `config.existingConfigMap`: icecream-configmaps
# `auth.existingSecret` required fields:
```env
ELASTICSEARCH_PASSWORD=password1234
```
# `config.existingConfigMap` required fields:
```env
ELASTICSEARCH_USERNAME=admin
```
