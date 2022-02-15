package com.formacion.app.apirest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.formacion.app.apirest.entity.Departamento;

@Service
public interface DepartamentoService {

	List<Departamento> getDepartamentos();

	Departamento getDepartamento(long id);

	Departamento postDepartamento(Departamento departamento);

	Departamento putDepartamento(Departamento departamento, long id);
	
	Departamento deleteDepartamento(long id);
}
