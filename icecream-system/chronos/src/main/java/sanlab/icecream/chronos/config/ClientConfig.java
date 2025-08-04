package sanlab.icecream.chronos.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Value("${app.minio.url}")
    private String minioUrl;
    @Value("${app.minio.access.key}")
    private String accessKey;
    @Value("${app.minio.access.secret}")
    private String accessSecret;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
            .endpoint(minioUrl)
            .credentials(accessKey, accessSecret)
            .build();
    }

}
