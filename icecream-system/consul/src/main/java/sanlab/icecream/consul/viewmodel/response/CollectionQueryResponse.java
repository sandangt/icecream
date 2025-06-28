package sanlab.icecream.consul.viewmodel.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CollectionQueryResponse<T> {

    private long total;
    private long page;
    private long totalPages;
    private List<T> data;

}
