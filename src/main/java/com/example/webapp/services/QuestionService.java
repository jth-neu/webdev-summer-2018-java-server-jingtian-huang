package com.example.webapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.models.Exam;
import com.example.webapp.models.questions.BaseExamQuestion;
import com.example.webapp.models.questions.EssayExamQuestion;
import com.example.webapp.repositories.BaseExamQuestionRepository;
import com.example.webapp.repositories.EssayExamQuestionRepository;
import com.example.webapp.repositories.ExamRepository;
import com.example.webapp.repositories.FillInTheBlanksExamQuestionRepository;
import com.example.webapp.repositories.MultipleChoiceExamQuestionRepository;
import com.example.webapp.repositories.TrueOrFalseExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class QuestionService {

	@Autowired
	ExamRepository examRepo;

	@Autowired
	BaseExamQuestionRepository baseRepo;
	
	@Autowired
	EssayExamQuestionRepository essayRepo;
	
	@Autowired
	FillInTheBlanksExamQuestionRepository fillInTheBlankRepo;
	
	@Autowired
	MultipleChoiceExamQuestionRepository multipleChoiceRepo;
	
	@Autowired
	TrueOrFalseExamQuestionRepository trueOrFalseRepo;

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
	
	@PostMapping("/api/exam/{examId}/essay")
	public EssayExamQuestion createEssayForExam(@RequestBody EssayExamQuestion essay,
			@PathVariable("examId") int id) {
		Optional<Exam> e = examRepo.findById(id);
		if(e.isPresent()) {
			Exam exam = e.get();
			essay.setExam(exam);
			return essayRepo.save(essay);
		}
		return null;
	}
}
