FROM eclipse-temurin:25-jre-jammy

WORKDIR /app

COPY java-packages/opentelemetry-javaagent.jar /otel/agent.jar

COPY consul/target/*.jar /app/app.jar

ENTRYPOINT [ "java", "-javaagent:/otel/agent.jar", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar" ]
