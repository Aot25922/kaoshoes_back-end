package sit.int221.kaofood.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sizeId;
    private String sizeValue;
    @ManyToMany(mappedBy = "sizeList")
    private List<Menu> MenuList;

    public Size(int id, String value) {
        this.sizeId = id;
        this.sizeValue = value;
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


}
