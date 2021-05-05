package sit.int221.kaofood.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.kaofood.model.Brand;

public interface BrandRepository extends JpaRepository<Brand , Integer > {
}
