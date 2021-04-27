package sit.int221.kaofood.controller;

import sit.int221.kaofood.entity.Product;
import sit.int221.kaofood.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productrepository;

    @GetMapping("/product")
    public List<Product> product(){
        return productrepository.findAll();
    }
}
