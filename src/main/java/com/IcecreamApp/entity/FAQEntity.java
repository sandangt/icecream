package com.IcecreamApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="faq")
public class FAQEntity extends BaseEntity {
	
	@Column(name="question", columnDefinition="TEXT")
	private String question;
	
	@Column(name="answer", columnDefinition="TEXT")
	private String answer;
}
