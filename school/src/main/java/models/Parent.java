package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parents")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "par_id", nullable = false)
    private int par_id;

    @Column(name = "par_name")
    private String par_name;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="ad_id")
    private Region address;

    @ManyToMany
    @JoinTable (name="parent_child",
            joinColumns=@JoinColumn (name="parent_id"),
            inverseJoinColumns=@JoinColumn(name="child_id"))
    private List<Children> kids;

    public Parent(String par_name, Region address){
        this.par_name = par_name;
        this.address = address;
    }

    public Parent(){
    }

    public void setRegion(Region region) {
        this.address = region;
    }

    @Override
    public String toString() {
        return "Родитель {" +
                "par_id=" + par_id +
                ", имя ='" + par_name + '\'' +
                ", адрес =" + address +
                "" +
                '}';
    }
}
