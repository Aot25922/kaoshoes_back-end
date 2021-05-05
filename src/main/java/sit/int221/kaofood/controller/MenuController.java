package sit.int221.kaofood.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sit.int221.kaofood.model.Menu;
import sit.int221.kaofood.model.Menu_has_Size;
import sit.int221.kaofood.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.kaofood.repositories.MenuSizeRepository;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuSizeRepository menuSizeRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("")
    public List<Menu> showAllMenu(){
        return menuRepository.findAll();
    }

    @GetMapping("/size")
    public List<Menu_has_Size> showAllMenuSize(){
        return menuSizeRepository.findAll();
    }

    @GetMapping("/size/{id}")
    public List<Menu_has_Size> showAllMenuSize(@PathVariable int id){
        return menuSizeRepository.findMenu_has_SizeByMenu_MenuId(id);
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
        if((menuRepository.findMenuByMenuName(mymenu.getMenuName())).equals(mymenu)){
            return;
        }
        String type;
        try{
            String oldname = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        type = oldname.substring(oldname.lastIndexOf("."));
        }
        catch (Exception e){ type=".png";}
        String fileName = mymenu.getMenuName()+type;;
        String uploadDir="target/classes/image/"+mymenu.getCategory().getCateName()+"/";
        mymenu.setImagePath(uploadDir+fileName);
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
        menuRepository.save(mymenu);
    }

    @GetMapping("/get/{image}")
    public ResponseEntity<InputStreamResource> showImage(@PathVariable String image) throws IOException {
        List<Menu> menu = menuRepository.findMenuByMenuName(image);
        String imagePath = menu.get(0).getImagePath().substring(menu.get(0).getImagePath().lastIndexOf("/image/"));
        var imgFile = new ClassPathResource(imagePath);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }


    @PutMapping("/{id}")
    public void updateMenu(@PathVariable(value = "id") int Id,@RequestParam String menu)throws IOException{
       Menu editMenu = menuRepository.findById(Id).orElse(null);
       Menu mymenu = null;
        try {
            mymenu = objectMapper.readValue(menu, Menu.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        editMenu.setMenuName(mymenu.getMenuName());
        editMenu.setCost(mymenu.getCost());
        editMenu.setDescript(mymenu.getDescript());
        editMenu.setCategory(mymenu.getCategory());
        menuRepository.save(editMenu);
    }

    @PutMapping("/image/{id}")
    public void updateImage(@PathVariable(value = "id") int Id,@RequestParam MultipartFile multipartFile)throws IOException{
        Menu editMenu = menuRepository.findById(Id).orElse(null);
            String type;
            try{
                String oldname = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                type = oldname.substring(oldname.lastIndexOf("."));
            }
            catch (Exception e){ type=".png";}
            String fileName = editMenu.getMenuName()+type;;
            String uploadDir="target/classes/image/"+editMenu.getCategory().getCateName()+"/";
            editMenu.setImagePath(uploadDir+fileName);
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                throw new IOException("Could not save image file: " + fileName, ioe);
            }


        menuRepository.save(editMenu);
    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable  int id) {
        menuRepository.deleteById(id);
    }
}
