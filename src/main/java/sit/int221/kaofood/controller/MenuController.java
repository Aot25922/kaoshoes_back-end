package sit.int221.kaofood.controller;


import sit.int221.kaofood.model.Menu;
import sit.int221.kaofood.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/menu")
    public List<Menu> showAllMenu(){
        return menuRepository.findAll();
    }

    @GetMapping("/menu/{id}")
    public Menu showMenu(@PathVariable int id){
        return menuRepository.findById(id).orElse(null);
    }

    @RequestMapping("/menu/my/{id}")
    public void deleteMenu(@PathVariable  int id) {
        menuRepository.deleteById(id);
    }

    @RequestMapping("/menu/add")
    public void addMenu(Menu menu){
        menuRepository.save(menu);
    }
}
