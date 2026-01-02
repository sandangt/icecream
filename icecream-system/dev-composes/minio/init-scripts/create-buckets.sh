#!/bin/sh

set -e

# Start MinIO server in the background using the provided command-line arguments
/usr/bin/minio "$@" &

minio_pid=$!

# Wait for MinIO to become healthy
until curl -sf http://127.0.0.1:9000/minio/health/live; do
  sleep 1
done

# Set up mc alias for the local MinIO instance
mc alias set local http://127.0.0.1:9000 "${MINIO_ROOT_USER}" "${MINIO_ROOT_PASSWORD}"

# Updated list: added buckets for traces and metrics
BUCKETS="images icecream-logs icecream-traces icecream-metrics"

for bucket in $BUCKETS; do
  mc mb "local/${bucket}" --ignore-existing
  echo "Successfully verified/created bucket: ${bucket}"
done

# Set 'images' to public
mc anonymous set download local/images

wait $minio_pid
