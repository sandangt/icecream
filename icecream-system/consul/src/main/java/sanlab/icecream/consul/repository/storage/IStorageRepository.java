package sanlab.icecream.consul.repository.storage;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface IStorageRepository {

    Path upload(MultipartFile file, Path filePath);

}
