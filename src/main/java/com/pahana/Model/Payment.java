package com.pahana.Model;

import javax.xml.crypto.Data;
import java.sql.Date;

public class Payment {

    private  Integer id;
    private  Integer mobile;
    private  Double subtotal;
    private  Double amount;
    private  Double balance;
    private  String item;
    private  Integer quntity;
    private  Double price;
    private Integer cusid;

    public Payment(int cusid, String cusname, String total)
    {
        this.id= cusid;
        this.item=cusname;
        this.subtotal= Double.valueOf(total);
    }



    public Payment(int i, Date dadate, String total) {
        this.id=i;
        this.date= dadate.toString();
        this.subtotal= Double.valueOf(total);
    }

    public Payment(int i, String cusname, double subtotal)
    {
        this.id= i;
        this.item=cusname;
        this.subtotal= subtotal;
    }


    public Integer getCusid() {
        return cusid;
    }

    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private  String date;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuntity() {
        return quntity;
    }

    public void setQuntity(Integer quntity) {
        this.quntity = quntity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public Payment(Integer id,Integer cusid, String name, Integer quntity, String price,String date, Double subtotal, Double amount, Double balance)
    {
       this.id=id;
       this.cusid=cusid;
       this.item=name;
       this.quntity=quntity;
       this.price= Double.valueOf(price);
       this.date=date;
       this.subtotal = subtotal;
       this.amount= amount;
       this.balance=balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }



    public Payment(Integer mobile, Double subtotal, Double amount, Double balance) {
        this.mobile = mobile;
        this.subtotal = subtotal;
        this.amount= amount;
        this.balance=balance;
    }
}
