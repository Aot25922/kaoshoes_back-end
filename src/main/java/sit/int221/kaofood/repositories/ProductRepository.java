package sit.int221.kaofood.repositories;

import sit.int221.kaofood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
