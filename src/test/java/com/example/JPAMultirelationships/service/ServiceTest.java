package com.example.JPAMultirelationships.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.JPAMultirelationships.model.Car;
import com.example.JPAMultirelationships.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

	@Autowired
	PersonService personService;
	
	@Autowired
	CarService carService;
	
	Person person;
	Car car;
	
	@Before
	public void setUp(){
		person = new Person("Totoro");
		person = personService.create(person);

		car = new Car("123ABC");
		car = carService.create(car);
	}
	
	@Test
	public void shouldAddOwnerToCar(){		
		carService.addOwnerTo(car, person);							// create link: this creates ownership relationship!
		
		person = personService.findById(person.getId());			// refresh from database
		car = carService.findById(car.getId());
		
		assertTrue(car.getOwners().contains(person));			// relationship established ? (first of two)
		assertTrue(person.getOwnedCar().equals(car));	
	}
	
	
	@Test
	public void shouldAddDriverToCar(){
		carService.addDriverTo(car, person);							// create link: this creates driver-car relationship! different from ownership.
		
		person = personService.findById(person.getId());			// refresh from database
		car = carService.findById(car.getId());
		
		assertTrue(car.getDrivers().contains(person));				// relationship established ? (second of two)
		assertTrue(person.getPermittedToDrive().equals(car));	
	}
	
	
	@Test
	public void shouldSavePersonAndCar() {
		assertTrue(person.getId() != null);
		assertTrue(car.getId() != null);

	}

}
