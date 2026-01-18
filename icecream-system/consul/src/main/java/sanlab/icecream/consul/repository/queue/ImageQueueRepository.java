package sanlab.icecream.consul.repository.queue;

import sanlab.icecream.fundamentum.dto.FileHandlingDto;

public interface ImageQueueRepository {

    void delete(FileHandlingDto fileDto);

}
