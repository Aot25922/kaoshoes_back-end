package sit.int221.kaofood.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.kaofood.model.Category;
import sit.int221.kaofood.repositories.CategoryRepository;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public List<Category> showSize(){
        return categoryRepository.findAll();
    }
}
