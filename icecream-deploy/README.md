# icecream-deploy

Deployment code for the **icecream** e-commerce platform on your **LAN3**
Kubernetes cluster (`sanlab-k8s-1`, Talos Linux, MetalLB `10.0.10.100-149`),
sitting behind pfsense/HAProxy/Cloudflare at `icecream.sanlab.zone`.

This README is written assuming you are a **complete beginner** with
Kubernetes/Helm but already comfortable with Docker/Dockerfiles. Read it
top to bottom once before running anything.

---

## 1. The 6 Kubernetes/Helm concepts you actually need

You don't need to learn all of Kubernetes. For this app, these building
blocks cover ~95% of what you'll touch:

| Concept | What it is | Analogy |
|---|---|---|
| **Pod** | One or more containers running together, the smallest deployable unit. | A single running container (usually 1:1 with your Docker container). |
| **Deployment** | Declares "I want N replicas of this Pod running, always". Handles rolling restarts, self-healing. | A supervisor/systemd unit that also does zero-downtime redeploys. |
| **Service** | A stable internal DNS name + virtual IP that load-balances traffic to a set of Pods. | An internal load balancer / service discovery entry, e.g. `consul.icecream.svc.cluster.local`. |
| **ConfigMap / Secret** | Key-value config injected into Pods as env vars or files. Secret = base64-encoded, meant for passwords. | Your `.env` file, split into "safe to read" (ConfigMap) and "sensitive" (Secret). |
| **Ingress / ApisixRoute** | Routes external HTTP(S) traffic (by host/path) to internal Services. | nginx `server {}` blocks / reverse proxy rules — in your stack, this is APISIX. |
| **HPA (HorizontalPodAutoscaler)** | Automatically adds/removes replicas based on CPU/memory load. | Auto-scaling group. |

**Helm** is just a templating + packaging layer on top of raw Kubernetes YAML:

```
a Helm "chart" = Chart.yaml (metadata) + values.yaml (config knobs)
                 + templates/*.yaml (YAML with {{ .Values.x }} placeholders)

"helm install myrelease ./mychart -f myvalues.yaml"
   = render templates with values.yaml + myvalues.yaml merged
   = kubectl apply the result, tracked as a "release" you can upgrade/rollback
```

That's it. Everything below is just applying this pattern repeatedly.

---

## 2. Why one generic chart instead of 7 charts

Your 7 Spring Boot services (`codex`, `consul`, `memoir`, `echo`, `chronos`,
`conflux`, `horus`) are structurally identical: a container, a port, a
Service, some env vars, maybe a Secret, maybe autoscaling. Writing 7 near-copies
of the same Deployment/Service YAML is exactly the kind of duplication Helm
exists to avoid.

So this repo has **one reusable chart**, `charts/spring-boot-service/`, and **7
small values files** (`environments/lan3/values-<service>.yaml`) that each
just override the differences (image name, DB connection string, resources).
`storefront` gets its own small chart (`charts/storefront/`) because NextJS's
port/health-check shape is different enough to not be worth forcing into the
same template.

When you build an 8th backend service later: add one Dockerfile, one values
file, done — no new chart needed.

---

## 3. Repository layout

```
icecream-deploy/
├── 00-namespace.yaml                 # creates the `icecream` namespace
├── charts/
│   ├── spring-boot-service/                # generic reusable chart (all 7 backends)
│   │   ├── Chart.yaml
│   │   ├── values.yaml                # sane defaults
│   │   └── templates/                 # Deployment, Service, ConfigMap, Secret, HPA, SA
│   └── storefront/                    # NextJS-specific chart
│       ├── Chart.yaml
│       ├── values.yaml
│       └── templates/
├── environments/lan3/                 # per-service overrides (THE file you edit most)
│   ├── values-codex.yaml
│   ├── values-consul.yaml
│   ├── values-memoir.yaml
│   ├── values-echo.yaml
│   ├── values-chronos.yaml
│   ├── values-conflux.yaml
│   ├── values-horus.yaml
│   └── values-storefront.yaml
├── infra/                             # 3rd-party infra, deployed via Bitnami Helm charts
│   ├── values-postgresql-consul.yaml
│   ├── values-postgresql-memoir.yaml
│   ├── values-postgresql-keycloak.yaml
│   ├── values-mongodb-echo.yaml
│   ├── values-mongodb-chronos.yaml
│   ├── values-redis.yaml
│   ├── values-rabbitmq.yaml
│   ├── values-kafka.yaml
│   ├── values-elasticsearch.yaml
│   ├── values-minio.yaml
│   ├── values-keycloak.yaml
│   ├── values-apisix.yaml
│   └── kafka-connect/                 # Debezium CDC wiring (postgres -> kafka -> ES)
├── gateway/                            # APISIX route definitions (public entry points)
│   ├── apisixroute-storefront.yaml
│   ├── apisixroute-api.yaml
│   └── apisixroute-horus.yaml          # commented out - internal only by default
├── secrets/README.md                  # list of every Secret this stack expects
└── scripts/
    ├──00-add-helm-repos.sh
    ├── 10-install-infra.sh
    ├── 20-create-secrets.sh
    └── 30-install-services.sh
```

