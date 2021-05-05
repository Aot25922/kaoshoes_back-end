package sit.int221.kaofood.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sizeId;
    private String sizeValue;
    @OneToMany(mappedBy = "size",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu_has_Size> menuSize;

    public Size(int sizeId, String sizeValue, List<Menu_has_Size> menuSize) {
        this.sizeId = sizeId;
        this.sizeValue = sizeValue;
        this.menuSize = menuSize;
    }

    public Size() {
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int id) {
        this.sizeId = id;
    }

    public String getSize() {
        return sizeValue;
    }

    public void setSize(String value) {
        sizeValue = value;
    }

    public String getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(String sizeValue) {
        this.sizeValue = sizeValue;
    }

    public List<Menu_has_Size> getMenuSize() {
        return menuSize;
    }

    public void setMenuSize(List<Menu_has_Size> menusize) {
        this.menuSize = menusize;
    }
}
