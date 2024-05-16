package com.juan.springboot.form.app.services;

import java.util.List;

import com.juan.springboot.form.app.models.domain.Pais;

public interface PaisService {
	
	public List<Pais> listar();

	public Pais getPaisPorId(Integer id);
}
