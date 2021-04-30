package sit.int221.kaofood.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sit.int221.kaofood.model.Menu;
import sit.int221.kaofood.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.kaofood.service.FileUploadUtil;

import java.io.IOException;
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
    public void addMenu(@RequestParam String menu,@RequestParam MultipartFile multipartFile) throws IOException {
        Menu mymenu = null;
        try {
            mymenu = objectMapper.readValue(menu, Menu.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        mymenu.setImagePath(fileName);
        String uploadDir = "src/main/resources/image/";
        try {
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }catch (IOException ioe){
            throw new IOException("Could not save image file: ");
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
