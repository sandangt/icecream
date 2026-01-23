FROM debezium/connect:2.7.3.Final

USER root

COPY --chown=kafka:kafka debezium/plugins /kafka/connect

RUN cd /kafka/connect && \
        for f in ./*.zip; do [ -e "$f" ] && unzip -o "$f" -d . || true; done && \
        for f in ./*.tar.gz; do [ -e "$f" ] && tar -xzf "$f" -C . || true; done

RUN chown -R kafka:kafka /kafka/connect

USER kafka
