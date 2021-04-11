package models;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sc_id")
    private int id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "fk_ad_id", referencedColumnName = "ad_id")
    private Region region;

    @OneToMany (mappedBy="ch_school", fetch=FetchType.LAZY)
    private List<Children> students;

    public School(String name, Region region){
        this.name = name;
        this.region = region;
    }

    public School() {}

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Школа {" +
                "id=" + id +
                ", номер ='" + name + '\'' +
                ", адрес =" + region +
                '}';
    }
}
