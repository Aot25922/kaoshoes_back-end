package int221.project.controller;

import int221.project.entity.Menu;
import int221.project.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
public class MenuController {
    @Autowired
    MenuRepository menuRepository;

    @GetMapping("/Menu")
    public List<Menu> showMenu(){
        return menuRepository.findAll();
    }
}
