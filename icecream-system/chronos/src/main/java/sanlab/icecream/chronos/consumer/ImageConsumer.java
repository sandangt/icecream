package sanlab.icecream.chronos.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import sanlab.icecream.chronos.repository.storage.StorageRepository;
import sanlab.icecream.fundamentum.dto.FileHandlingDto;

import java.util.function.Consumer;

@Configuration
public class ImageConsumer {

    private final StorageRepository minIOStorageRepository;

    public ImageConsumer(StorageRepository minIOStorageRepository) {
        this.minIOStorageRepository = minIOStorageRepository;
    }

    @Bean
    public Consumer<Message<FileHandlingDto>> deleteImage() {
        return message -> {
            FileHandlingDto payload = message.getPayload();
            minIOStorageRepository.deleteImage(payload.getRelativePath());
        };
    }

}
