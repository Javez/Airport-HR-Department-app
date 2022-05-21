package javezProject.model;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;

@Entity
@Table(name = "users", schema = "zhulyanydb")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fName")
    private String fName;
    @Column(name = "sName")
    private String sName;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "homeAddress")
    private String homeAddress;
    @Column(name = "role")
    private String role;
    @Column(name = "password")
    private String password;

    @Autowired
    public User() {
    }

    public User(int id, String fname, String sname, String nickname, String email, String phoneNumber, String homeAddress, String role) {
        this.id = id;
        this.fName = fname;
        this.sName = sname;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fName;
    }

    public void setFname(String fname) {
        this.fName = fname;
    }

    public String getSname() {
        return sName;
    }

    public void setSname(String sname) {
        this.sName = sname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
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
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fName + '\'' +
                ", sname='" + sName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", homeAddress='" + homeAddress + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
