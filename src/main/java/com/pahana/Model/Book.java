package com.pahana.Model;

public class Book {
	
	private int id;
	private String image;
	private String name;
	private String type;
	private Double price;
	private String description;
	private int quntity;
	private Double Subtotal;

	public Double getSubtotal() {
		return Subtotal;
	}

	public void setSubtotal(Double subtotal) {
		Subtotal = subtotal;
	}



	public Book(Integer quntityid, Integer quntity)
	{
		this.id=quntityid;
		this.quntity=quntity;
	}

    public Book(String item,Double price, int qty ,Double total )
	{
		this.name=item;
		this.price= price;
		this.quntity= qty;
		this.Subtotal=total;
    }

    public int getQuntity() {
		return quntity;
	}

	public void setQuntity(int quntity) {
		this.quntity = quntity;
	}




	public Book(Integer id,String image, String booktype) {
		this.id=id;
		this.image=image;
		this.type =booktype;
		
	}

	public Book(Integer id,String name, String image, String price ,String description,Integer quntity) {
		this.id = id;
		this.name= name;
		this.image=image;
		this.price = Double.valueOf(price);
		this.description=description;
		this.quntity = quntity;
	}

    public int getId() {
		return id;
	}
	public void setId(int id) {
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

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}



}
