package com.restapi.demo.model;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteResource {
	
	private Long id;
	private final String title;
	private final String body;
	private final List<TagResource> tags;
	
	@JsonCreator
	public NoteResource(@JsonProperty("id") Long id, @JsonProperty("title") String title, @JsonProperty("body") String body,
			@JsonProperty("tags") List<TagResource> tags) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.tags = tags == null ? Collections.<TagResource>emptyList(): tags;
	}
	
	@JsonProperty
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}
	
	@JsonProperty("tags")
	public List<TagResource> getTags() {
		return tags;
	}
	
	
}
