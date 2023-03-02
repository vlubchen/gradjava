package ru.vlubchen.gradjava.model;

public class Restaurant extends AbstractNamedEntity {
    private String phone;

    private String address;

    private String email;

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