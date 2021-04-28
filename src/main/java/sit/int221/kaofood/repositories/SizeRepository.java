package sit.int221.kaofood.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.kaofood.model.Size;

public interface SizeRepository extends JpaRepository <Size,Integer> {
}
