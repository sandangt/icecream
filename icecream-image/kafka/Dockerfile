FROM confluentinc/cp-kafka:7.8.4

LABEL maintainer='dangthaisan@hotmail.com'
LABEL repackaged='true'

COPY --chmod=755 docker-entrypoint.sh /opt/kafka/docker-entrypoint.sh
COPY --chmod=755 create-topics.sh /opt/kafka/create-topics.sh

ENTRYPOINT ["/opt/kafka/docker-entrypoint.sh"]
