package sanlab.icecream.frontier.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sanlab.icecream.fundamentum.constant.EImageType;
import sanlab.icecream.fundamentum.constant.StoragePath;
import sanlab.icecream.frontier.dto.core.ImageDto;
import sanlab.icecream.fundamentum.exception.StoringImageException;
import sanlab.icecream.frontier.mapper.IImageMapper;
import sanlab.icecream.frontier.model.Image;
import sanlab.icecream.frontier.repository.crud.IImageRepository;
import sanlab.icecream.frontier.repository.storage.ImageStorage;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final IImageRepository imageRepository;
    private final ImageStorage imageStorage;
    private final IImageMapper imageMapper;

    private static final String AVATAR_PIC_NAME = "avatar";
    private static final String MEDIA_PIC_PATTERN = "media-%s";

    private ImageDto upsertAvatar(UUID id, MultipartFile img, String entityPath, String description) {
        String fileName = String.join(
            ".", AVATAR_PIC_NAME, FilenameUtils.getExtension(img.getOriginalFilename()));
        Path relativePath = imageStorage.upload(img, Path.of(entityPath, id.toString(), fileName));
        var image = imageRepository.findFirstByRelativePath(relativePath.toString())
            .orElseGet(() -> imageRepository.save(Image.builder()
                .description(description)
                .relativePath(relativePath.toString())
                .type(EImageType.AVATAR)
                .build()));
        return imageMapper.entityToDto(image);
    }

    public ImageDto upsertProductAvatar(UUID productId, MultipartFile img) {
        try {
            return upsertAvatar(
                productId, img, StoragePath.PRODUCT, "Avatar of product with id %s".formatted(productId.toString()));
        } catch (Exception ignore) {
            throw new StoringImageException();
        }
    }

    public ImageDto upsertCategoryAvatar(UUID categoryId, MultipartFile img) {
        try {
            return upsertAvatar(
                categoryId, img, StoragePath.CATEGORY, "Avatar of category with id %s".formatted(categoryId.toString()));
        } catch (Exception ignore) {
            throw new StoringImageException();
        }
    }

    public List<ImageDto> bulkUpsertProductMedia(UUID productId, MultipartFile[] media) {
        try {
            List<ImageDto> result = new ArrayList<>();
            Deque<Image> savedDQ = new ArrayDeque<>();
            for (int i=0; i<media.length; i++) {
                if (media[i].isEmpty()) {
                    continue;
                }
                String fileName = String.join(".", MEDIA_PIC_PATTERN.formatted(i),
                    FilenameUtils.getExtension(media[i].getOriginalFilename()));
                Path relativePath = imageStorage.upload(media[i], Path.of(StoragePath.PRODUCT, productId.toString(), fileName));
                imageRepository.findFirstByRelativePath(relativePath.toString())
                    .ifPresentOrElse(image -> result.add(imageMapper.entityToDto(image)), () -> {
                    var newImage = Image.builder()
                        .description("Image of product with id %s".formatted(productId.toString()))
                        .relativePath(relativePath.toString())
                        .type(EImageType.MEDIA)
                        .build();
                    savedDQ.add(newImage);
                });
            }
            var newImgs = imageRepository.saveAll(savedDQ);
            result.addAll(imageMapper.entityToDto(newImgs));
            return result;
        } catch (Exception ignore) {
            throw new StoringImageException();
        }
    }

}
