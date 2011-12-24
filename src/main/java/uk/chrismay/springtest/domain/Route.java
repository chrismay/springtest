package uk.chrismay.springtest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Route {

	public Route(String name){
		this.name = name;
	}
	@Id
	@GeneratedValue
	private long id;
	
	public long getId() {
		return id;
	}
	@Column
	private String name;

	public String getName() {
		return name;
	}

	
}
