package sanlab.icecream.consul.repository.storage;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageRepository {

    Path upload(MultipartFile file, Path filePath);

}
