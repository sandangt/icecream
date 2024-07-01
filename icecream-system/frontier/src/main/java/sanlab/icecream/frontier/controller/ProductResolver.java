package sanlab.icecream.frontier.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import sanlab.icecream.frontier.dto.extended.ProductExtendedDto;
import sanlab.icecream.frontier.service.CategoryService;
import sanlab.icecream.frontier.service.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductResolver {

    private final ProductService productService;
    private final CategoryService categoryService;

    @SchemaMapping(typeName = "Query", field = "allProducts")
    public void getAllProducts() {

    }

}
