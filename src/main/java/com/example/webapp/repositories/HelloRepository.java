package com.example.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webapp.models.Hello;

public interface HelloRepository extends CrudRepository<Hello, Integer> {

}
