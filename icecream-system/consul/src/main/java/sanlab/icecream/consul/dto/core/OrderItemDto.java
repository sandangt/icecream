package sanlab.icecream.consul.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    protected UUID id;
    protected Long quantity;
    protected String note;
    protected Double price;
    protected Double discount;
}
