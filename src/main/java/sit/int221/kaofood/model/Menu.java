package sit.int221.kaofood.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuId;
    private String menuName;
    private String descript;
    private Double cost;
    private String imagePath;
    @ManyToOne
    @JoinColumn(name="CateId")
    private Category category;
    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu_has_Size> menuSize;

    public Menu() {
    }

    public Menu(int menuId, String menuName, String descript, Double cost, String imagePath, Category category, List<Menu_has_Size> menuSize) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.descript = descript;
        this.cost = cost;
        this.imagePath = imagePath;
        this.category = category;
        this.menuSize = menuSize;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }


    public void setMenuSize(List<Menu_has_Size> menusize) {
        this.menuSize = menusize;
    }
}
