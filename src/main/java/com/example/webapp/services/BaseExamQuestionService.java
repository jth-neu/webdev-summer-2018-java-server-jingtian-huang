package com.example.webapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.models.Exam;
import com.example.webapp.models.questions.BaseExamQuestion;
import com.example.webapp.repositories.BaseExamQuestionRepository;
import com.example.webapp.repositories.ExamRepository;

@RestController
@CrossOrigin(origins = "*")
public class BaseExamQuestionService {

	@Autowired
	ExamRepository examRepo;

	@Autowired
	BaseExamQuestionRepository baseRepo;

	@GetMapping("/api/exam/{examId}/question")
	public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepo.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			return questions;
		}
		return null;
	}
	
	@DeleteMapping("/api/question/{questionId}")
	public void deleteQuestionById(@PathVariable("questionId") int id) {
		baseRepo.deleteById(id);
	}
}
