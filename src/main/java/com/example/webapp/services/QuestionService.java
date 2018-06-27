package com.example.webapp.services;

import java.util.List;
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
import com.example.webapp.models.questions.BaseExamQuestion;
import com.example.webapp.models.questions.EssayExamQuestion;
import com.example.webapp.models.questions.FillInTheBlanksExamQuestion;
import com.example.webapp.models.questions.MultipleChoiceExamQuestion;
import com.example.webapp.models.questions.TrueOrFalseExamQuestion;
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
	
	@PostMapping("/api/exam/{examId}/choice")
	public MultipleChoiceExamQuestion createMultipleChoiceForExam(@RequestBody MultipleChoiceExamQuestion choices,
			@PathVariable("examId") int id) {
		Optional<Exam> e = examRepo.findById(id);
		if(e.isPresent()) {
			Exam exam = e.get();
			choices.setExam(exam);
			return multipleChoiceRepo.save(choices);
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/blanks")
	public FillInTheBlanksExamQuestion createFillInTheBlankForExam(@RequestBody FillInTheBlanksExamQuestion blanks,
			@PathVariable("examId") int id) {
		Optional<Exam> e = examRepo.findById(id);
		if(e.isPresent()) {
			Exam exam = e.get();
			blanks.setExam(exam);
			return fillInTheBlankRepo.save(blanks);
		}
		return null;
	}

	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalseExamQuestion createTrueOrFalseForExam(@RequestBody TrueOrFalseExamQuestion truefalse,
			@PathVariable("examId") int id) {
		Optional<Exam> e = examRepo.findById(id);
		if(e.isPresent()) {
			Exam exam = e.get();
			truefalse.setExam(exam);
			return trueOrFalseRepo.save(truefalse);
		}
		return null;
	}
	
	@PutMapping("/api/essay/{questionId}")
	public EssayExamQuestion updateEssay(@RequestBody EssayExamQuestion essay, @PathVariable("questionId") int questionId) {
		Optional<EssayExamQuestion> e = essayRepo.findById(questionId);
		if(e.isPresent()) {
			EssayExamQuestion previousQuestion = e.get();
			if(essay.getTitle() != null) {
				previousQuestion.setTitle(essay.getTitle());
			}
			if(essay.getDescription() != null) {
				previousQuestion.setDescription(essay.getDescription());
			}
			if(essay.getPoints() != null) {
				previousQuestion.setPoints(essay.getPoints());
			}
			return essayRepo.save(previousQuestion);
		}
		return null;
	}
	
	@PutMapping("/api/choice/{questionId}")
	public MultipleChoiceExamQuestion updateMultipleChoice(@RequestBody MultipleChoiceExamQuestion choices, @PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceExamQuestion> e = multipleChoiceRepo.findById(questionId);
		if(e.isPresent()) {
			MultipleChoiceExamQuestion previousQuestion = e.get();
			if(choices.getTitle() != null) {
				previousQuestion.setTitle(choices.getTitle());
			}
			if(choices.getDescription() != null) {
				previousQuestion.setDescription(choices.getDescription());
			}
			if(choices.getPoints() != null) {
				previousQuestion.setPoints(choices.getPoints());
			}
			if(choices.getChoices() != null) {
				previousQuestion.setChoices(choices.getChoices());
			}
			if(choices.getCorrectChoice() != null) {
				previousQuestion.setCorrectChoice(choices.getCorrectChoice());
			}
			return multipleChoiceRepo.save(previousQuestion);
		}
		return null;
	}
	
	@PutMapping("/api/blanks/{questionId}")
	public FillInTheBlanksExamQuestion updateFillInTheBlank(@RequestBody FillInTheBlanksExamQuestion blanks, @PathVariable("questionId") int questionId) {
		Optional<FillInTheBlanksExamQuestion> e = fillInTheBlankRepo.findById(questionId);
		if(e.isPresent()) {
			FillInTheBlanksExamQuestion previousQuestion = e.get();
			if(blanks.getTitle() != null) {
				previousQuestion.setTitle(blanks.getTitle());
			}
			if(blanks.getDescription() != null) {
				previousQuestion.setDescription(blanks.getDescription());
			}
			if(blanks.getPoints() != null) {
				previousQuestion.setPoints(blanks.getPoints());
			}
			if(blanks.getVariables() != null) {
				previousQuestion.setVariables(blanks.getVariables());
			}
			
			return fillInTheBlankRepo.save(previousQuestion);
		}
		return null;
	}
	
	@PutMapping("/api/truefalse/{questionId}")
	public TrueOrFalseExamQuestion updateTrueOrFalse(@RequestBody TrueOrFalseExamQuestion truefalse, @PathVariable("questionId") int questionId) {
		Optional<TrueOrFalseExamQuestion> e = trueOrFalseRepo.findById(questionId);
		if(e.isPresent()) {
			TrueOrFalseExamQuestion previousQuestion = e.get();
			if(truefalse.getTitle() != null) {
				previousQuestion.setTitle(truefalse.getTitle());
			}
			if(truefalse.getDescription() != null) {
				previousQuestion.setDescription(truefalse.getDescription());
			}
			if(truefalse.getPoints() != null) {
				previousQuestion.setPoints(truefalse.getPoints());
			}
			if(truefalse.getIsTrue() != null) {
				previousQuestion.setIsTrue(truefalse.getIsTrue());
			}
			return trueOrFalseRepo.save(previousQuestion);
		}
		return null;
	}
	
}
