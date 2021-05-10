package sit.int221.kaofood.model;

import javax.persistence.*;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ProductId;
    private String productName;
    private String descript;
    private Double price;
    private String imagePath;
    private Date manuDate;
    @ManyToOne
    @JoinColumn(name="BrandId")
    private Brand brand;
    @ManyToMany
    @JoinTable(
            name = "Product_has_Size",
            joinColumns = @JoinColumn(name = "ProductId"),
            inverseJoinColumns = @JoinColumn(name = "SizeId"))
    private List<Size> sizeList;

    public Product() {
    }

    public Product(int productId, String productName, String descript, Double price, String imagePath, Date manuDate, Brand brand, List<Size> sizeList) {
        ProductId = productId;
        this.productName = productName;
        this.descript = descript;
        this.price = price;
        this.imagePath = imagePath;
        this.manuDate = manuDate;
        this.brand = brand;
        this.sizeList = sizeList;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getManuDate() {
        return manuDate;
    }

    public void setManuDate(Date manuDate) {
        this.manuDate = manuDate;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Size> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<Size> sizeList) {
        this.sizeList = sizeList;
    }
}
