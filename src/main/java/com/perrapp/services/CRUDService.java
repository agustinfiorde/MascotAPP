package com.perrapp.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.perrapp.errors.MascotAppException;

public interface CRUDService<D> {

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public D save(D d) throws MascotAppException;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public D edit(D d) throws MascotAppException;
	
	@Transactional(readOnly = true)
	public D getOne(String id);
	
	@Transactional(readOnly = true)
	public List<D> getAll();
	
	@Transactional(readOnly = true)
	public List<D> getAll(Pageable page);
	
	@Transactional(readOnly = true)
	public List<D> searchAll(String q);
	
	@Transactional(readOnly = true)
	public List<D> searchAll(Pageable page);
	
	public void validator(D d) throws MascotAppException;
}
