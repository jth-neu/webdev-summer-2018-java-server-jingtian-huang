package com.example.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webapp.models.questions.FillInTheBlanksExamQuestion;

public interface FillInTheBlanksExamQuestionRepository extends CrudRepository<FillInTheBlanksExamQuestion,Integer> {

}
