FROM apache/apisix:3.19.0-debian

ENV APISIX_STAND_ALONE=true

COPY --chown=apisix:apisix config.yaml /usr/local/apisix/conf/config.yaml
COPY --chown=apisix:apisix apisix.yaml /usr/local/apisix/conf/apisix.yaml
