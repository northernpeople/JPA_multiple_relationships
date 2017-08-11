package com.example.JPAMultirelationships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JPAMultirelationships.model.Person;
import com.example.JPAMultirelationships.repo.PersonRepo;

@Service
public class PersonService {
	
	@Autowired
	PersonRepo repo;
	
	public Person create(Person newPerson){
		return repo.saveAndFlush(newPerson);
	}

	public Person findById(Long id) {
		return repo.findOne(id);
	}

}
