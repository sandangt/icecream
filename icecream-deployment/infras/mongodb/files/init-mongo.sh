#!/bin/bash
set -e

if [ -n "$MONGO_MULTIPLE_DATABASES" ]; then
  echo "Creating multiple databases: $MONGO_MULTIPLE_DATABASES"
  mongo_cmd="mongosh --username $MONGO_INITDB_ROOT_USERNAME --password $MONGO_INITDB_ROOT_PASSWORD --authenticationDatabase admin"

  for db in $(echo $MONGO_MULTIPLE_DATABASES | tr "," " "); do
    echo "Creating database: $db"
    $mongo_cmd <<EOF
    use $db
    db.createCollection("initCollection")
EOF
  done
fi
