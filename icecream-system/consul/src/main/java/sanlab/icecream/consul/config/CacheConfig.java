package sanlab.icecream.consul.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import sanlab.icecream.fundamentum.config.serde.CompressedRedisSerializer;

import java.time.Duration;

@Configuration
public class CacheConfig {

    @Bean
    @Lazy
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        CompressedRedisSerializer serializer = new CompressedRedisSerializer();
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }

    private static RedisCacheConfiguration buildCacheConfig() {
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(15))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
            )
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(new CompressedRedisSerializer())
            )
            .disableCachingNullValues();
    }

    @Bean
    @Primary
    public RedisCacheManager defaultCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = buildCacheConfig();
        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(config)
            .transactionAware()
            .build();
    }

    @Bean
    @Lazy
    public RedisCacheManager shortLivedCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = buildCacheConfig();
        config = config.entryTtl(Duration.ofSeconds(30));
        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(config)
            .transactionAware()
            .build();
    }

    @Bean
    @Lazy
    public RedisCacheManager longLivedCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = buildCacheConfig();
        config = config.entryTtl(Duration.ofHours(12));
        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(config)
            .transactionAware()
            .build();
    }

}

