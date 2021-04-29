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
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("")
    public List<Menu> showAllMenu(){
        return menuRepository.findAll();
    }

    @GetMapping("/{id}")
    public Menu showMenu(@PathVariable int id){
        return menuRepository.findById(id).orElse(null);
    }

    @PostMapping ("")
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

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable  int id) {
        menuRepository.deleteById(id);
    }
}