---

## 4. Mapping your architecture diagram to infra choices

| Diagram element | What it is | How it's deployed here |
|---|---|---|
| `storefront` | NextJS 16 SSR/SSG, NextAuth+Keycloak | `charts/storefront` |
| `codex` | central config, no DB | `spring-boot-service` chart, no DB env |
| `consul` | transactional service | `spring-boot-service` chart + dedicated `consul-postgresql` |
| `memoir` | user audit log | `spring-boot-service` chart + dedicated `memoir-postgresql` (also the "full-text search DB" in your diagram) |
| `echo` | async notifications | `spring-boot-service` chart + dedicated `echo-mongodb` |
| `chronos` | scheduler | `spring-boot-service` chart + dedicated `chronos-mongodb` |
| `conflux` | consumer & enrichment, no own DB | `spring-boot-service` chart, consumes Kafka, writes to Elasticsearch |
| `horus` | backoffice site | `spring-boot-service` chart, reads Elasticsearch + `consul-postgresql`, **not** publicly routed |
| `idp` | Keycloak | `bitnami/keycloak` + dedicated `keycloak-postgresql` |
| `cache` | Redis | `bitnami/redis`, one shared instance |
| `task queue` | RabbitMQ | `bitnami/rabbitmq`, one shared instance |
| `message queue` | Kafka | `bitnami/kafka`, one shared instance (KRaft mode, no ZooKeeper) |
| `search engine` | Elasticsearch | `bitnami/elasticsearch` |
| `object storage` | MinIO | `bitnami/minio` |
| CDC flow (postgres ⇄ message queue ⇄ search engine) | Debezium + Kafka Connect | `infra/kafka-connect/` (custom image + a Job that registers the Debezium Postgres connector) |
| API gateway | APISIX | `apisix/apisix` Helm chart, exposed as `type: LoadBalancer` |

