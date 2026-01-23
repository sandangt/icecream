package sanlab.icecream.horus.service;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.vaadin.hilla.BrowserCallable;
import sanlab.icecream.horus.repository.ProductRepository;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;



}
