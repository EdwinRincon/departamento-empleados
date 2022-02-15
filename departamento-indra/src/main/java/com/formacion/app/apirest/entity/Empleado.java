package com.formacion.app.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="empleados")
public class Empleado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEmpleado")
	private long idEmpleado;
	
	@Column(nullable = false)
	private String dni,nombre,telefono,password;
	@Column(nullable = false)
	private float salario;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_departamento")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Departamento departamento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_rol")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Rol rol;
	
	
	public long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	
	
	
	

}
