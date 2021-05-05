package sit.int221.kaofood.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class MenuSizeId implements Serializable {
    @Column(name="MenuId")
    private int MenuId;
    @Column(name="SizeId")
    private int SizeId;

    public MenuSizeId() {
    }

    public MenuSizeId(int menuId, int sizeId) {
        MenuId = menuId;
        SizeId = sizeId;
    }

    public int getMenuId() {
        return MenuId;
    }

    public void setMenuId(int menuId) {
        MenuId = menuId;
    }

    public int getSizeId() {
        return SizeId;
    }

    public void setSizeId(int sizeId) {
        SizeId = sizeId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
