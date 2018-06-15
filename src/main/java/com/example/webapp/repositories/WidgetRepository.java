package com.example.webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webapp.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer>{

}
