package com.crud.crud.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pat_objeto")
@EntityListeners(AuditingEntityListener.class)

public class Objeto {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="descricao")
	@Getter
	@Setter
	private String descricao;
	
	@Column(name="patrimonioAntigo")
	@Getter
	@Setter
	private String patrimonioAntigo;
	
	@Column(name="pendencias")
	@Getter
	@Setter
	private String pendencias;
	
	@Column(name="patrimonioNovo")
	@Getter
	@Setter
	private String patrimonioNovo;
	
	@Column(name="patrimonioPolitec")
	@Getter
	@Setter
	private Integer patrimonioPolitec;
	
	@Column(name="estado")
	@Getter
	@Setter
	@Enumerated(value = EnumType.STRING)
	private EstadoConservacao estado;
	
	@Column(name="=createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date createdAt;
}
