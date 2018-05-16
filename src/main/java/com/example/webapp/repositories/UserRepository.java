package com.example.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webapp.models.User;

public interface UserRepository extends CrudRepository<User,Integer>{

}
