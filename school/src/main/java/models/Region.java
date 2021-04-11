package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id", nullable = false)
    private int ad_id;

    @Column(name = "ad_name", unique = true)
    private String ad_name;

    @OneToOne(mappedBy = "region")
    private School school;

    @OneToMany (mappedBy="address")
    private List<Parent> peoples;

    public Region(String name){
        this.ad_name = name;
    }

    public Region() {

    }

    public int getId() {
        return ad_id;
    }

    public String getName() {
        return ad_name;
    }

    public void setName(String name) {
        this.ad_name = name;
    }

    @Override
    public String toString() {
        return "Адрес {" +
                "id=" + ad_id +
                ", адрес='" + ad_name + '\'' +
                '}';
    }
}
