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

import com.formacion.app.apirest.entity.Rol;
import com.formacion.app.apirest.service.RolServiceImpl;

@RestController
@RequestMapping("/roles")
public class RolController {
	
	@Autowired
	RolServiceImpl rolServiceImpl;

	@GetMapping("/all")
	public ResponseEntity<List<Rol>> getAllClientes() {
		return new ResponseEntity<>(rolServiceImpl.getRoles(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getRol(@PathVariable Long id) {
		Rol rol = null;
		Map<String, Object> response = new HashMap<>();
		try {
			rol = rolServiceImpl.getRol(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar consulta a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (rol == null) {
			response.put("mensaje", "El rol ID: " + id.toString() + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Rol>(rol, HttpStatus.OK);
	}
	
	
	@PostMapping("")
	public ResponseEntity<?> postRol(@RequestBody Rol rol) {
		Rol newRol = null;
		Map<String, Object> response = new HashMap<>();
		try {
			newRol = rolServiceImpl.postRol(rol);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al guardar en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (newRol == null) {
			response.put("mensaje", "El rol: " + rol.getTipo_empleado()+" no se ha guardado en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		response.put("rol", newRol);
		response.put("mensaje", "Se ha guardado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putRol(@RequestBody Rol rol, @PathVariable long id) {
		Rol editRol = null;
		Map<String, Object> response = new HashMap<>();
		try {
			editRol = rolServiceImpl.putRol(rol, id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al editar el rol");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (editRol == null) {
			response.put("mensaje", "No se han hecho cambios para este rol");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		response.put("rol", editRol);
		response.put("mensaje", "Se ha editado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRol(@PathVariable long id) {
		Rol deleteRol = null;
		Map<String, Object> response = new HashMap<>();
		try {
			deleteRol = rolServiceImpl.deleteRol(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			if (deleteRol == null) {
				response.put("error2", "No existe el rol: " + id+ " o no se puede eliminar");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
			}
			response.put("mensaje", "Error al eliminar el rol");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("rol", deleteRol);
		response.put("mensaje", "Se ha eliminado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	
}
