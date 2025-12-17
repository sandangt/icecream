FROM postgres:16.8

RUN apt-get update && \
    apt-get install -y postgresql-16-wal2json && \
    rm -rf /var/lib/apt/lists/*

COPY ./postgresql/create-multiple-postgresql-databases.sh /docker-entrypoint-initdb.d/

RUN echo "wal_level = logical" >> /usr/share/postgresql/postgresql.conf && \
    echo "max_wal_senders = 10" >> /usr/share/postgresql/postgresql.conf && \
    echo "max_replication_slots = 10" >> /usr/share/postgresql/postgresql.conf

CMD ["postgres", "-c", "wal_level=logical", "-c", "max_wal_senders=10", "-c", "max_replication_slots=10"]
