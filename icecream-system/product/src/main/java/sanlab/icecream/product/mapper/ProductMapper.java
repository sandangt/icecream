package sanlab.icecream.product.mapper;

import java.util.Objects;
import java.util.UUID;

import com.google.common.base.Strings;
import icecream.sharedlib.proto.ProductDTO;
import sanlab.icecream.product.model.Product;
import sanlab.icecream.sharedlib.datetime.DateTimeConverter;


public class ProductMapper {
    public static ProductDTO modelToDTO(Product product) {
        ProductDTO.Builder builder = ProductDTO.newBuilder();
        if (!Objects.isNull(product.getId())) builder.setId(product.getId());
        if (!Strings.isNullOrEmpty(product.getName())) builder.setName(product.getName());
        if (!Strings.isNullOrEmpty(product.getBriefDescription())) {
            builder.setBriefDescription(product.getBriefDescription());
        }
        if (!Strings.isNullOrEmpty(product.getDescription())) {
            builder.setDescription(product.getDescription());
        }
        if (!Strings.isNullOrEmpty(product.getSku())) builder.setSku(product.getSku());
        if (!Strings.isNullOrEmpty(product.getSlug())) builder.setSlug(product.getSlug());
        if (!Objects.isNull(product.getPrice())) builder.setPrice(product.getPrice());
        if (!Objects.isNull(product.getStockQuantity())) builder.setStockQuantity(product.getStockQuantity());
        if (!Strings.isNullOrEmpty(product.getMetaTitle())) {
            builder.setMetaTitle(product.getMetaTitle());
        }
        if (!Strings.isNullOrEmpty(product.getMetaKeyword())) {
            builder.setMetaKeyword(product.getMetaKeyword());
        }
        if (!Strings.isNullOrEmpty(product.getMetaDescription())) {
            builder.setMetaDescription(product.getMetaDescription());
        }
        if (!Objects.isNull(product.getMediaId())) {
            builder.setMediaId(Objects.toString(product.getMediaId()));
        }
        if (!Objects.isNull(product.getCreatedOn())) {
            builder.setCreatedOn(
                DateTimeConverter.OffsetDateTimeToProtobufTimestamp(product.getCreatedOn())
            );
        }
        if (!Strings.isNullOrEmpty(product.getCreatedBy())) builder.setCreatedBy(product.getCreatedBy());
        if (!Objects.isNull(product.getLastModifiedOn())) {
            builder.setLastModifiedOn(
                DateTimeConverter.OffsetDateTimeToProtobufTimestamp(product.getLastModifiedOn())
            );
        }
        if (!Strings.isNullOrEmpty(product.getLastModifiedBy())) builder.setLastModifiedBy(product.getLastModifiedBy());
        return builder.build();
    }
    public static Product dtoToModel(ProductDTO productDTO) {
        Product.ProductBuilder builder = Product.builder();
        if (productDTO.hasId()) builder.id(productDTO.getId());
        if (productDTO.hasName()) builder.name(productDTO.getName());
        if (productDTO.hasBriefDescription()) builder.briefDescription(productDTO.getBriefDescription());
        if (productDTO.hasDescription()) builder.description(productDTO.getDescription());
        if (productDTO.hasSku()) builder.sku(productDTO.getSku());
        if (productDTO.hasSlug()) builder.slug(productDTO.getSlug());
        if (productDTO.hasPrice()) builder.price(productDTO.getPrice());
        if (productDTO.hasStockQuantity()) builder.stockQuantity(productDTO.getStockQuantity());
        if (productDTO.hasMetaTitle()) builder.metaTitle(productDTO.getMetaTitle());
        if (productDTO.hasMetaKeyword()) builder.metaKeyword(productDTO.getMetaKeyword());
        if (productDTO.hasMetaDescription()) builder.metaDescription(productDTO.getMetaDescription());
        if (productDTO.hasMediaId()) builder.mediaId(UUID.fromString(productDTO.getMediaId()));
        return builder.build();
    }
}
