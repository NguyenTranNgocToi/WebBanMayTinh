package com.pcstore.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;



@Entity @Table(name="Customers")
public class Customer {
	@Id
	@NotEmpty(message="không để trống")
	String id;
	
	@Length(min = 6, message="ít nhất 6 ký tự")
	String password;
	
	
	@NotEmpty(message="không để trống")
	String fullname;
	
	
	@NotEmpty(message="không để trống")
	@Email(message="phải là email")
	String email;
	
	String photo;
	

	Boolean activated;

	Boolean admin;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	List<Order> orders;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	


}
