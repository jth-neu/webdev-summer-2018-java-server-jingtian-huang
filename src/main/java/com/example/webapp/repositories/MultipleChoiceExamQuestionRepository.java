package com.example.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webapp.models.questions.MultipleChoiceExamQuestion;

public interface MultipleChoiceExamQuestionRepository extends CrudRepository<MultipleChoiceExamQuestion, Integer> {

}
