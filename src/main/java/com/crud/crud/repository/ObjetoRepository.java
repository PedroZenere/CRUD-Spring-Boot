package com.crud.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.crud.model.Objeto;

public interface ObjetoRepository extends JpaRepository<Objeto, Long>{

	Objeto findOne(Long id);
	
}
