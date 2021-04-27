package int221.project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Menuid;
    @Column(nullable = false)
    private String Menuname;
    private String Descript;
    @Column(nullable = false)
    private Double Costl;
    private String Image_Path;
    @ManyToOne
    @JoinColumn(name="category_cateid")
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Menu_has_size",
            joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "Menuid"),
            inverseJoinColumns = @JoinColumn(name = "size_id",
                    referencedColumnName = "SizeId"))
    private List<Size> mysize;

    public Menu() {
    }

    public Menu(int menuId, String menuname, String descript, Double costl, String image_Path) {
        Menuid = menuId;
        Menuname = menuname;
        Descript = descript;
        Costl = costl;
        Image_Path = image_Path;
    }

    public int getMenuId() {
        return Menuid;
    }

    public void setMenuId(int menuId) {
        Menuid = menuId;
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

    public List<Size> getSize() {
        return mysize;
    }
}
