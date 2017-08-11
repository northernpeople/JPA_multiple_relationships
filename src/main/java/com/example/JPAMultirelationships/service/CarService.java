package com.example.JPAMultirelationships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.JPAMultirelationships.model.Car;
import com.example.JPAMultirelationships.model.Person;
import com.example.JPAMultirelationships.repo.CarRepo;
import com.example.JPAMultirelationships.repo.PersonRepo;

@Service
public class CarService {
	
	@Autowired
	CarRepo carRepo;

	@Autowired
	PersonRepo personRepo;
	
	public Person setFavouriteCar(Car favourite, Person person){
		Assert.notNull(favourite, "cannot add person to null");											// assert
		Assert.notNull(person, "cannot add null to car");
		Assert.notNull(favourite.getId(), "car should have persistence identity");
		Assert.notNull(person.getId(), "person should have persistence identity");
		
		person.setFavouriteCar(favourite);
		
		return personRepo.saveAndFlush(person);															// entity that has a reference and annotation is an owner of the relationship in unidirectional relationship. always.
	}
	
	public Car setMechanic(Car car, Person mechanic){
		Assert.notNull(car, "cannot add person to null");											// assert
		Assert.notNull(mechanic, "cannot add null to car");
		Assert.notNull(car.getId(), "car should have persistence identity");
		Assert.notNull(mechanic.getId(), "person should have persistence identity");
		
		car.setMechanic(mechanic);
		
		return carRepo.saveAndFlush(car); // saving car because car is an owner of the unidirectional relationship.
		
	}
	
	public Person addOwnerTo(Car car, Person owner){
		Assert.notNull(car, "cannot add person to null");											// assert
		Assert.notNull(owner, "cannot add null to car");
		Assert.notNull(car.getId(), "car should have persistence identity");
		Assert.notNull(owner.getId(), "person should have persistence identity");
		
		car.getOwners().add(owner);																		// bidirectional linking of ownership relationship
		owner.setOwnedCar(car);
		
		return personRepo.saveAndFlush(owner);													// save/update owning entity
	}
	
	public Person addDriverTo(Car car, Person driver){
		Assert.notNull(car, "cannot add person to null");											// assert
		Assert.notNull(driver, "cannot add null to car");
		Assert.notNull(car.getId(), "car should have persistence identity");
		Assert.notNull(driver.getId(), "person should have persistence identity");
		
		car.getDrivers().add(driver);    																	// bidirectional linking of driver-car relationship
		driver.setPermittedToDrive(car);

		/* 
		 * If this was two unidrectional relationships, and we only updated one of them, what would happen? 
		 * Problem! 
		 * Because driver would be in the drivers set of another car, but he/she would have reference to another car. 
		 * He/she would think about driving wrong car!
		 * We lost data integrity!
		 * This two unidrectional relationships, are in fact two sides of one bidirectional relationship and we maintain it here.
		 */
		
		return personRepo.saveAndFlush(driver);													// always save/update OWNING entity	
	}

	public Car findById(Long id) {
		return carRepo.findOne(id);
	}
	
	public Car create(Car newCar){
		return carRepo.saveAndFlush(newCar);
	}
	
	

}
