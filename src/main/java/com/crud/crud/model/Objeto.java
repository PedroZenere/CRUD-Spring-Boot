package com.crud.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="pat_objeto")
@EntityListeners(AuditingEntityListener.class)

public class Objeto {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="patrimonioAntigo")
	private String patrimonioAntigo;
	
	@Column(name="pendencias")
	private String pendencias;
	
	@Column(name="patrimonioNovo")
	private String patrimonioNovo;
	
	@Column(name="patrimonioPolitec")
	private Integer patrimonioPolitec;
	
	@Column(name="estado")
	@Enumerated(value = EnumType.STRING)
	private EstadoConservacao estado;
}
