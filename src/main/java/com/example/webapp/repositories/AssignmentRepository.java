package com.example.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webapp.models.Assignment;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {

}
