package icecream.product.service.impl;

import icecream.product.model.Product;
import icecream.product.repository.ProductRepository;
import icecream.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> readAll() {
        return productRepository.findAll();
    }
}
