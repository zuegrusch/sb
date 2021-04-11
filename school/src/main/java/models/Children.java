package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "childs")
public class Children {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ch_id", nullable = false)
    private int ch_id;

    @Column(name = "ch_name")
    private String ch_name;

    @Column
    private int age;

    @ManyToOne
    @JoinColumn (name="school_id")
    private School ch_school;

    @ManyToMany
    @JoinTable (name="parent_child",
            joinColumns=@JoinColumn (name="child_id"),
            inverseJoinColumns=@JoinColumn(name="parent_id"))
    private List<Parent> parents;

    public Children(String name, int age, List<Parent> parents) {
        this.age = age;
        ch_name = name;
        this.parents = parents;
    }

    public void setSchool(School school) {
        this.ch_school = school;
    }

    public Children() {}

    @Override
    public String toString() {
        return "Ребенок {" +
                "ch_id=" + ch_id +
                ", имя ='" + ch_name + '\'' +
                ", возраст =" + age +
                ", школа =" + ch_school +
                ", родители =" + parents +
                '}';
    }
}
