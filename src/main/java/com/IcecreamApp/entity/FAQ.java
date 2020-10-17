package com.IcecreamApp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="faq")
public class FAQ extends Base implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7160726146322296895L;

	@Column(name="question", columnDefinition="TEXT")
	private String question;
	
	@Column(name="answer", columnDefinition="TEXT")
	private String answer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
