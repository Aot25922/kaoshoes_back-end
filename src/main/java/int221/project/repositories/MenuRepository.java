package int221.project.repositories;

import int221.project.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository <Menu,Integer> {
}
