#!/bin/bash

set -e
set -u

function create_database_with_mysql_user() {
	local database=$1
	mysql -u root -p$MYSQL_ROOT_PASSWORD <<-EOSQL
	    CREATE DATABASE $database;
EOSQL
}


if [ -n "$MYSQL_MULTIPLE_DATABASES" ]; then
	echo "Multiple databases creation requested: $MYSQL_MULTIPLE_DATABASES"
	for db in $(echo $MYSQL_MULTIPLE_DATABASES | tr ',' ' '); do
		echo "  Creating databases '$db'"
		create_database_with_mysql_user $db
	done
	echo "Multiple databases created"
fi


mysql -u root -p$MYSQL_ROOT_PASSWORD <<-EOSQL
    GRANT ALL PRIVILEGES ON *.* TO '$MYSQL_USER'@'%';
    FLUSH PRIVILEGES;
EOSQL