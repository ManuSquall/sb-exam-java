package com.charles;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.charles.domain.Professor;
import com.charles.domain.ProfessorRepository;
import com.charles.domain.University;
import com.charles.domain.UniversityRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
public class SbExamJavaApplication implements CommandLineRunner{

	private static final Logger logger = 
			LoggerFactory.getLogger(SbExamJavaApplication.class);
	  
    @Autowired
    private ProfessorRepository p_repository;
    

    @Autowired
    private UniversityRepository u_repository;
    
    
    
	public static void main(String[] args) {
		SpringApplication.run(SbExamJavaApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		University u1 = new University("UCAD", "Universite Cheikh Anta Diop", "Ahmadou Aly MBAYE", 53000);
		University u2 = new  University("UGB", "Universite Gaston Berger", "Ousmane THIARE", 39000);
		u_repository.saveAll(Arrays.asList(u1, u2));

		Professor p1 = new Professor(1920, "Full Professor", "Samba Ndiaye", "Software Engineering", u1);
		Professor p2 = new Professor(3829, "Associate Professor", "Mandicou Ba", "Systems and Languages", u1);
		Professor p3 = new Professor(9482, "Lecturer", "David Faye", "Databases", u2);
		p_repository.saveAll(Arrays.asList(p1, p2, p3));
                  
		for (Professor p : p_repository.findAll()) {
			logger.info(p.getName() + " : " + p.getGrade() +", works for " +p.getEmployer().getName());
		}
		
	}
}	 

@RestController
@RequestMapping("exam/professors")
class ProfessorController {
	 
	private final ProfessorRepository p_Repository ;
	
	
	public ProfessorController(ProfessorRepository p_Repository) {
		this.p_Repository = p_Repository;
	}
	
	@GetMapping
	Iterable<Professor> getProfessors() {
		return p_Repository.findAll();
	}
	
	@GetMapping("/{id}")
	Optional<Professor> getProfessorById(@PathVariable int id) {
		return p_Repository.findById(id);
	}
	
	@PostMapping("/create")
	Professor postProfessor(@RequestBody Professor professor) {
		return p_Repository.save(professor);
	}
	
	@PutMapping("/update/{id}")
	ResponseEntity<Professor> putProfessor(@PathVariable int id, @RequestBody Professor professor) {
		return (p_Repository.existsById(id)) 
				? new ResponseEntity<>(p_Repository.save(professor),
						org.springframework.http.HttpStatus.OK)
				: new ResponseEntity<>(p_Repository.save(professor),
						org.springframework.http.HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteProfessor(@PathVariable int id) {
		p_Repository.deleteById(id);
	}
}


@RestController
@RequestMapping("exam/universities")
class UniversityController {
	private final UniversityRepository u_Repository ;
	
	
	public UniversityController(UniversityRepository u_Repository) {
		this.u_Repository = u_Repository;
	}
	
	@GetMapping
	Iterable<University> getUniversitys() {
		return u_Repository.findAll();
	}
	
	@GetMapping("/{id}")
	Optional<University> getUniversityById(@PathVariable Long id) {
		return u_Repository.findById(id);
	}
	
	@PostMapping("/create")
	University postUniversity(@RequestBody University university) {
		return u_Repository.save(university);
	}
	
	@PutMapping("/update/{id}")
	ResponseEntity<University> putUniversity(@PathVariable Long id, @RequestBody University university) {
		return (u_Repository.existsById(id)) 
				? new ResponseEntity<>(u_Repository.save(university),
						org.springframework.http.HttpStatus.OK)
				: new ResponseEntity<>(u_Repository.save(university),
						org.springframework.http.HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteUniversity(@PathVariable Long id) {
		u_Repository.deleteById(id);
	}
}