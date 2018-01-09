package model.entities;


import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String firstname;
    private String password;
    private String lastname;
    private String login;
    private String email;
    private String phone;

    private String role;

    public User() {
    }

    public User(Integer userId, String firstname, String lastname,String login, String email,  String role,String phone) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login=login;
        this.email=email;
        this.role = role;
        this.phone = phone;

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getId() {
        return userId;
    }

    public void setId(Integer usersId) {
        this.userId = usersId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash += this.firstname.hashCode() + this.lastname.hashCode() + this.email.hashCode();
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

        return ((this.firstname.equals(other.getFirstname())) && (this.lastname.equals(other.getLastname())) && (this.login.equals(other.getLogin()))
                && (this.password.equals(other.getPassword()))&& (this.email.equals(other.getEmail()))
                && (this.phone.equals(other.getPhone())) && (this.role.equals(other.getRole())));
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname
                + ", login=" + login + ", password=" + password +", phone=" + phone + ", email=" + email
                + ", role=" + role +  '}';
    }
}
