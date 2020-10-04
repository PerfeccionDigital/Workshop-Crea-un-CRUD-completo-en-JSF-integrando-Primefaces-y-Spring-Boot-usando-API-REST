/*
 * Reference: https://spring.io/guides/gs/accessing-data-rest
 */

package com.perfecciondigital.workshop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.perfecciondigital.workshop.entity.Person;

@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
	
}
