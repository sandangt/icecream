package com.IcecreamApp.DTO;

import java.sql.Date;

public class UserDetailDTO extends BaseDTO { 
    
    private String fullname;

    private String address;
    
    private int gender;
    
    private Date birthday;
    
    private String avatar;
    
    private UserDTO user;
	
    public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
}

