package com.perfecciondigital.workshop;

import javax.inject.Inject;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import com.perfecciondigital.workshop.entity.Person;
import com.perfecciondigital.workshop.repository.PersonRepository;
import com.perfecciondigital.workshop.repository.PersonRepositoryImpl;

@EnableWeld
class WorkShopPrimefacesRestClientTest {
	
	@WeldSetup
    public WeldInitiator weld = WeldInitiator.of(PersonRepositoryImpl.class);
	
	@Inject
	PersonRepository personRepository;
	
	@Test
	void contextLoads() {
		personRepository.get().forEach(System.out::println);
		System.out.println("");
		
		Person person = new Person();
		person.setFirstName("Andre");
		person.setLastName("Alvarez");
		person = personRepository.post(person);
		System.out.println("post " + person);
		
		person.setFirstName("Andre 2");
		person.setLastName("Alvarez 2");
		person = personRepository.put(person.getId().toString(), person);
		System.out.println("put " + person);
		
		person.setFirstName("Andre 55");
		person = personRepository.patch(person.getId().toString(), person);
		System.out.println("patch " + person);
		
		System.out.println("delete");
		personRepository.delete("1");
		System.out.println("");
		
		personRepository.get().forEach(System.out::println);
		
	}	
	
}
