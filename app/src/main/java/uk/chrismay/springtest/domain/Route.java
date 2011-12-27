package uk.chrismay.springtest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Route {

	public static final String QUERY_FIND_BY_NAME = "from Route r where r.name=?";

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
	
	@Column(unique = true)
    @NotBlank
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setId(long i) {
		this.id = i;
	}

	
}
