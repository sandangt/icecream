package sanlab.icecream.conflux.repository.queue;

import sanlab.icecream.conflux.model.enriched.ProductEnriched;

public interface ProductEnrichmentSinkRepository {

    void produce(ProductEnriched payload);

}
