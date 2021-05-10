package sit.int221.kaofood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.kaofood.model.Brand;
import sit.int221.kaofood.repositories.BrandRepository;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/brand")
public class BrandContoller {
    @Autowired
    private BrandRepository brandRepository;
    @GetMapping("")
    public List<Brand> showAllBrand(){
        return brandRepository.findAll();
    }
}
