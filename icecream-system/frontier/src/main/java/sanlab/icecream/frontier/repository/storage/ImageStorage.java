package sanlab.icecream.frontier.repository.storage;

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
import sanlab.icecream.fundamentum.exception.StoringFileException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public record ImageStorage(MinioClient minioClient,
                           @Value("${minio.bucket.image}") String bucketName) {

    public Path upload(MultipartFile img, Path filePath) {
        try (InputStream ins = img.getInputStream()) {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filePath.toString())
                    .stream(ins, img.getSize(), -1)
                    .build());
        } catch (ServerException | InsufficientDataException | ErrorResponseException | NoSuchAlgorithmException |
                 InvalidKeyException | InvalidResponseException | XmlParserException | InternalException |
                 IOException ex) {
            throw new StoringFileException("Failed to store image file");
        }
        return Path.of(bucketName, filePath.toString());
    }

}
