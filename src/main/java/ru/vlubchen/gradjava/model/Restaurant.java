package ru.vlubchen.gradjava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "phone")
    @NotBlank
    @Size(max = 25)
    private String phone;

    @Column(name = "address")
    @NotBlank
    @Size(max = 255)
    private String address;

    @Column(name = "email")
    @Email
    @NotBlank
    @Size(max = 128)
    private String email;

    public Restaurant() {
    }

    public Restaurant(String name, String phone, String address, String email) {
        this(null, name, phone, address, email);
    }

    public Restaurant(Integer id, String name, String phone, String address, String email) {
        super(id, name);
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}