package sit.int221.kaofood.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sit.int221.kaofood.exeption.exceptionResponse;
import sit.int221.kaofood.exeption.productException;
import sit.int221.kaofood.model.Product;
import sit.int221.kaofood.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
            throw  new productException(exceptionResponse.ERROR_CODE.PRODUCT_CANNOT_CREATE,"Cannot map JSON"+product+"value to product class or" +e);
        }
        if((productRepository.findMenuByBrand_BrandName(myproduct.getProductName())).equals(myproduct)){
            return;
        }
        String type;
        try{ String oldname = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        type = oldname.substring(oldname.lastIndexOf("."));
        }
        catch (Exception e){
            throw  new productException(exceptionResponse.ERROR_CODE.SAVE_OLD_EXTENSION,"Can't save old file type name or"+e);
        }
        String fileName = myproduct.getProductName()+type;;
        String uploadDir="./storage/image/";
        myproduct.setImagePath(uploadDir+fileName);
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw  new productException(exceptionResponse.ERROR_CODE.ERROR_TO_SAVE_FILE,"Can't save file to storage or"+ioe);
        }
        productRepository.save(myproduct);
    }

    @GetMapping("/image/{image}")
    public void getImageAsByteArray(HttpServletResponse response, @PathVariable String image) throws IOException {
        List<Product> products=productRepository.findByProductName(image);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        try(FileInputStream in = new FileInputStream(String.valueOf(products.get(0).getImagePath()));
            OutputStream out = response.getOutputStream()) {
            IOUtils.copy(in, out);
        }catch (IOException ioe){
            throw  new productException(exceptionResponse.ERROR_CODE.FILE_NOT_FOUND,"Can't show image from storage or"+ioe);
        }

    }


    @PutMapping("/{id}")
    public void updateMenu(@PathVariable(value = "id") int Id,@RequestParam String product)throws IOException{
       Product editProduct = productRepository.findById(Id).orElse(null);
       Product myproduct = null;
        try {
            myproduct = objectMapper.readValue(product, Product.class);
        } catch (JsonProcessingException e) {
            throw  new productException(exceptionResponse.ERROR_CODE.PRODUCT_CANNOT_CREATE,"Cannot map JSON"+product+"value to product class or" +e);
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
            catch (Exception e){
                throw  new productException(exceptionResponse.ERROR_CODE.SAVE_OLD_EXTENSION,"Can't save old file type name or"+e);
            }
        File checkFile=new File(editProduct.getImagePath());
            if(checkFile.exists()) {
                try {
                    Path oldImgPath = Paths.get(editProduct.getImagePath());
                    Files.delete(oldImgPath);
                } catch (IOException ioe) {
                    throw new productException(exceptionResponse.ERROR_CODE.ERROR_TO_DELETE_FILE, "Can't delete this file from storage or" + ioe);
                }
            }
            String fileName = editProduct.getProductName()+type;;
            String uploadDir="./storage/image/";
            editProduct.setImagePath(uploadDir+fileName);
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                throw  new productException(exceptionResponse.ERROR_CODE.ERROR_TO_SAVE_FILE,"Can't save file to storage or"+ioe);
            }
        productRepository.save(editProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable  int id) {
        productRepository.deleteById(id);
    }
}
