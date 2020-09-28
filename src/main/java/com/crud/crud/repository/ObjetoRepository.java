package com.crud.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.crud.model.Objeto;

public interface ObjetoRepository extends JpaRepository<Objeto, Long>{

	Objeto findOne(Long id);
	
	//Convençao de nomenclatura para realizar consultas
	Optional<Objeto> findByPatrimonioAntigo(String patrimonioAntigo);
	
	Optional<Objeto> findByPatrimonioNovo(String patrimonioNovo);
}
