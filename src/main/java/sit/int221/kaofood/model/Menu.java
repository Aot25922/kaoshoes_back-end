package sit.int221.kaofood.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MenuId;
    private String menuName;
    private String descript;
    private Double cost;
    private Double price;
    private String imagePath;
    @ManyToOne
    @JoinColumn(name="CateId")
    private Category category;
    @ManyToMany
    @JoinTable(
            name = "Menu_has_Size",
            joinColumns = @JoinColumn(name = "MenuId"),
            inverseJoinColumns = @JoinColumn(name = "SizeId"))
    private List<Size> sizeList;

    public Menu() {
    }

    public Menu(int menuId,String menuName,Double price, String descript, Double cost, String image_Path,Category category,List<Size> sizeList) {
        this.MenuId=menuId;
        this.menuName = menuName;
        this.descript = descript;
        this.cost = cost;
        imagePath = image_Path;
        this.category=category;
        this.price=price;
        this.sizeList=sizeList;
    }

    public List<Size> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<Size> sizeList) {
        this.sizeList = sizeList;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getMenuId() {
        return MenuId;
    }

    public void setMenuId(int menuId) {
        this.MenuId = menuId;
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
}
