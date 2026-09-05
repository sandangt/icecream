# Secrets

**Never commit real passwords to git.** These are created imperatively by
`scripts/20-create-secrets.sh` (which you fill in locally / keep out of git,
e.g. via a `.env` file that's git-ignored), or later upgrade to
[sealed-secrets](https://github.com/bitnami-labs/sealed-secrets) or
[external-secrets](https://external-secrets.io/) once you're comfortable.

List of secrets this stack expects (name -> keys):

| Secret name                  | Keys                                              | Used by            |
|-------------------------------|----------------------------------------------------|---------------------|
| consul-postgresql-secret      | postgres-password, password                        | postgresql (consul) |
| consul-db-secret              | SPRING_DATASOURCE_USERNAME/PASSWORD, RABBITMQ_*     | consul              |
| memoir-postgresql-secret      | postgres-password, password                        | postgresql (memoir) |
| memoir-db-secret              | SPRING_DATASOURCE_USERNAME/PASSWORD, MINIO_*        | memoir              |
| keycloak-postgresql-secret    | postgres-password, password                        | postgresql (keycloak)|
| echo-mongodb-secret           | mongodb-passwords, mongodb-root-password            | mongodb (echo)      |
| echo-db-secret                | SPRING_DATA_MONGODB_*, SPRING_RABBITMQ_*            | echo                |
| chronos-mongodb-secret        | mongodb-passwords, mongodb-root-password            | mongodb (chronos)   |
| chronos-db-secret             | SPRING_DATA_MONGODB_*, SPRING_RABBITMQ_*            | chronos             |
| horus-db-secret               | SPRING_DATASOURCE_USERNAME/PASSWORD (read-only)     | horus               |
| redis-secret                  | redis-password                                      | redis               |
| rabbitmq-secret                | rabbitmq-password                                   | rabbitmq            |
| minio-secret                  | root-user, root-password                            | minio               |
| keycloak-admin-secret         | admin-password                                      | keycloak            |
| storefront-secret             | NEXTAUTH_SECRET, KEYCLOAK_CLIENT_ID/SECRET          | storefront          |
