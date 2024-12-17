package sanlab.icecream.frontier.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Value("${minio.url}")
    private String minioUrl;
    @Value("${minio.access.key}")
    private String accessKey;
    @Value("${minio.access.secret}")
    private String accessSecret;

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
            .endpoint(minioUrl)
            .credentials(accessKey, accessSecret)
            .build();
    }

}
