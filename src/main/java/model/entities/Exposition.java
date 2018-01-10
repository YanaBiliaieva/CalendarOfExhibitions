package model.entities;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

public class Exposition implements Serializable {
    private Integer id;
    private Date dateStart;
    private Date dateEnd;
    private String description;
    private String theme;
    private Integer price;
    private Integer hallId;

    public Exposition() {
    }

    public Exposition(Integer id, Date dateStart, Date dateEnd, String description, String theme, Integer price, Integer hallId) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
        this.theme = theme;
        this.price = price;
        this.hallId = hallId;
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

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash += this.id.hashCode() + this.dateStart.hashCode() + this.dateEnd.hashCode()
                + this.description.hashCode() + this.theme.hashCode() + this.hallId.hashCode();
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
                (this.hallId.equals(other.getHallId())) && (this.price.equals(other.getPrice())));
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
                ", hallId=" + hallId +
                '}';
    }
}
