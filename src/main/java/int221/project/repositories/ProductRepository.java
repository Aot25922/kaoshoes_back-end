package int221.project.repositories;

import int221.project.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<product, Integer>{

}
