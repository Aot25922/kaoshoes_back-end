package sit.int221.kaofood.repositories;


import sit.int221.kaofood.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository <Menu,Integer> {
}
