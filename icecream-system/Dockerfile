# -------- Build stage --------
FROM maven:3.9.13-eclipse-temurin-25 AS builder

WORKDIR /build
COPY . .

ARG MODULE
RUN mvn -pl ${MODULE} -am clean package -DskipTests

# -------- Runtime --------
FROM eclipse-temurin:25-jre-jammy

WORKDIR /app

# (Optional) OpenTelemetry agent
COPY java-packages/opentelemetry-javaagent.jar /otel/agent.jar

ARG MODULE
COPY --from=builder /build/${MODULE}/target/*.jar app.jar

ENTRYPOINT [
  "java",
  "-javaagent:/otel/agent.jar",
  "-XX:MaxRAMPercentage=75.0",
  "-jar",
  "app.jar"
]
