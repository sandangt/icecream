package sanlab.icecream.customer.config;

import java.util.HashMap;
import java.util.Map;

import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import sanlab.icecream.sharedlib.constant.KafkaGroup;
import sanlab.icecream.sharedlib.proto.CustomerDTO;


@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServersUrl;
    @Value("${spring.kafka.schema-registry-server}")
    private String schemaRegistryServerUrl;

    private Map<String, Object> generateProtobufObjConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServersUrl);
        properties.put(KafkaProtobufDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryServerUrl);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaGroup.CUSTOMER);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaProtobufDeserializer.class);
        return properties;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CustomerDTO> customerListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CustomerDTO> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        Map<String, Object> properties = generateProtobufObjConfig();
        properties.put(KafkaProtobufDeserializerConfig.SPECIFIC_PROTOBUF_VALUE_TYPE, CustomerDTO.class.getName());
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(properties));
        return factory;
    }

}
