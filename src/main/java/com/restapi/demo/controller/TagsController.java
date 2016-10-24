package com.restapi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.demo.entity.Tag;
import com.restapi.demo.exception.ResourceDoesNotExistException;
import com.restapi.demo.model.TagResource;
import com.restapi.demo.respository.TagRepository;

@RestController
@RequestMapping("tags")
public class TagsController {
	
	private final TagRepository tagRepository;
	
	@Autowired
	public TagsController(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> all(){
		return new ResponseEntity<Object>(tagRepository.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody TagResource tagResource){
		try{
		Tag tag = new Tag();
		tag.setName(tag.getName());
		
		tagRepository.save(tag);
		
		tagResource.setId(tag.getId());
		
		}catch(Exception e){
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(tagResource,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> tag(@PathVariable("id") Long id){
		return new ResponseEntity<Object>(findByTagId(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id){
		this.tagRepository.delete(id);
	}
	
	
	private Tag findByTagId(Long id){
		Tag tag = tagRepository.findById(id);
		if(tag == null){
			throw new ResourceDoesNotExistException();
		}
		return tag;
	}
}
