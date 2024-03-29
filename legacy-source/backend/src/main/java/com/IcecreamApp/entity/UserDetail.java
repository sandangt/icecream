package com.IcecreamApp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.IcecreamApp.systemConstant.EGender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_details")
public class UserDetail implements Serializable { 
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	protected Long id;

	@Column(name="last_update_time")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name="firstname", columnDefinition="VARCHAR(100)")
    private String firstname;
	
	@Column(name="lastname", columnDefinition="VARCHAR(100)")
    private String lastname;

	@Column(name="address", columnDefinition="VARCHAR(100)")
    private String address;
    
    @Column(name="gender", columnDefinition="VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private EGender gender;
    
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
	public UserDetail() {
		
	}
    
    public UserDetail(Long id, String firstname, String lastname, String address, EGender gender, Date birthday, String avatar, User user) {
		this.id = id;
    	this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.gender = gender;
		this.birthday = birthday;
		this.avatar = avatar;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

