package com.example.webapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.models.Lesson;
import com.example.webapp.models.Widget;
import com.example.webapp.repositories.LessonRepository;
import com.example.webapp.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins= "*")
public class WidgetService {
	
	@Autowired
	WidgetRepository  repository;
	@Autowired
	LessonRepository lessonRepository;
	
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
	
	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgetsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getWigets();
		}
		return null;		
	}
	
	@PostMapping("/api/lesson/{lessonId}/widget")
	public Widget saveWidget(@PathVariable("lessonId") int lessonId, @RequestBody Widget widget) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if(data.isPresent()) {
			Lesson lesson = data.get();
			widget.setLesson(lesson);
			return repository.save(widget);

		}
		return null;		
	}
	
	
	@PostMapping("/api/lesson/{lessonId}/widgets")
	public List<Widget> saveWidgets(@PathVariable("lessonId") int lessonId, @RequestBody List<Widget> newWidgets) {
		repository.deleteWidgetsByLessonId(lessonId);
		List<Widget> result = new ArrayList<Widget>();
		for(Widget widget : newWidgets) {
			result.add(saveWidget(lessonId,widget));
		}
		return result;
	}
}
