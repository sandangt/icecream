package sanlab.icecream.gateway.config;

import java.util.HashMap;
import java.util.Map;

import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import sanlab.icecream.sharedlib.proto.CategoryDTO;
import sanlab.icecream.sharedlib.proto.ProductCategoryRelationship;
import sanlab.icecream.sharedlib.proto.ProductDTO;


@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServersUrl;
    @Value("${spring.kafka.schema-registry-server}")
    private String schemaRegistryServerUrl;

    private Map<String, Object> generateProtobufObjConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServersUrl);
        properties.put(KafkaProtobufSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryServerUrl);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaProtobufSerializer.class);
        return properties;
    }

    @Bean(name = "product-producer")
    public KafkaTemplate<String, ProductDTO> productProducer() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(generateProtobufObjConfig()));
    }

    @Bean(name = "category-producer")
    public KafkaTemplate<String, CategoryDTO> categoryProducer() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(generateProtobufObjConfig()));
    }

    @Bean(name = "product-category-producer")
    public KafkaTemplate<String, ProductCategoryRelationship> productRelationshipProducer() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(generateProtobufObjConfig()));
    }
}
