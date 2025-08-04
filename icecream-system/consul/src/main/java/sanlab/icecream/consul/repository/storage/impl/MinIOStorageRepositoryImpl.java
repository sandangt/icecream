package sanlab.icecream.consul.repository.storage.impl;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sanlab.icecream.consul.repository.storage.StorageRepository;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_STORE_IMAGE_FILE;

@Component("minIOStorageRepository")
public class MinIOStorageRepositoryImpl implements StorageRepository {

    private final MinioClient minioClient;
    private final String bucketName;

    public MinIOStorageRepositoryImpl(
        MinioClient minioClient,
        @Value("${app.minio.bucket.image}") String bucketName) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
    }

    @Override
    public Path upload(MultipartFile file, Path filePath) {
        try (InputStream ins = file.getInputStream()) {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filePath.toString())
                    .stream(ins, file.getSize(), -1)
                    .build());
        } catch (ServerException | InsufficientDataException | ErrorResponseException | NoSuchAlgorithmException |
                 InvalidKeyException | InvalidResponseException | XmlParserException | InternalException |
                 IOException ex) {
            throw new IcRuntimeException(ex, FAIL_TO_STORE_IMAGE_FILE);
        }
        return Path.of(bucketName, filePath.toString());
    }
}
