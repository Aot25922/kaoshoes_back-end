package sit.int221.kaofood.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandId;
    private String brandName;
    @OneToMany(mappedBy = "brand")
    private List<Product> productList;

    public Brand() {
    }

    public Brand(int brandId, String brandName, List<Product> productList) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.productList = productList;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
