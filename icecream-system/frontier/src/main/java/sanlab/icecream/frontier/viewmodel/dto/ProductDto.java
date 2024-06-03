package sanlab.icecream.frontier.viewmodel.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.frontier.constant.EProductStatus;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    protected UUID id;
    @NotBlank
    protected String name;
    protected String description;
    protected Double price;
    protected EProductStatus status;
    protected Long quantity;
    protected Long createdAt;
    protected Long modifiedAt;
}
