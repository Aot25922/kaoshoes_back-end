package sit.int221.kaofood.repositories;


import sit.int221.kaofood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product,Integer> {
    List<Product> findMenuByBrand_BrandName(String Brandname);
    List<Product> findByProductName(String name);
}
