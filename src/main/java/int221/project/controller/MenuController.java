package int221.project.controller;


import int221.project.entity.Menu;
import int221.project.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class MenuController {
    @Autowired
    MenuRepository menuRepository;

    @GetMapping("/Menu")
    public List<Menu> showAllMenu(){
        return menuRepository.findAll();
    }

    @GetMapping("/Menu/{id}")
    public Menu showMenu(@PathVariable int id){
        return menuRepository.findById(id).orElse(null);
    }

    @RequestMapping("/Menu/my/{id}")
    public void deleteMenu(@PathVariable  int id) {
        menuRepository.deleteById(id);
    }
}
