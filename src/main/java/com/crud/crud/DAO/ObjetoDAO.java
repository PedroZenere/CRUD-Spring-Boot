package com.crud.crud.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.crud.model.Objeto;
import com.crud.crud.repository.ObjetoRepository;

@Service
@Transactional
public class ObjetoDAO {
	
	@Autowired
	ObjetoRepository objetoRepository;
	
	/*Salvar um objeto */
	public Objeto save(Objeto emp) {
		return objetoRepository.save(emp);
	}
	
	
	/*Encontrar um objeto */
	public Objeto findOne(Long id) {
		return objetoRepository.findOne(id);
	}
	
	
	/*Encontrar todos os objetos */
	public List<Objeto> findAll(){
		return objetoRepository.findAll();
	}
	
	/*Deletar um objeto */
	public void delete(Objeto emp) {
		objetoRepository.delete(emp);
	}
}
