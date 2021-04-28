package sit.int221.kaofood.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int menuId;
    private String menuName;
    private String descript;
    private Double cost;
    private String imagePath;
    @ManyToOne
    @JoinColumn(name="CateId")
    private Category category;
    @ManyToMany
    @JoinTable(
            name = "Menu_has_Size",
            joinColumns = @JoinColumn(name = "MenuId"),
            inverseJoinColumns = @JoinColumn(name = "SizeId"))
    private List<Size> size;

    public Menu() {
    }

    public Menu(int menuId, String menuName, String descript, Double cost, String image_Path) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.descript = descript;
        this.cost = cost;
        imagePath = image_Path;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Category getCategory() {
        return category;
    }

    public List<Size> getSize() {
        return size;
    }
}
