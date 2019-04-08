package edu.uh.tech.cis3368.semesterproject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMER", schema = "PUBLIC", catalog = "PROJECT")
public class Customer {
    private int id;
    private String name;
    private String streetAddressLn1;
    private String streetAddressLn2;
    private String city;
    private String state;
    private String zipCode;
    private String phone;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "STREET_ADDRESS_LN1", nullable = false, length = 24)
    public String getStreetAddressLn1() {
        return streetAddressLn1;
    }

    public void setStreetAddressLn1(String streetAddressLn1) {
        this.streetAddressLn1 = streetAddressLn1;
    }

    @Basic
    @Column(name = "STREET_ADDRESS_LN2", nullable = true, length = 24)
    public String getStreetAddressLn2() {
        return streetAddressLn2;
    }

    public void setStreetAddressLn2(String streetAddressLn2) {
        this.streetAddressLn2 = streetAddressLn2;
    }

    @Basic
    @Column(name = "CITY", nullable = false, length = 24)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "STATE", nullable = false, length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "ZIP_CODE", nullable = false, length = 5)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "PHONE", nullable = false, length = 12)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(streetAddressLn1, that.streetAddressLn1) &&
                Objects.equals(streetAddressLn2, that.streetAddressLn2) &&
                Objects.equals(city, that.city) &&
                Objects.equals(state, that.state) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, streetAddressLn1, streetAddressLn2, city, state, zipCode, phone);
    }
}
