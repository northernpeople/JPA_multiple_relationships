package com.example.JPAMultirelationships.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.JPAMultirelationships.model.Car;

public interface CarRepo extends JpaRepository<Car, Long> {}
