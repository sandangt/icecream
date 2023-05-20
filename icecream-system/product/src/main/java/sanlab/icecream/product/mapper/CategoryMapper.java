package sanlab.icecream.product.mapper;

import java.util.Objects;
import java.util.UUID;

import com.google.common.base.Strings;
import icecream.sharedlib.proto.CategoryDTO;
import sanlab.icecream.product.model.Category;
import sanlab.icecream.sharedlib.datetime.DateTimeConverter;


public class CategoryMapper {
    public static CategoryDTO modelToDTO(Category category) {
        CategoryDTO.Builder builder = CategoryDTO.newBuilder();
        if (!Objects.isNull(category.getId())) builder.setId(category.getId());
        if (!Strings.isNullOrEmpty(category.getName())) builder.setName(category.getName());
        if (!Strings.isNullOrEmpty(category.getDescription())) {
            builder.setDescription(category.getDescription());
        }
        if (!Strings.isNullOrEmpty(category.getSlug())) builder.setSlug(category.getSlug());
        if (!Strings.isNullOrEmpty(category.getMetaKeyword())) {
            builder.setMetaKeyword(category.getMetaKeyword());
        }
        if (!Strings.isNullOrEmpty(category.getMetaDescription())) {
            builder.setMetaDescription(category.getMetaDescription());
        }
        if (!Objects.isNull(category.getMediaId())) {
            builder.setMediaId(Objects.toString(category.getMediaId()));
        }
        if (!Objects.isNull(category.getCreatedOn())) {
            builder.setCreatedOn(
                DateTimeConverter.OffsetDateTimeToProtobufTimestamp(category.getCreatedOn())
            );
        }
        if (!Strings.isNullOrEmpty(category.getCreatedBy())) builder.setCreatedBy(category.getCreatedBy());
        if (!Objects.isNull(category.getLastModifiedOn())) {
            builder.setLastModifiedOn(
                DateTimeConverter.OffsetDateTimeToProtobufTimestamp(category.getLastModifiedOn())
            );
        }
        if (!Strings.isNullOrEmpty(category.getLastModifiedBy())) {
            builder.setLastModifiedBy(category.getLastModifiedBy());
        }
        return builder.build();
    }
    public static Category dtoToModel(CategoryDTO categoryDTO) {
        Category.CategoryBuilder builder = Category.builder();
        if (categoryDTO.hasId()) builder.id(categoryDTO.getId());
        if (categoryDTO.hasName()) builder.name(categoryDTO.getName());
        if (categoryDTO.hasDescription()) builder.description(categoryDTO.getDescription());
        if (categoryDTO.hasSlug()) builder.slug(categoryDTO.getSlug());
        if (categoryDTO.hasMetaKeyword()) builder.metaKeyword(categoryDTO.getMetaKeyword());
        if (categoryDTO.hasMetaDescription()) builder.metaDescription(categoryDTO.getMetaDescription());
        if (categoryDTO.hasMediaId()) builder.mediaId(UUID.fromString(categoryDTO.getMediaId()));
        return builder.build();
    }
}
