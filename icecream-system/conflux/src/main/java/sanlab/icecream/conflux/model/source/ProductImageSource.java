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
public class ProductImageSource {

    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("image_id")
    private String imageId;

    @JsonProperty("__op")
    private String operation;
    @JsonProperty("__ts_ms")
    private Long tsMs;
    @JsonProperty("__deleted")
    private String deleted;

}
