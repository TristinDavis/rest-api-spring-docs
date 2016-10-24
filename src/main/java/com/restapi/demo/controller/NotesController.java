package com.restapi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.demo.entity.Note;
import com.restapi.demo.exception.ResourceDoesNotExistException;
import com.restapi.demo.model.NoteResource;
import com.restapi.demo.respository.NoteRepository;

@RestController
@RequestMapping("/notes")
public class NotesController {
	
	private final NoteRepository noteRepository;
	
	@Autowired
	public NotesController(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all(){
		return new ResponseEntity<Object>(noteRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody NoteResource noteResource){
		try{
			Note note = new Note();
			note.setTitle(noteResource.getTitle());
			note.setBody(noteResource.getBody());
	//		TODO 1.- note.setTags(noteResource.getTags());
			this.noteRepository.save(note);
	//		Set Note id generated
			noteResource.setId(note.getId());
		}catch (Exception e){
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(noteResource, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> note(@PathVariable("id") Long id){
		return new ResponseEntity<Object>(findByNoteId(id), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id){
		this.noteRepository.delete(id);
	}
	
	@RequestMapping(value="/{id}/tags", method=RequestMethod.GET)
	public ResponseEntity<Object> getTags(@PathVariable("id") Long id){
		return new ResponseEntity<Object>(findByNoteId(id).getTags(),HttpStatus.OK);
	}

	
	private Note findByNoteId(Long id){
		Note note = this.noteRepository.findById(id);
		if(note == null){
			throw new ResourceDoesNotExistException();
		}
		return note;
	}
	

}
