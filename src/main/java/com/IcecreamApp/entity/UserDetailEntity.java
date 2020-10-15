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
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserEntity user;
}
