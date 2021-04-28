package sit.int221.kaofood.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Sizeid;
    @Column(nullable = false)
    private Character Sizename;
    @ManyToMany(mappedBy = "mysize")
    private List<Menu> MenuList;

    public Size(int sizeid, Character size) {
        Sizeid = sizeid;
        Sizename = size;
    }

    public Size() {
    }

    public int getSizeid() {
        return Sizeid;
    }

    public void setSizeid(int sizeid) {
        Sizeid = sizeid;
    }

    public Character getSize() {
        return Sizename;
    }

    public void setSize(Character size) {
        Sizename = size;
    }


}
