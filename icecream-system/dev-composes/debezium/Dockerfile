FROM debezium/connect:2.7.3.Final

# Install Confluent Elasticsearch Sink Connector
RUN #confluent-hub install --no-prompt confluentinc/kafka-connect-elasticsearch:14

# Optional: Install other useful connectors
RUN confluent-hub install --no-prompt debezium/debezium-connector-postgresql:2.7.3
# RUN confluent-hub install --no-prompt debezium/debezium-connector-mysql:2.7.3
# RUN confluent-hub install --no-prompt debezium/debezium-connector-mongodb:2.7.3
