package uk.chrismay.springtest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Route {

	public Route(String name){
		this.name = name;
	}
	public Route(){
		//for spring
	}
	@Id
	@GeneratedValue
	private long id;
	
	public long getId() {
		return id;
	}
	
	@Column
    @NotBlank
	private String name;

	public String getName() {
		return name;
	}

	
}
