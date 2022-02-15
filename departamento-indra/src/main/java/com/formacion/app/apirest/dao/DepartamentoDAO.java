package com.formacion.app.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.app.apirest.entity.Departamento;

@Repository
public interface DepartamentoDAO extends CrudRepository<Departamento, Long> {

}
