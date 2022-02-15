package com.formacion.app.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private long id_rol;
	
	private String tipo_empleado;

	public long getId_rol() {
		return id_rol;
	}

	public void setId_rol(long id_rol) {
		this.id_rol = id_rol;
	}

	public String getTipo_empleado() {
		return tipo_empleado;
	}

	public void setTipo_empleado(String tipo_empleado) {
		this.tipo_empleado = tipo_empleado;
	}


}
