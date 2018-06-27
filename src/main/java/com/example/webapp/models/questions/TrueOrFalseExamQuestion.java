package com.example.webapp.models.questions;

import javax.persistence.Column;

public class TrueOrFalseExamQuestion extends BaseExamQuestion {
	@Column(name = "IS_TRUE", nullable = false)
	private Boolean isTrue;
	public Boolean getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
}
