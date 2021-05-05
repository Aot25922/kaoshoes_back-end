package sit.int221.kaofood.model;

import javax.persistence.*;
@Entity
@Table(name = "Menu_has_Size")
public class Menu_has_Size  {
    @EmbeddedId
    private MenuSizeId Id;
    @ManyToOne
    @MapsId("MenuId")
    @JoinColumn(name = "MenuId")
    private Menu menu;
    @ManyToOne
    @MapsId("SizeId")
    @JoinColumn(name = "SizeId")
    private Size size;
    private Double Price;

    public Menu_has_Size() {
    }

    public Menu_has_Size( Menu menu, Size size, Double price) {
        Id = new MenuSizeId(menu.getMenuId(), size.getSizeId());
        this.menu = menu;
        this.size = size;
        this.Price = price;
    }

    public MenuSizeId getId() {
        return Id;
    }

    public void setId(Menu menu, Size size) {
        Id = new MenuSizeId(menu.getMenuId(), size.getSizeId());
    }


    public void setMenu(Menu menu) {
        this.menu = menu;
    }



    public void setSize(Size size) {
        this.size = size;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        this.Price = price;
    }
}
