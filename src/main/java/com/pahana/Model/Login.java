package com.pahana.Model;

public class Login {

	public Login(Integer id, String address, String mobile) {
		this.id=id;
		this.address=address;
		this.mobile= Integer.parseInt(mobile);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private int id;
    private String name;
    private String email;
    private  String password;
    private  String address;
    private  int mobile;
    private  String status;

	public Login(String email, String password) {

	    this.password = password;
	    this.email = email;
	}

	

}
