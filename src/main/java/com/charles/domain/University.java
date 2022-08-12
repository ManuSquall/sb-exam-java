package com.charles.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "universities")
public class University {


	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private int capacity;
	 private String president;
	 private String name;
	 private String fullname;
	 
	 /*
	 @OneToMany(cascade = CascadeType.ALL)
	 @JoinColumn(name = "u_fid", referencedColumnName = "id")
	 List<Professor> professors = new ArrayList<>();
	 */
	 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "university")
	 private Set<Professor> professors = new HashSet<>();
	 
	 public University() {}
	 public University(Long id, String name, String fullname, String president, int capacity) {
	  this.id = id;
	  this.capacity = capacity;
	  this.president = president;
	  this.name = name;
	  this.fullname = fullname;
	 }
	 
	 public University(String name, String fullname, String president, int capacity) {
		  this.capacity = capacity;
		  this.president = president;
		  this.name = name;
		  this.fullname = fullname;
		 }
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getPresident() {
		return president;
	}
	public void setPresident(String president) {
		this.president = president;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	
	
	public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public void add(Professor professor) {

        if (professor != null) {
            if (professors == null) {
                professors = new HashSet<>();
            }

            professors.add(professor);
           // item.setOrder(this);
        }
    }
	 
	 
	 
	
}
