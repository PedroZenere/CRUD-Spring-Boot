package com.crud.crud.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	@RequestMapping({"/hello"})
	public String hello(){
		return "Hello World";
	}

	@PostMapping
	public ResponseEntity<Objeto> createObjeto(@Validated @RequestBody Objeto obj) throws Exception {
		
		/*Validacao */
		if(obj.getDescricao() == null || obj.getDescricao().trim().equals("")) {
			new ResponseEntity("A descrição do objeto é obrigatório", HttpStatus.BAD_REQUEST);
		}
		
		if(obj.getEstado() == null) {
			new ResponseEntity("É necessário informar um estado de conservação válido", HttpStatus.BAD_REQUEST);
		}
		
		// Verifica se possuí o patrimônio antigo cadastrado na base
		//Verifica se não é nulo
		if(obj.getPatrimonioAntigo() != null ) {
			Optional<Objeto> patAnt = objetoDAO.findByPatrimonioAntigo(obj.getPatrimonioAntigo());
		
			if( patAnt.isPresent()) {
				new ResponseEntity("Já há um objeto cadastrado com o patrimônio informado", HttpStatus.BAD_REQUEST);
			}
		}
		
		// Verifica se possuí o patrimônio novo cadastrado na base
		//Verifica se não é nulo
		if(obj.getPatrimonioNovo() != null) {
			Optional<Objeto> patNov = objetoDAO.findByPatrimonioNovo(obj.getPatrimonioAntigo());
		
			if( patNov.isPresent() ) {
				new ResponseEntity("Já há um objeto cadastrado com o patrimônio informado", HttpStatus.BAD_REQUEST);
			}
		}
		
		try {
			Objeto entidade = objetoDAO.save(obj);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity("Erro ao cadastrar", HttpStatus.BAD_REQUEST);
		}
	}
	
	/* Listar todos os objetos */
	//Falta criar sala para poder realizar as consultas por sala
	@GetMapping("/objetos")
	public List<Objeto> getAllObjeto(){
		return objetoDAO.findAll();
	}
	
	/* Listar um objeto pelo ID */
	@GetMapping("/objetos/{id}")
	public ResponseEntity<Optional<Objeto>> getObjetoById(@PathVariable(value = "id") Long objid) {
		
		Optional<Objeto> obj = objetoDAO.findById(objid);
		
		if(!obj.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(obj);
	}
	
	/* Atualizar um objeto */
	@PutMapping("/objetos/{id}")
	public ResponseEntity<Objeto> updateObjeto(@PathVariable(value = "id") Long objid, @Validated @RequestBody Objeto objDetails) {
		
		Optional<Objeto> obj = objetoDAO.findById(objid);
		
		if(!obj.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		/*Validacao */
		if(objDetails.getDescricao() == null || objDetails.getDescricao().trim().equals("")) {
			new ResponseEntity<Object>("A descrição do objeto é obrigatório", HttpStatus.BAD_REQUEST);
		}
		
		if(objDetails.getEstado() == null) {
			new ResponseEntity("É necessário informar um estado de conservação válido", HttpStatus.BAD_REQUEST);
		}
		
		// Verifica se possuí o patrimônio antigo cadastrado na base
		//Verifica se não é nulo
		if(objDetails.getPatrimonioAntigo() != null ) {
			Optional<Objeto> patAnt = objetoDAO.findByPatrimonioAntigo(objDetails.getPatrimonioAntigo());
		
			if( patAnt.isPresent()) {
				new ResponseEntity<Object>("Já há um objeto cadastrado com o patrimônio informado", HttpStatus.BAD_REQUEST);
			}
		}
		
		// Verifica se possuí o patrimônio novo cadastrado na base
		//Verifica se não é nulo
		if(objDetails.getPatrimonioNovo() != null) {
			Optional<Objeto> patNov = objetoDAO.findByPatrimonioNovo(objDetails.getPatrimonioNovo());
		
			if( patNov.isPresent() ) {
				new ResponseEntity<Object>("Já há um objeto cadastrado com o patrimônio informado", HttpStatus.BAD_REQUEST);
			}
		}
		
		Objeto updateObj = obj.get();
		
		//Realiza a setagem dos atributos
		updateObj.setDescricao(objDetails.getDescricao());
		updateObj.setEstado(objDetails.getEstado());
		updateObj.setPendencias(objDetails.getPendencias());
		updateObj.setPatrimonioAntigo(objDetails.getPatrimonioAntigo());
		updateObj.setPatrimonioNovo(objDetails.getPatrimonioNovo());
		updateObj.setPatrimonioPolitec(objDetails.getPatrimonioPolitec());
		
		//Salva as alterações
		try {
			Objeto objUpdate = objetoDAO.save(updateObj);
		
			return ResponseEntity.ok().body(objUpdate);
		} catch(Exception e) {
			return new ResponseEntity("Erro ao atualizar objeto", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/* Apagar um objeto */
	@DeleteMapping("/objetos/{id}")
	public ResponseEntity<Objeto> deleteObjeto(@PathVariable(value = "id") Long objid) {
		
		Optional<Objeto> obj = objetoDAO.findById(objid);
		
		if(!obj.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Objeto deleteObj = obj.get();
		
		objetoDAO.delete(deleteObj);
		
		return ResponseEntity.ok().build();
	}
	
}
