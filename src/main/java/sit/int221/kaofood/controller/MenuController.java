package sit.int221.kaofood.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/menu")
    public List<Menu> showAllMenu(){
        return menuRepository.findAll();
    }

    @GetMapping("/menu/{id}")
    public Menu showMenu(@PathVariable int id){
        return menuRepository.findById(id).orElse(null);
    }

    @RequestMapping("/menu/add")
    public void addMenu(@RequestParam String menu){
        Menu mymenu = null;
        try {
            mymenu = objectMapper.readValue(menu, Menu.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        menuRepository.save(mymenu);
    }

    @PutMapping("")
    public void updateMenu(@RequestBody Menu menu){

    }

    @DeleteMapping("/menu/{id}")
    public void deleteMenu(@PathVariable  int id) {
        menuRepository.deleteById(id);
    }
}
