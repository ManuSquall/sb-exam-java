package com.charles.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long>{
	
	University findByName(String name);
	University findById (long id);
	University deleteById (long id);

}
