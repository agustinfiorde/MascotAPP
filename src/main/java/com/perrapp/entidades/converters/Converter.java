package com.perrapp.entidades.converters;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

public abstract class Converter<E extends Object, D extends Object> {

	@Autowired
	ModelMapper modelMapper;

	public abstract D entidadToDto(E entity);

	public abstract E dtoToEntity(D dto) throws ParseException;

	public List<D> entidadesToDto(List<E> entidades) {
		List<D> list = new ArrayList<D>();
		entidades.forEach((aux)->list.add(entidadToDto(aux)));
		return list;
	};

	public List<E> dtosToEntidades(List<D> dtos) {
		List<E> list = new ArrayList<E>();
		dtos.forEach((aux)->list.add(dtoToEntity(aux)));
		return list;
	};

}