Each backend gets **its own database instance** rather than one shared
Postgres/Mongo — this matches microservice best practice (no service reaches
into another's tables) and is cheap in a homelab since these are small
workloads.

---

## 5. Networking: how a request reaches a Pod

```
Browser
  │  https://icecream.sanlab.zone
  ▼
Cloudflare DNS (A record kept in sync by pfsense Dynamic DNS)
  ▼
ISP router → pfsense WAN (192.168.1.132)
  ▼
pfsense HAProxy (TLS termination, cert from Acme plugin — same as your other apps)
  ▼  (plain HTTP, forwarded into LAN3)
MetalLB VIP for `apisix-gateway` Service, e.g. 10.0.10.10x
  (this IP is inside 10.0.8.0/22, so pfsense's LAN3 interface (10.0.8.1)
   routes/ARPs to it directly — no extra routing needed)
  ▼
APISIX (ApisixRoute rules in gateway/) — path/host based routing
  ├── "/"      → storefront (NextJS SSR)
  ├── "/api/*" → consul (BFF for transactional operations)
  └── "/auth/*"→ keycloak (OIDC endpoints for NextAuth)
```

Add one more HAProxy backend entry in pfsense (like you already do for
`media-store.sanlab.zone`, `lorem.sanlab.zone`, etc.), pointing at the APISIX
MetalLB VIP instead of a single VM IP. Everything after that is standard k8s
Service routing.

`horus` (backoffice) is deliberately **not** given a public ApisixRoute in
this repo — see the commented template in `gateway/apisixroute-horus.yaml`
for how to expose it later behind Keycloak auth on its own subdomain, once
you're ready.

---

## 6. Step-by-step: first deployment

```bash
# 0. Point kubectl at sanlab-k8s-1 (LAN3), confirm nodes are Ready
kubectl config use-context sanlab-k8s-1
kubectl get nodes

# 1. Add Helm repos
./scripts/00-add-helm-repos.sh

# 2. Create the namespace
kubectl apply -f 00-namespace.yaml

# 3. Create all required Secrets FIRST (infra + app credentials)
#    Edit/extend scripts/20-create-secrets.sh using secrets/README.md as your checklist,
#    then run it with env vars set, e.g.:
export CONSUL_PG_ADMIN_PW=... CONSUL_PG_APP_PW=... RABBITMQ_PW=...
./scripts/20-create-secrets.sh

# 4. Install infra (databases, brokers, search, storage, idp, gateway, CDC)
./scripts/10-install-infra.sh
kubectl -n icecream get pods -w      # wait until everything is Running/Ready

# 5. Push your app images to your own registry, then update image.tag
#    in environments/lan3/values-*.yaml to match what you built.

# 6. Install the 7 backend services + storefront
./scripts/30-install-services.sh

# 7. Get the gateway's LoadBalancer IP and configure pfsense HAProxy
kubectl -n icecream get svc apisix-gateway
#   -> use the EXTERNAL-IP (from the 10.0.10.100-149 MetalLB pool) as the
#      HAProxy backend server for icecream.sanlab.zone
```

Order matters: **secrets → infra → Kafka Connect/Debezium → app services →
gateway routes**, because Deployments will crash-loop (harmlessly) until
their ConfigMaps/Secrets/DBs exist — Kubernetes just keeps retrying, so this
isn't fragile, just noisy if done out of order.

---

## 7. Day-2 basics you'll actually use

```bash
kubectl -n icecream get pods                       # what's running
kubectl -n icecream logs deploy/consul -f           # tail logs
kubectl -n icecream describe pod <name>             # why is it not starting
helm -n icecream upgrade consul charts/spring-boot-service -f environments/lan3/values-consul.yaml
                                                     # deploy a new image tag / config change
helm -n icecream rollback consul                    # undo a bad upgrade
helm -n icecream history consul                     # see release history
```

---

## 8. Sensible next steps (not required to launch, but worth knowing about)

- **Secrets management**: replace the manual `kubectl create secret` script
  with [sealed-secrets](https://github.com/bitnami-labs/sealed-secrets) so
  you *can* commit secrets to git safely.
- **GitOps**: once comfortable, point ArgoCD or Flux at this repo so `git
  push` = deploy, instead of running scripts by hand.
- **NetworkPolicies**: restrict which Pods can talk to which (e.g. only
  `consul` can reach `consul-postgresql`), since by default all Pods in a
  namespace can reach each other.
- **PodDisruptionBudgets** + **resource requests/limits tuning** once you see
  real traffic/memory usage via `kubectl top pods`.
- **Observability**: you already have a "proxmox monitoring vm" on LAN — add
  a `kube-prometheus-stack` Helm release here too, scraping via Spring Boot
  Actuator's `/actuator/prometheus` endpoint on each service.
- **Strimzi** as a more production-grade alternative to `bitnami/kafka` +
  hand-rolled Kafka Connect, once you outgrow this simpler setup (adds
  `KafkaConnector` CRDs instead of the REST-API Job used here).

---

## 9. Assumptions made (double-check / adjust)

- Your Spring Boot services expose Actuator health at `/actuator/health`,
  `/actuator/health/liveness`, `/actuator/health/readiness` on the same port
  as the app (8080) — adjust `containerPort`/`managementPort` per service in
  `environments/lan3/` if you split them (common in Spring Boot: management
  on 8081).
- `storefront` exposes a simple `/api/health` route returning 200 (add this
  as a trivial NextJS route handler if it doesn't exist yet).
- Debezium CDC is wired only for `consul`'s Postgres → Kafka → (consumed by
  `conflux`) → Elasticsearch, matching the CDC arrows in your diagram. Add a
  second connector block in `register-debezium-connector-job.yaml` if
  `memoir`'s Postgres also needs CDC.
- `horus`'s exact data access pattern was inferred from the diagram (reads
  Elasticsearch + `consul`'s DB) — adjust `values-horus.yaml` env vars to
  match its real implementation.
