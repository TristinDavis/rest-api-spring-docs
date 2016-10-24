package com.restapi.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TagResource {
	
	private Long id;
	private final String name;
	
	@JsonCreator
	public TagResource(@JsonProperty("id") Long id,@JsonProperty("name")String name) {
		this.id=id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	
}
