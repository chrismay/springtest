package uk.chrismay.springtest.domain;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Ride {

	public static final String QUERY_FIND_BY_ROUTE = "from Ride r where r.route = ?";
	public static final String QUERY_FIND_ALL = "from Ride";

	public Ride(Route route){
		this.date = new Date(new java.util.Date().getTime());
		this.route = route;	
	}
	
	// default constructor for Jackson/Hibernate
	public Ride(){
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}

	@Column 
	private Date date;
	
	public java.util.Date getDate() {
		if (this.date == null){
			return null;
		}
		return new java.util.Date(this.date.getTime());
	}

	public void setDate(java.util.Date date) {
		this.date = new Date(date.getTime());
	}
	@Column
	private String comments;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)	
	private Route route;

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public void updateFrom(Ride newData) {
		route = newData.route;
		comments = newData.comments;
		date = newData.date;
		
	}
}
