package com.IcecreamApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="faq")
public class FAQ extends Base {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7160726146322296895L;

	@Column(name="question", columnDefinition="TEXT")
	private String question;
	
	@Column(name="answer", columnDefinition="TEXT")
	private String answer;

	public FAQ() {
		
	}
	public FAQ(Long id, String question, String answer) {
		this.id = id;
		this.question = question;
		this.answer = answer;
	}
	public FAQ(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	
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
