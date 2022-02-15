package com.formacion.app.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.app.apirest.entity.Departamento;
import com.formacion.app.apirest.service.DepartamentoServiceImpl;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	DepartamentoServiceImpl departamentoServiceImpl;

	
	@GetMapping("/all")
	public ResponseEntity<List<Departamento>> getAllDepartamentos() {
		return new ResponseEntity<>(departamentoServiceImpl.getDepartamentos(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getDepartamento(@PathVariable Long id) {
		Departamento departamento = null;
		Map<String, Object> response = new HashMap<>();
		try {
			departamento = departamentoServiceImpl.getDepartamento(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar consulta a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (departamento == null) {
			response.put("mensaje", "El departamento ID: " + id.toString() + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);
	}
	
	
	@PostMapping("")
	public ResponseEntity<?> postDepartamento(@RequestBody Departamento departamento) {
		Departamento newDepartamento = null;
		Map<String, Object> response = new HashMap<>();
		try {
			newDepartamento = departamentoServiceImpl.postDepartamento(departamento);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al guardar en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (newDepartamento == null) {
			response.put("mensaje", "El departamento: " + departamento.getNombre()+" no se ha guardado en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		response.put("departamento", newDepartamento);
		response.put("mensaje", "Se ha guardado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putDepartamento(@RequestBody Departamento departamento, @PathVariable long id) {
		Departamento editDepartamento = null;
		Map<String, Object> response = new HashMap<>();
		try {
			editDepartamento = departamentoServiceImpl.putDepartamento(departamento, id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al editar al departamento");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (editDepartamento == null) {
			response.put("mensaje", "No se han hecho cambios para este departamento");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		response.put("departamento", editDepartamento);
		response.put("mensaje", "Se ha editado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDepartamento(@PathVariable long id) {
		Departamento deleteDepartamento = null;
		Map<String, Object> response = new HashMap<>();
		try {
			deleteDepartamento = departamentoServiceImpl.deleteDepartamento(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			if (deleteDepartamento == null) {
				response.put("error2", "No existe el departamento: " + id+ " o no se puede eliminar");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
			}
			response.put("mensaje", "Error al eliminar al departamento");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("departamento", deleteDepartamento);
		response.put("mensaje", "Se ha eliminado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
}
