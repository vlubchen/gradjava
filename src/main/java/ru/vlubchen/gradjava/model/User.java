package ru.vlubchen.gradjava.model;

import java.util.Date;
import java.util.Set;

public class User extends AbstractNamedEntity {
    private String email;
    private String password;
    private boolean enabled = true;
    private Date registered = new Date();
    private Role roles;

    public User(Integer id, String name, String email, String password, boolean enabled, Date registered, Role roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registred=" + registered +
                ", roles=" + roles +
                '}';
    }
}
