package com.charles.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "professors")
public class Professor {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 private int matricule;
	 private String grade;
	 private String name;
	 private String speciality;
	 
	// default fetch type for ManyToOne: EAGER
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "university_id")
	    @JsonIgnore
	    private University university;
	 
	 public Professor() {}
	 
	 public Professor(int id, int matricule, String grade, String name, String speciality) {
	  this.id = id;
	  this.matricule = matricule;
	  this.grade = grade;
	  this.name = name;
	  this.speciality = speciality;
	  
	
	 }
	 
	 public Professor(int matricule, String grade, String name, String speciality, University nameU) {
		  this.matricule = matricule;
		  this.grade = grade;
		  this.name = name;
		  this.speciality = speciality;
		  this.setUniversity(nameU);
		  
		
		 }
	 
	
	    
	    
	 /*public Professor(String name) {
	  this(UUID.randomUUID().toString(),name);
	 }*/
	public int getMatricule() {
		return matricule;
	}
	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	public University getEmployer() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
	 
	 
	
}
