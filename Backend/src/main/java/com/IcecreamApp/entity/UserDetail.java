package com.IcecreamApp.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_details")
public class UserDetail extends Base { 
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -3166009761593242165L; 
	
	@Column(name="fullname", columnDefinition="VARCHAR(100)")
    private String fullname;

	@Column(name="address", columnDefinition="VARCHAR(100)")
    private String address;
    
    @Column(name="gender", columnDefinition="TINYINT")
    private Integer gender;
    
    @Column(name="birthday", columnDefinition="DATE")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date birthday;
    
    @Column(name="avatar", columnDefinition="VARCHAR(250)")
    private String avatar;

    /**
     * Foreign key section 
     */
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private User user;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

