package sanlab.icecream.consul.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryESearch {

    private String id;

    private String name;

    private String slug;

    private String description;

    private Long createdAt;

    private Long modifiedAt;
}
