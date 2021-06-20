package com.bruno.sabium.service;

import java.util.List;

public interface CrudService<E> {

	
	public <T> List<E> findAll();
	public <T> Object findOne(long id);
	public <T> Object save(E entity);
	public void delete(long id);
}
