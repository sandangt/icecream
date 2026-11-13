#!/bin/sh
set -eu

rm -f /tmp/garage-ready

# 1. Start Garage in the background (args come from compose `command:`)
garage "$@" &
garage_pid=$!

# Forward stop signals so `docker compose down` shuts Garage down cleanly
trap 'kill -TERM "$garage_pid" 2>/dev/null; wait "$garage_pid"; exit $?' TERM INT

# 2. Wait until the single-node bootstrap has created the default key
n=0
until garage key info "${GARAGE_DEFAULT_ACCESS_KEY}" >/dev/null 2>&1; do
  kill -0 "$garage_pid" 2>/dev/null || { echo "garage exited during startup" >&2; exit 1; }
  n=$((n+1)); [ "$n" -ge 60 ] && { echo "default key not ready" >&2; exit 1; }
  sleep 1
done

# 3. Create the extra buckets and grant the key access (idempotent)
for b in ${EXTRA_BUCKETS:-}; do
  garage bucket info "$b" >/dev/null 2>&1 || garage bucket create "$b"
  garage bucket allow --read --write --owner "$b" --key "${GARAGE_DEFAULT_ACCESS_KEY}"
  echo "Bucket ready: $b"
done

# 4. Public buckets (replacement for `mc anonymous set download`)
for b in ${PUBLIC_BUCKETS:-}; do
  garage bucket website --allow "$b"
  echo "Bucket public via web endpoint: $b"
done

touch /tmp/garage-ready
echo "Garage init done"

# 5. Keep the container alive as long as Garage runs
wait "$garage_pid"
