package com.example.webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.models.Widget;
import com.example.webapp.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins= "*")
public class WidgetService {
	
	@Autowired
	WidgetRepository  repository;
	
	@GetMapping("/api/widget")
	public List<Widget> finaAllWidgets() {
		return (List<Widget>) repository.findAll();
	}
	
	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody List<Widget> widgets) {
		repository.deleteAll();
		for(Widget widget: widgets) {
			repository.save(widget);
		}
	}
}
