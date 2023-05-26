package sanlab.icecream.gateway.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import sanlab.icecream.gateway.service.product.CategoryService;
import sanlab.icecream.gateway.service.product.ProductService;
import sanlab.icecream.gateway.viewmodel.PageInfoRequestVm;
import sanlab.icecream.gateway.viewmodel.ResponseVm;
import sanlab.icecream.gateway.viewmodel.product.CategoryResponseVm;
import sanlab.icecream.gateway.viewmodel.product.CategoryVm;
import sanlab.icecream.gateway.viewmodel.product.ProductResponseVm;
import sanlab.icecream.gateway.viewmodel.product.ProductVm;

@CrossOrigin
@Controller
@RequiredArgsConstructor
public class GraphQLResolver {
    private final ProductService productService;
    private final CategoryService categoryService;

    // region Product
    @SchemaMapping(typeName = "Query", field = "allProducts")
    public List<ProductResponseVm> getAllProducts(@Argument("pageInfo") PageInfoRequestVm pageInfo) {
        return productService.getAllProducts(pageInfo);
    }

    @SchemaMapping(typeName = "Query", field = "productById")
    public ProductResponseVm getProductById(@Argument("id") Long id) {
        return productService.getProductById(id);
    }

    @SchemaMapping(typeName = "Mutation", field = "insertProduct")
    public ResponseVm insertProduct(@Argument("product") ProductVm product) {
        productService.insertProduct(product);
        return new ResponseVm(true);
    }

    @SchemaMapping(typeName="Mutation", field = "updateProduct")
    public ResponseVm updateProduct(@Argument("product") ProductVm product) {
        productService.updateProduct(product);
        return new ResponseVm(true);
    }
    // endregion

    // region Category
    @SchemaMapping(typeName = "Query", field = "allCategories")
    public List<CategoryResponseVm> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @SchemaMapping(typeName = "Query", field = "categoryById")
    public CategoryResponseVm getCategoryById(@Argument("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @SchemaMapping(typeName = "Mutation", field = "insertCategory")
    public ResponseVm insertCategory(@Argument("category") CategoryVm category) {
        categoryService.insertCategory(category);
        return new ResponseVm(true);
    }

    @SchemaMapping(typeName="Mutation", field = "updateCategory")
    public ResponseVm updateCategory(@Argument("category") CategoryVm category) {
        categoryService.updateCategory(category);
        return new ResponseVm(true);
    }
    // endregion
}
