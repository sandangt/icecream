package sanlab.icecream.consul.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sanlab.icecream.consul.mapper.ImageMapper;
import sanlab.icecream.consul.mapper.ProductMapper;
import sanlab.icecream.fundamentum.constant.EImageType;
import sanlab.icecream.fundamentum.constant.StoragePath;
import sanlab.icecream.fundamentum.dto.core.ImageDto;
import sanlab.icecream.consul.model.Image;
import sanlab.icecream.consul.repository.crud.ImageRepository;
import sanlab.icecream.consul.repository.storage.StorageRepository;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PERSIST_DATA_FAILED;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_STORE_IMAGE_FAILED;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_IMAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final StorageRepository minIOStorageRepository;
    private final ImageMapper imageMapper;
    private final ProductMapper productMapper;

    private static final String AVATAR_PIC_NAME = "avatar";
    private static final String MEDIA_PIC_PATTERN = "media-%s";

    @Transactional(readOnly = true)
    public List<ProductExtendedDto> getAllProducts(UUID id) {
        if (!imageRepository.existsById(id))
            throw new IcRuntimeException(REPOSITORY_IMAGE_NOT_FOUND, "id: %s".formatted(id));
        return imageRepository.findAllProductsById(id)
            .parallelStream()
            .map(productMapper::entityToExtendedDto)
            .toList();
    }

    @Transactional
    public ImageDto upsertImage(Path filePath, MultipartFile img, String description) {
        Path relativePath = minIOStorageRepository.upload(img, filePath);
        try {
            var image = imageRepository.findFirstByRelativePath(relativePath.toString())
                .orElseGet(() -> imageRepository.save(Image.builder()
                    .description(description)
                    .relativePath(relativePath.toString())
                    .type(EImageType.AVATAR.name())
                    .build()));
            return imageMapper.entityToDto(image);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "image");
        }
    }

    public ImageDto upsertProductAvatar(UUID productId, MultipartFile img) {
        String fileName = String.join(
            ".", AVATAR_PIC_NAME, FilenameUtils.getExtension(img.getOriginalFilename()));
        Path filePath = Path.of(StoragePath.PRODUCT, productId.toString(), fileName);
        return upsertImage(filePath, img, "Avatar of product with id %s".formatted(productId.toString()));
    }

    public ImageDto upsertCategoryAvatar(UUID categoryId, MultipartFile img) {
        String fileName = String.join(
            ".", AVATAR_PIC_NAME, FilenameUtils.getExtension(img.getOriginalFilename()));
        Path filePath = Path.of(StoragePath.CATEGORY, categoryId.toString(), fileName);
        return upsertImage(filePath, img, "Avatar of category with id %s".formatted(categoryId.toString()));
    }

    public ImageDto upsertCustomerAvatar(UUID userId, MultipartFile img) {
        String fileName = String.join(
            ".", AVATAR_PIC_NAME, FilenameUtils.getExtension(img.getOriginalFilename()));
        Path filePath = Path.of(StoragePath.CUSTOMER, userId.toString(), fileName);
        return upsertImage(filePath, img, "Avatar of user with id %s".formatted(userId.toString()));
    }

    @Transactional
    public List<ImageDto> bulkUpsertProductMedia(UUID productId, MultipartFile[] media) {
        List<ImageDto> result = new ArrayList<>();
        Deque<Image> savedDQ = new ArrayDeque<>();
        for (int i=0; i<media.length; i++) {
            if (media[i].isEmpty()) {
                continue;
            }
            String fileName = String.join(".", MEDIA_PIC_PATTERN.formatted(i),
                FilenameUtils.getExtension(media[i].getOriginalFilename()));
            Path relativePath = minIOStorageRepository.upload(media[i], Path.of(StoragePath.PRODUCT, productId.toString(), fileName));
            imageRepository.findFirstByRelativePath(relativePath.toString())
                .ifPresentOrElse(image -> result.add(imageMapper.entityToDto(image)), () -> {
                var newImage = Image.builder()
                    .description("Image of product with id %s".formatted(productId.toString()))
                    .relativePath(relativePath.toString())
                    .type(EImageType.MEDIA.name())
                    .build();
                savedDQ.add(newImage);
            });
        }
        try {
            var newImgs = imageRepository.saveAll(savedDQ);
            result.addAll(imageMapper.entityToDto(newImgs));
            return result;
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_STORE_IMAGE_FAILED);
        }
    }

}
