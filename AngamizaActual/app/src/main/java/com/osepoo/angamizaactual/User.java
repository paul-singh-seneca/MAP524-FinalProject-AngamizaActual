package com.osepoo.angamizaactual;

public class User {

    private int id;
    private String empid,firstname,lastname,email,signinname,phone_no,role,status;

    public User(int id, String empid, String firstname, String lastname, String email,
                String signinname, String phone_no, String role, String status){

        this.id = id;
        this.empid = empid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.signinname = signinname;
        this.phone_no = phone_no;
        this.role = role;
        this.status = status;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
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

    public String getSigninname() {
        return signinname;
    }

    public void setSigninname(String signinname) {
        this.signinname = signinname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
