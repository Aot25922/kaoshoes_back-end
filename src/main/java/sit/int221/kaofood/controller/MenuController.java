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
import sit.int221.kaofood.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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
        String type;
        try{
            String oldname = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        type = oldname.substring(oldname.lastIndexOf("."));
        }
        catch (Exception e){ type=".png";}
        String fileName = mymenu.getMenuName()+type;;
        mymenu.setImagePath(fileName);
//        String uploadDir = "src/main/resources/image/";
        Path uploadPath = Paths.get("src/main/resources/image/");
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
        Menu menu = null;
        for(Menu find : menuRepository.findMenuByMenuName(image)){
            if(find.getImagePath().replaceFirst("[.][^.]+$", "").equals(find.getMenuName())){
                menu=find;
            }
        }
        var imgFile = new ClassPathResource("image/"+ menu.getImagePath());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }


    @PutMapping("")
    public void updateMenu(@RequestBody Menu menu){

    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable  int id) {
        menuRepository.deleteById(id);
    }
}
