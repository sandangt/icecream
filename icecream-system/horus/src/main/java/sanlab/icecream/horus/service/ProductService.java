package sanlab.icecream.horus.service;

import java.util.List;
import java.util.UUID;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.CrudRepositoryService;
import lombok.RequiredArgsConstructor;
import sanlab.icecream.horus.model.Product;
import sanlab.icecream.horus.repository.ProductRepository;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class ProductService extends CrudRepositoryService<Product, UUID, ProductRepository> {

    private final ProductRepository productRepository;

    public List<Product> listTop27() {
        return productRepository.findTop27ByOrderByCreatedAt();
    }
    
}
