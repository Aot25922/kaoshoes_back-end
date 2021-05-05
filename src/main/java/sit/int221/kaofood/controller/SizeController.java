package sit.int221.kaofood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.kaofood.model.Brand;
import sit.int221.kaofood.model.Size;
import sit.int221.kaofood.repositories.SizeRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/size")
public class SizeController {
    @Autowired
    private SizeRepository sizeRepository;
    @GetMapping("")
    public List<Size> showAllBrand(){
        return sizeRepository.findAll();
    }

}
