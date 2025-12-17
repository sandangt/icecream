FROM confluentinc/cp-kafka:7.8.4

COPY --chmod=755 kafka/docker-entrypoint.sh /opt/kafka/docker-entrypoint.sh
COPY --chmod=755 kafka/init-scripts/create-topics.sh /opt/kafka/init-scripts/create-topics.sh

ENTRYPOINT ["/opt/kafka/docker-entrypoint.sh"]
