package com.pahana.Model;

public class Customer {
    private  Integer id;
    private  String name;
    private  String image;
    private  String address;
    private  Integer mobile;
    private  String email;
    private  String status;



    public Customer(String fileName, String name, String email, Integer mobile, String address) {
        this.image=fileName;
        this.name = name;
        this.email =email;
        this.mobile = mobile;
        this.address =address;
    }

    public Customer(int id, String image, String name, Integer mobile, String address, String email, String status) {
        this.id=id;
        this.image =image;
        this.name = name;
        this.email =email;
        this.mobile = mobile;
        this.address =address;
        this.status = status;
    }

    public Customer(Integer custid, String fileName, String ename, String eemail, Integer emobile, String eaddress) {
        this.id=custid;
        this.image =fileName;
        this.name = ename;
        this.email =eemail;
        this.mobile = emobile;
        this.address =eaddress;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public  String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public  Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
