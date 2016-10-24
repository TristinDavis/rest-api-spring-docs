package com.restapi.demo.respository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.restapi.demo.entity.Note;
import com.restapi.demo.entity.Tag;

public interface NoteRepository extends CrudRepository<Note, Long> {
	
	Note findById(long id);
	
	List<Tag> findByTagsIn(Collection<Tag> tags);

}
