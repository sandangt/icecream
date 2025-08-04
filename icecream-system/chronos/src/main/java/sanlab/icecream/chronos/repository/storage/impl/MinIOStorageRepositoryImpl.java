package sanlab.icecream.chronos.repository.storage.impl;

import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sanlab.icecream.chronos.repository.storage.StorageRepository;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import static sanlab.icecream.chronos.exception.ChronosErrorModel.FAIL_TO_PROCESS_STORAGE_SERVICE;

@Component("minIOStorageRepository")
public class MinIOStorageRepositoryImpl implements StorageRepository {

    private final MinioClient minioClient;
    private final String imageBucket;

    public MinIOStorageRepositoryImpl(
        MinioClient minioClient,
        @Value("${app.minio.bucket.image}") String imageBucket
    ) {
        this.minioClient = minioClient;
        this.imageBucket = imageBucket;
    }

    @Override
    public void deleteImage(String relativePath) {
        try {
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(imageBucket)
                    .object(relativePath.replace(imageBucket, StringUtils.EMPTY))
                    .build()
            );
        } catch (Exception ex) {
            throw new IcRuntimeException(FAIL_TO_PROCESS_STORAGE_SERVICE);
        }
    }
}
