package model.entities;


import java.io.Serializable;

public class User implements Serializable {

    private int userId;
    private String firstname;
    private String password;
    private String lastname;
    private String login;
    private String email;
    private String phone;
    private Integer balance;
    private String role;

    public User() {
    }

    public User(Integer userId, String firstname, String password, String lastname, String login, String email,
                String phone, Integer balance, String role) {
        this.userId = userId;
        this.firstname = firstname;
        this.password = password;
        this.lastname = lastname;
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash += this.firstname.hashCode() + this.lastname.hashCode() + this.email.hashCode() + this.login.hashCode();
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if (userId != other.userId) return false;

        return ((this.firstname.equals(other.getFirstname())) && (this.lastname.equals(other.getLastname())) && (this.login.equals(other.getLogin()))
                && (this.password.equals(other.getPassword())) && (this.email.equals(other.getEmail()))
                && (this.phone.equals(other.getPhone())) && (this.role.equals(other.getRole())));
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstname='" + firstname + '\'' +
                ", password='" + password + '\'' +
                ", lastname='" + lastname + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", balance=" + balance +
                ", role='" + role + '\'' +
                '}';
    }
}
