package exhibitions.model.entities;

import java.io.Serializable;

public class Hall implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private String city;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Hall() {
    }

    public Hall(Integer id, String name, String address, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash += this.id.hashCode() + this.name.hashCode() + this.address.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof Hall)) {
            return false;
        }
        Hall other = (Hall) object;
        return ((this.name.equals(other.getName())) && (this.address.equals(other.getAddress())));

    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
