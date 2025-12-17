package sanlab.icecream.consul.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ImageESearch {
    private String id;
    private String type;
    private String description;
    private String relativePath;
    private Long createdAt;
    private Long modifiedAt;
}
