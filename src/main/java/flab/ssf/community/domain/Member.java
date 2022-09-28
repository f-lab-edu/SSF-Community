package flab.ssf.community.domain;


import java.util.Date;


public class Member {


    private Long id;

    private String uid;

    private String pw;

    private String email;

    private String phone;

    private char grade;

    private Date date;

    private String address;

    private String name;

    private char role;

    private char enabled;

    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
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

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }

    public char getEnabled() {
        return enabled;
    }

    public void setEnabled(char enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", pw='" + pw + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", grade=" + grade +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                '}';
    }
}
