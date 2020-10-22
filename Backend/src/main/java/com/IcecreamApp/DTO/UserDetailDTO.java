package com.IcecreamApp.DTO;

import java.sql.Date;

public class UserDetailDTO extends BaseDTO { 
    
    private String fullname;

    private String address;
    
    private int gender;
    
    private Date birthday;
    
    private String avatar;
    
    private UserDTO user;
	
    public UserDetailDTO(long id, java.util.Date modifiedDate, String fullname, String address, int gender,
			Date birthday, String avatar, UserDTO user) {
		super(id, modifiedDate);
		this.fullname = fullname;
		this.address = address;
		this.gender = gender;
		this.birthday = birthday;
		this.avatar = avatar;
		this.user = user;
	}

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

