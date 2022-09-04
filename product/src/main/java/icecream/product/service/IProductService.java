package icecream.product.service;

import icecream.product.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> readAll();
}
