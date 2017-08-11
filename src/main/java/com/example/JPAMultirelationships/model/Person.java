package com.example.JPAMultirelationships.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Person {
	
	@Id @GeneratedValue
	Long id;
	
	String name;
	
	@ManyToOne
	Car ownedCar;
	
	@ManyToOne
	Car permittedToDrive;

	

	public Car getOwnedCar() {
		return ownedCar;
	}

	public void setOwnedCar(Car ownedCar) {
		this.ownedCar = ownedCar;
	}

	public Car getPermittedToDrive() {
		return permittedToDrive;
	}

	public void setPermittedToDrive(Car permittedToDrive) {
		this.permittedToDrive = permittedToDrive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person(String name) {
		this.name = name;
	}

	public Person() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}
	
	
	

}
