package com.crud.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crud.DAO.ObjetoDAO;
import com.crud.crud.model.Objeto;

@Controller
@RestController
@RequestMapping("/objetos")
public class ObjetoController {
	
	@Autowired
	ObjetoDAO objetoDAO;
	
	/* Salvar um objeto */
	@PostMapping("/objetos")
	public Objeto createObjeto(@Validated @RequestBody Objeto emp) {
		return objetoDAO.save(emp);
	}
	
	/* Listar todos os objetos */
	@GetMapping("/objetos")
	public List<Objeto> getAllObjeto(){
		return objetoDAO.findAll();
	}
	
	/* Listar um objeto pelo ID */
	@GetMapping("/objetos/{id}")
	public ResponseEntity<Objeto> getObjetoById(@PathVariable(value = "id") Long objid) {
		
		Objeto obj = objetoDAO.findOne(objid);
		
		if(obj == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(obj);
	}
	
	/* Atualizar um objeto */
	@PutMapping("/objetos/{id}")
	public ResponseEntity<Objeto> updateObjeto(@PathVariable(value = "id") Long objid, @Validated @RequestBody Objeto objDetails) {
		
		Objeto obj = objetoDAO.findOne(objid);
		
		if(obj == null) {
			return ResponseEntity.notFound().build();
		}
		
		obj.setDescricao(objDetails.getDescricao());
		obj.setEstado(objDetails.getEstado());
		obj.setPendencias(objDetails.getPendencias());
		obj.setPatrimonioAntigo(objDetails.getPatrimonioAntigo());
		obj.setPatrimonioNovo(objDetails.getPatrimonioNovo());
		obj.setPatrimonioPolitec(objDetails.getPatrimonioPolitec());
		
		Objeto objUpdate = objetoDAO.save(obj);
		
		return ResponseEntity.ok().body(objUpdate);
		
	}
	
	/* Apagar um objeto */
	@DeleteMapping("/objetos/{id}")
	public ResponseEntity<Objeto> deleteObjeto(@PathVariable(value = "id") Long objid) {
		
		Objeto obj = objetoDAO.findOne(objid);
		
		if(obj == null) {
			return ResponseEntity.notFound().build();
		}
		
		objetoDAO.delete(obj);
		
		return ResponseEntity.ok().build();
	}
	
}
