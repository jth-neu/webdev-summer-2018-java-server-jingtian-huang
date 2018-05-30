package com.example.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webapp.models.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {}
