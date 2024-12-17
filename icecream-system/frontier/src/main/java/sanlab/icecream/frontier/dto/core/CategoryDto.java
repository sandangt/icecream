package sanlab.icecream.frontier.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    protected UUID id;
    protected String slug;
    protected String name;
    protected String description;
    protected Long createdAt;
    protected Long modifiedAt;
}
