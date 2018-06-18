package com.example.webapp.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webapp.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer>{
	@Modifying
	@Transactional 
	@Query("DELETE FROM Widget w WHERE w.lesson.id=:lessonId")
	void deleteWidgetsByLessonId(@Param("lessonId") int lessonId);
}
