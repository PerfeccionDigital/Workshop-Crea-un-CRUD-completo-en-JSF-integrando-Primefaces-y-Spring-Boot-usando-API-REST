package com.perfecciondigital.workshop.model;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.perfecciondigital.workshop.entity.Person;
import com.perfecciondigital.workshop.repository.PersonRepository;
import com.perfecciondigital.workshop.util.UtilFaces;

import lombok.Data;

@Data
@Named
@ViewScoped
public class PersonModel implements Serializable {

	private static final long serialVersionUID = -1731186755108785856L;
	
	private List<Person> persons;
	
	private Person personSelect;
	
	@Inject
	private PersonRepository personRepository;
	
	@PostConstruct
	public void init() {
		getAll();
	}
	
	private void getAll() {
		persons = personRepository.get();
	}
	
	public void save() {

		if(personSelect.getId() == null) {
			personRepository.post(personSelect);
			UtilFaces.addMessage(FacesMessage.SEVERITY_INFO, "Registered Successfully", null);
		}else {
			personRepository.put(personSelect.getId().toString(), personSelect);
			UtilFaces.addMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully", null);
		}
		
		getAll();

		UtilFaces.executeScript("PF('wvDlgPerson').hide();");
	}
	
	public void delete(Person personSelect) {
		personRepository.delete(personSelect.getId().toString());
		UtilFaces.addMessage(FacesMessage.SEVERITY_INFO, "Deleted Successfully", null);
		
		getAll();
	}
	
	public void openDlgPerson() {
		reset();
		UtilFaces.executeScript("PF('wvDlgPerson').show();");
	}
	
	public void reset() {
		personSelect = new Person();
	}
	
	
}
