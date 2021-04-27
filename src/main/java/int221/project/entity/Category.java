package int221.project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Cateid;
    @Column(nullable=false)
    private String Catename;
    @OneToMany(mappedBy = "category")
    private List<Menu> MenuList;

    public Category() {
    }

    public Category(int cateId, String cateName) {
        Cateid = cateId;
        Catename = cateName;
    }

    public int getCateId() {
        return Cateid;
    }

    public void setCateId(int cateId) {
        Cateid = cateId;
    }

    public String getCateName() {
        return Catename;
    }

    public void setCateName(String cateName) {
        Catename = cateName;
    }
}
