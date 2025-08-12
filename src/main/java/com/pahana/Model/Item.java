package com.pahana.Model;

public class Item {

    private  Integer id;
    private  String image;
    private  String name;
    private  Double price;

    public Item(String fileName, String name, String price, Integer quntity, String description, String category) {
        this.image=fileName;
        this.name=name;
        this.price= Double.valueOf(price);
        this.quntity=quntity;
        this.description=description;
        this.category=category;
    }

    public Item(int id, String image, String name, Integer price, String description, String quntity, String booktype) {
        this.id=id;
        this.image=image;
        this.name=name;
        this.price= Double.valueOf(price);
        this.description=description;
        this.quntity= Integer.valueOf(quntity);
        this.category=booktype;
    }

    public Item(Integer itemid, String fileName, String iname, String price, Integer quntity, String description, String category) {
        this.id=itemid;
        this.image=fileName;
        this.name=iname;
        this.price= Double.valueOf(price);
        this.description=description;
        this.quntity= Integer.valueOf(quntity);
        this.category=category;
    }

    public Item(String fileName, String booktype) {
        this.image=fileName;
        this.category=booktype;
    }

    public Item(int id, String image, String type) {
        this.id=id;
        this.image=image;
        this.category=type;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuntity() {
        return quntity;
    }

    public void setQuntity(Integer quntity) {
        this.quntity = quntity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private  Integer quntity;
    private  String description;
    private String category;
}
