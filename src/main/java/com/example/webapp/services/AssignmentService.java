package com.example.webapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.models.Assignment;
import com.example.webapp.models.Lesson;
import com.example.webapp.repositories.AssignmentRepository;
import com.example.webapp.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {
	
	@Autowired
	AssignmentRepository assignmentRepository;

	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/assignment")
	public Iterable<Assignment> findAllAssignments() {
		return assignmentRepository.findAll(); 
	}
	
	@GetMapping("/api/assignment/{assignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int id) {
		Optional<Assignment> a = assignmentRepository.findById(id);
		if(a.isPresent()) {
			return a.get();
		}
		return null;
	}
	
	
	@GetMapping("/api/lesson/{lessonId}/assignment")
	public Iterable<Assignment> findAllAssignmentsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> l = lessonRepository.findById(lessonId);
		if(l.isPresent()) {
			Lesson lesson = l.get();
			Iterable<Assignment> assignments = lesson.getAssignments();
			return assignments;
		}
		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/assignment")
	public Assignment createAssignmentForLesson(@RequestBody Assignment assignment, @PathVariable("lessonId") int lessonId) {
		Optional<Lesson> l = lessonRepository.findById(lessonId);
		if(l.isPresent()) {
			Lesson lesson = l.get();
			assignment.setLesson(lesson); 
			return assignmentRepository.save(assignment);
		}
		return null;
	}
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignmentById(@PathVariable("assignmentId") int id) {
		assignmentRepository.deleteById(id);
	}

}
