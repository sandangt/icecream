package sanlab.icecream.fundamentum.dto.aggregated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackStatDto {

    private UUID productId;
    private Long total;
    private Double averageStar;

}
