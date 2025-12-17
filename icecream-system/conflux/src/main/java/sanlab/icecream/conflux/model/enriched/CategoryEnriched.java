package sanlab.icecream.conflux.model.enriched;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEnriched {

    private String id;
    private String slug;
    private String name;
    private String description;
    private Long createdAt;
    private Long modifiedAt;

}
