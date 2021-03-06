package com.example.webapp.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.example.webapp.models.questions.BaseExamQuestion;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exam {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private Integer points;
	@OneToMany(mappedBy="exam")
	private List<BaseExamQuestion> questions;
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	
	public List<BaseExamQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(List<BaseExamQuestion> questions) {
		this.questions = questions;
	}
}