package int221.project.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name="Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int MenuId;
    @Column(nullable = false)
    private String Menuname;
    private String Descript;
    @Column(nullable = false)
    private Double Costl;
    private String Image_Path;
    @ManyToOne
    @JoinColumn(name="category_cateid")
    private Category category;

    public Menu() {
    }

    public Menu(int menuId, String menuname, String descript, Double costl, String image_Path) {
        MenuId = menuId;
        Menuname = menuname;
        Descript = descript;
        Costl = costl;
        Image_Path = image_Path;
    }

    public int getMenuId() {
        return MenuId;
    }

    public void setMenuId(int menuId) {
        MenuId = menuId;
    }

    public String getMenuname() {
        return Menuname;
    }

    public void setMenuname(String menuname) {
        Menuname = menuname;
    }

    public String getDescript() {
        return Descript;
    }

    public void setDescript(String descript) {
        Descript = descript;
    }

    public Double getCostl() {
        return Costl;
    }

    public void setCostl(Double costl) {
        Costl = costl;
    }

    public String getImage_Path() {
        return Image_Path;
    }

    public void setImage_Path(String image_Path) {
        Image_Path = image_Path;
    }

    public Category getCategory() {
        return category;
    }
}
