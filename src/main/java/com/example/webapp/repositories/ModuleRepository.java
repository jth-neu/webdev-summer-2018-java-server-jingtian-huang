package com.example.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webapp.models.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer>{}
