FROM eclipse-temurin:25-jre-jammy

RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY java-packages/opentelemetry-javaagent.jar /otel/agent.jar

COPY memoir/target/*.jar /app/app.jar

ENTRYPOINT ["java",\
  "-javaagent:/otel/agent.jar",\
  "-XX:MaxRAMPercentage=75.0",\
  "-XX:NativeMemoryTracking=summary",\
  "-jar", "app.jar"]
