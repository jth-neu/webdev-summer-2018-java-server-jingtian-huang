package com.example.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webapp.models.questions.EssayExamQuestion;

public interface EssayExamQuestionRepository extends CrudRepository<EssayExamQuestion,Integer>{

}
