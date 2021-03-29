package Driver;

import java.io.Serializable;
import java.util.Objects;

public class Driver implements Serializable {
    private String fullname;

    public Driver(String fullname){
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "Водитель " + fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(fullname, driver.fullname);
    }
}
