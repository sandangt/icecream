package sanlab.icecream.fundamentum.config.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import static sanlab.icecream.fundamentum.utils.CompressionUtils.compress;
import static sanlab.icecream.fundamentum.utils.CompressionUtils.decompress;

public class CompressedRedisSerializer implements RedisSerializer<Object> {

    private final ObjectMapper objectMapper;

    public CompressedRedisSerializer() {
        this.objectMapper = new ObjectMapper();
        BasicPolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfBaseType(Object.class)
            .build();
        this.objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return new byte[0];
        }
        try {
            return compress(objectMapper.writeValueAsBytes(object));
        } catch (Exception e) {
            throw new SerializationException("Failed to serialize object", e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        try {
            return objectMapper.readValue(decompress(bytes), Object.class);
        } catch (Exception e) {
            throw new SerializationException("Failed to deserialize object", e);
        }
    }

}
