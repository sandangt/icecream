package sanlab.icecream.consul.repository.queue.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import sanlab.icecream.consul.repository.queue.ImageQueueRepository;
import sanlab.icecream.fundamentum.dto.FileHandlingDto;

@Component
@RequiredArgsConstructor
public class ImageQueueRepositoryImpl implements ImageQueueRepository {

    private final StreamBridge streamBridge;

    private static final String DELETE_FILE_OUT_CHANNEL = "imageDelete-out-0";

    @Override
    public void delete(FileHandlingDto fileHandlingDto) {
        var message = MessageBuilder
            .withPayload(fileHandlingDto)
            .build();
        streamBridge.send(DELETE_FILE_OUT_CHANNEL, message);
    }
}
