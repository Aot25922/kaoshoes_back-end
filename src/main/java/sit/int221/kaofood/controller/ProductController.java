package sit.int221.kaofood.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sit.int221.kaofood.model.Product;
import sit.int221.kaofood.repositories.ProductRepository;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("")
    public List<Product> showAllMenu(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product showMenu(@PathVariable int id){
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping ("")
    public void addMenu(@RequestParam String product,@RequestParam MultipartFile multipartFile) throws IOException {
        Product myproduct = null;
        try {
            myproduct = objectMapper.readValue(product, Product.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if((productRepository.findMenuByBrand_BrandName(myproduct.getProductName())).equals(myproduct)){
            return;
        }
        String type;
        try{
            String oldname = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        type = oldname.substring(oldname.lastIndexOf("."));
        }
        catch (Exception e){ type=".png";}
        String fileName = myproduct.getProductName()+type;;
        String uploadDir="target/classes/image/"+myproduct.getBrand().getBrandName()+"/";
        myproduct.setImagePath(uploadDir+fileName);
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
        productRepository.save(myproduct);
    }

    @GetMapping("/image/{image}")
    public ResponseEntity<InputStreamResource> showImage(@PathVariable String image) throws IOException {
        List<Product> products = productRepository.findByProductName(image);
        String imagePath = products.get(0).getImagePath().substring(products.get(0).getImagePath().lastIndexOf("/image/"));
        var imgFile = new ClassPathResource(imagePath);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }


    @PutMapping("/{id}")
    public void updateMenu(@PathVariable(value = "id") int Id,@RequestParam String product)throws IOException{
       Product editProduct = productRepository.findById(Id).orElse(null);
       Product myproduct = null;
        try {
            myproduct = objectMapper.readValue(product, Product.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        editProduct.setProductName(myproduct.getProductName());
        editProduct.setPrice(myproduct.getPrice());
        editProduct.setDescript(myproduct.getDescript());
        editProduct.setManuDate(myproduct.getManuDate());
        editProduct.setBrand(myproduct.getBrand());
        editProduct.setSizeList(myproduct.getSizeList());
        productRepository.save(editProduct);
    }

    @PutMapping("/image/{id}")
    public void updateImage(@PathVariable(value = "id") int Id,@RequestParam MultipartFile multipartFile)throws IOException{
        Product editProduct = productRepository.findById(Id).orElse(null);
            String type;
            try{
                String oldname = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                type = oldname.substring(oldname.lastIndexOf("."));
            }
            catch (Exception e){ type=".png";}
            String fileName = editProduct.getProductName()+type;;
            String uploadDir="target/classes/image/"+ editProduct.getBrand().getBrandName()+"/";
            editProduct.setImagePath(uploadDir+fileName);
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


        productRepository.save(editProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable  int id) {
        productRepository.deleteById(id);
    }
}
