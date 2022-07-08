package com.icecream.product.DTO;

import java.util.Date;

public class FAQDTO extends BaseDTO {
	
	private String question;
	
	private String answer;

	public FAQDTO(long id, Date modifiedDate, String question, String answer) {
		super(id, modifiedDate);
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
