FROM minio/minio:RELEASE.2025-04-22T22-12-26Z

# Copy mc binary from the official mc image
COPY --from=minio/mc:RELEASE.2025-08-13T08-35-41Z /usr/bin/mc /usr/bin/mc

# Copy the initialization script
COPY ./minio/init-scripts/create-buckets.sh /create-buckets.sh

# Make the script executable
RUN chmod +x /create-buckets.sh

# Set the entrypoint to our custom script
ENTRYPOINT ["/create-buckets.sh"]
