package int221.project.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int CateId;
    @Column(nullable=false)
    private String CateName;
    @OneToMany(mappedBy = "category")
    private List<Menu> MenuList;

    public Category() {
    }

    public Category(int cateId, String cateName) {
        CateId = cateId;
        CateName = cateName;
    }

    public int getCateId() {
        return CateId;
    }

    public void setCateId(int cateId) {
        CateId = cateId;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String cateName) {
        CateName = cateName;
    }
}
