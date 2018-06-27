package com.example.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webapp.models.questions.BaseExamQuestion;

public interface BaseExamQuestionRepository extends CrudRepository<BaseExamQuestion, Integer> {

}
