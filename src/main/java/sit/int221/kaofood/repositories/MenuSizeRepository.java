package sit.int221.kaofood.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.kaofood.model.MenuSizeId;
import sit.int221.kaofood.model.Menu_has_Size;

import java.util.List;

public interface MenuSizeRepository extends JpaRepository <Menu_has_Size, MenuSizeId> {
    List<Menu_has_Size> findMenu_has_SizeByMenu_MenuId(int Id);
}
