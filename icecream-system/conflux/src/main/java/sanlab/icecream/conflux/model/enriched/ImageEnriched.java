package sanlab.icecream.conflux.model.enriched;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEnriched {

    private String id;
    private String description;
    private String relativePath;
    private String type;
    private Long createdAt;
    private Long modifiedAt;

}
