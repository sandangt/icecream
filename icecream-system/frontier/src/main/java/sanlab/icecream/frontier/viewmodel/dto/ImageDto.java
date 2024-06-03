package sanlab.icecream.frontier.viewmodel.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sanlab.icecream.frontier.constant.EImageType;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    protected UUID id;
    protected String description;
    @NotBlank
    protected String relativePath;
    protected EImageType type;
    protected Long createdAt;
    protected Long modifiedAt;
}
