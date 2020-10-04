package com.perfecciondigital.workshop.repository;

import java.io.Serializable;
import java.util.List;

public interface ServiceClient<T> extends Serializable {

	List<T> get();
	
	T post(T entity);
	
	T put(String id, T entity);
	
	T patch(String id, T entity);
	
	void delete(String id);
	
}
