package sit.int221.kaofood.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.kaofood.model.Size;
import sit.int221.kaofood.repositories.SizeRepository;

import java.util.List;

@RestController
public class SizeController {
    @Autowired
    private SizeRepository sizeRepository;

    @GetMapping("/size")
    public List<Size> showSize(){
        return sizeRepository.findAll();
    }
}
