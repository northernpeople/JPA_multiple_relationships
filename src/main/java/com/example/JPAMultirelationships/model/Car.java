package com.example.JPAMultirelationships.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Car {
	
	@Id @GeneratedValue
	Long id;
	
	String VIN;
	
	/*
	 * First relationship: many people can own one car.
	 */
	@OneToMany(mappedBy="ownedCar", fetch=FetchType.EAGER)
	Set<Person> owners = new HashSet<>();
	
	/*
	 * Second relationship: many people are permitted to drive one car
	 */
	@OneToMany(mappedBy="permittedToDrive", fetch=FetchType.EAGER)
	Set<Person> drivers = new HashSet<>();

	/*
	 * QUESTIONS:
	 * 
	 * 1) How will this look in UML, what about the database? create UML showing two relationships.
	 * 
	 * 2) Migrate to MySQL, and see the schema from hbm2ddl. create ERD in workbench, how is this similar to UML?
	 * 
	 * 3) Add one unidirectional relationship from Car to Person and one from Person to Car, such that they are different relationships, and don't have to be bidirectional.
	 * 
	 */
	
	
	public Set<Person> getOwners() {
		return owners;
	}

	public void setOwners(Set<Person> owners) {
		this.owners = owners;
	}

	public Set<Person> getDrivers() {
		return drivers;
	}

	public void setDrivers(Set<Person> drivers) {
		this.drivers = drivers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	public Car(String vIN) {
		VIN = vIN;
	}

	public Car() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((VIN == null) ? 0 : VIN.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (VIN == null) {
			if (other.VIN != null)
				return false;
		} else if (!VIN.equals(other.VIN))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [id=").append(id).append(", VIN=").append(VIN).append("]");
		return builder.toString();
	}
	
	
	

}
