package com.IcecreamApp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userdetail")
public class UserDetailEntity extends BaseEntity { 
    
    @Column(name="fullname", columnDefinition="VARCHAR(100")
    private String fullname;

	@Column(name="address", columnDefinition="VARCHAR(100)")
    private String address;
    
    @Column(name="gender", columnDefinition="TINYINT")
    private Integer gender;
    
    @Column(name="birthday", columnDefinition="DATE")
    private Date birthday;
    
    @Column(name="avatar")
    private String avatar;
    
    @Column(name="status", columnDefinition="TINYINT")
    private Integer status;

    /**
     * Foreign key section 
     */
    private UserEntity user;
    

    
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}

