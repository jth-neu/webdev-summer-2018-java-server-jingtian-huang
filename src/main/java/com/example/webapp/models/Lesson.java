package com.example.webapp.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lesson {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  
  private String title;
  
  @ManyToOne
  @JsonIgnore
  private Module module;
  
  @OneToMany(mappedBy="lesson")
  private List<Widget> wigets;
  
  @OneToMany(mappedBy="lesson")
  @JsonIgnore
  private List<Assignment> assignments;
  
  public List<Assignment> getAssignments() {
	return assignments;
}
public void setAssignments(List<Assignment> assignments) {
	this.assignments = assignments;
}
public List<Widget> getWigets() {
	return wigets;
}
public void setWigets(List<Widget> wigets) {
	this.wigets = wigets;
}
public int getId() {
		return id;
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
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
}

