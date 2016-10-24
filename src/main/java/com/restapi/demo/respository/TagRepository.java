package com.restapi.demo.respository;

import org.springframework.data.repository.CrudRepository;

import com.restapi.demo.entity.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
	
	Tag findById(long id);
}
