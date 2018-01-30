package model.entities;

import java.io.Serializable;
import java.util.Date;


public class Exposition implements Serializable {
    private Integer id;
    private Date dateStart;
    private Date dateEnd;
    private String description;
    private String theme;
    private Integer price;
    private String hallName;
    private String hallCity;
    private String hallAddress;
    private Integer ticketsAvailable;

    public Exposition() { }


    public Exposition(Integer id, Date dateStart, Date dateEnd, String description, String theme, Integer price,
                      String hallName, String hallCity, String hallAddress, Integer ticketsAvailable) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
        this.theme = theme;
        this.price = price;
        this.hallName = hallName;
        this.hallCity = hallCity;
        this.hallAddress = hallAddress;
        this.ticketsAvailable = ticketsAvailable;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getHallCity() {
        return hallCity;
    }

    public void setHallCity(String hallCity) {
        this.hallCity = hallCity;
    }

    public String getHallAddress() {
        return hallAddress;
    }

    public void setHallAddress(String hallAddress) {
        this.hallAddress = hallAddress;
    }

    public Integer getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(Integer ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }



    @Override
    public int hashCode() {
        int hash = 31;
        hash += this.dateStart.hashCode() + this.dateEnd.hashCode()
                + this.description.hashCode() + this.theme.hashCode();
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
        if (!(object instanceof Exposition)) {
            return false;
        }
        Exposition other = (Exposition) object;
        return ((this.theme.equals(other.getTheme())) && (this.description.equals(other.getDescription())) &&
                (this.dateEnd.equals(other.getDateEnd())) && (this.dateStart.equals(other.getDateStart())) &&
                (this.hallName.equals(other.getHallName())) && (this.price.equals(other.getPrice())));
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", description='" + description + '\'' +
                ", theme='" + theme + '\'' +
                ", price=" + price +
                ", hallName='" + hallName + '\'' +
                ", hallCity='" + hallCity + '\'' +
                ", hallAddress='" + hallAddress + '\'' +
                ", ticketsAvailable=" + ticketsAvailable +
                '}';
    }
}
