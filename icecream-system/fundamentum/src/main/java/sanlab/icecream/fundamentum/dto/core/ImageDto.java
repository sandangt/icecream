package sanlab.icecream.fundamentum.dto.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sanlab.icecream.fundamentum.constant.EImageType;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    protected UUID id;
    protected String description;
    protected String relativePath;
    protected EImageType type;
    protected Long createdAt;
    protected Long modifiedAt;
}
