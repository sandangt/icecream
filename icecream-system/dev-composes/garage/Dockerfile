FROM alpine:3.22

# Garage ships as a single static binary; copy it from the official image
COPY --from=dxflrs/garage:v2.4.1 /garage /usr/local/bin/garage

COPY garage/init-scripts/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
