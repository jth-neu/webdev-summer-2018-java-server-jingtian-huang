package com.example.webapp.models.questions;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MultipleChoiceExamQuestion extends BaseExamQuestion{
	@Column(name = "CHOICES", nullable = false)
	private String choices;

	public String getChoices() {
		return choices;
	}

	public void setChoices(String choices) {
		this.choices = choices;
	}
}