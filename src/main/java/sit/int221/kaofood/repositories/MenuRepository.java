package sit.int221.kaofood.repositories;


import sit.int221.kaofood.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository <Menu,Integer> {
    List<Menu> findMenuByMenuName(String Menuname);
}
