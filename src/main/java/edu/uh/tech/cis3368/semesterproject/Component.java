package edu.uh.tech.cis3368.semesterproject;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Objects;

@Entity
public class Component {
    private int id;
    private String name;
    private String description;
    private BigInteger wholesalePrice;

    @Override
    public String toString() {
        return String.format("%s", name);
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 24)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "WHOLESALE_PRICE", nullable = false, precision = 0)
    public BigInteger getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigInteger wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return id == component.id &&
                Objects.equals(name, component.name) &&
                Objects.equals(description, component.description) &&
                Objects.equals(wholesalePrice, component.wholesalePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, wholesalePrice);
    }
}
