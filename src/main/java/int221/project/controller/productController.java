package int221.project.controller;

import int221.project.entity.product;
import int221.project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class productController {
    @Autowired
    ProductRepository productrepository;

    @GetMapping
    public List<product> product(){
        return productrepository.findAll();
    }
}
