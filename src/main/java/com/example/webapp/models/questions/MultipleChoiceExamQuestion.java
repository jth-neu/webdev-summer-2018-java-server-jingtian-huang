package com.example.webapp.models.questions;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MultipleChoiceExamQuestion extends BaseExamQuestion{
	@Column(name = "CHOICES", nullable = false)
	private String choices;
	@Column(name = "CORRECT_CHOICE", nullable = false)
	private Integer correctChoice;
	

	public Integer getCorrectChoice() {
		return correctChoice;
	}

	public void setCorrectChoice(Integer correctChoice) {
		this.correctChoice = correctChoice;
	}

	public String getChoices() {
		return choices;
	}

	public void setChoices(String choices) {
		this.choices = choices;
	}
}
