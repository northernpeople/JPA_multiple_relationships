package com.example.JPAMultirelationships.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.JPAMultirelationships.model.Person;

public interface PersonRepo extends JpaRepository<Person, Long>{}
