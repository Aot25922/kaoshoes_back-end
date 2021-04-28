package sit.int221.kaofood.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.kaofood.model.Category;

public interface CategoryRepository extends JpaRepository <Category,Integer> {
}
