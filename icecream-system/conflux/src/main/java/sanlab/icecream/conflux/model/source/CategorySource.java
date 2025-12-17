package sanlab.icecream.conflux.model.source;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorySource {

    private String id;
    private String slug;
    private String name;
    private String description;

    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("modified_at")
    private Long modifiedAt;

    @JsonProperty("__op")
    private String operation;
    @JsonProperty("__ts_ms")
    private Long tsMs;
    @JsonProperty("__deleted")
    private String deleted;

}
