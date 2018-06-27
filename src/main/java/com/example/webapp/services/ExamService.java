package com.example.webapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.models.Exam;
import com.example.webapp.models.Lesson;
import com.example.webapp.repositories.ExamRepository;
import com.example.webapp.repositories.LessonRepository;


@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired 
	LessonRepository lessonRepository;

	@GetMapping("/api/exam")
	public Iterable<Exam> findAllExams() {
		return examRepository.findAll(); 
	}
	
	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/exam")
	public Iterable<Exam> findAllExamsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> lessons = lessonRepository.findById(lessonId);
		if(lessons.isPresent()) {
			Lesson lesson = lessons.get();
			Iterable<Exam> exams = lesson.getExams();
		
			return exams;
		}
		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/exam")
	public Exam createExam(
			@PathVariable("lessonId") int lessonId,
			@RequestBody Exam newExam) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newExam.setLesson(lesson);
			return examRepository.save(newExam);

		}
		return null;		
	}

	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int id) {
		examRepository.deleteById(id);
	}
	
	@PutMapping("/api/exam/{examId}")
	public Exam updateExam(@RequestBody Exam exam, 
			@PathVariable("examId") int examId) {
		Optional<Exam> e = examRepository.findById(examId);
		if(e.isPresent()) {
			Exam exsitingExam = e.get();
			
			if(exam.getTitle() != null) {
				exsitingExam.setTitle(exam.getTitle());
			}
			if(exam.getDescription() != null) {
				exsitingExam.setDescription(exam.getDescription());
			}
			if(exam.getPoints() != null) {
				exsitingExam.setPoints(exam.getPoints());
			}
			return examRepository.save(exsitingExam);
		}
		return null;
	}
}