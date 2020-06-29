package com.nativejava.user;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "User model of REST API")
public class User {

	private int id;
	
	@NotNull(message = "Name is must")
	@Size(min = 2, message = "The length of the name should be more than 2")
	@ApiModelProperty(required = true)
	private String name;
	
	@NotNull(message = "DoB is must")
	@ApiModelProperty(required = true)
	@Past
	private Date dob;

	public User(int id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}

}
