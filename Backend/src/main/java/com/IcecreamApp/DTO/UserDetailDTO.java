package com.IcecreamApp.DTO;

import java.sql.Date;

import com.IcecreamApp.systemConstant.EGender;

public class UserDetailDTO extends BaseDTO { 
    
    private String firstname;
    
    private String lastname;

    private String address;
    
    private EGender gender;
    
    private Date birthday;
    
    private String avatar;
    
	public String getFullname() {
		return (this.firstname + " " + this.lastname);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EGender getGender() {
		return gender;
	}

	public void setGender(EGender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	
}

