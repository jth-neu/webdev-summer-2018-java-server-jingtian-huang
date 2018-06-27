package com.example.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webapp.models.Exam;

public interface ExamRepository extends CrudRepository<Exam, Integer> {

}
