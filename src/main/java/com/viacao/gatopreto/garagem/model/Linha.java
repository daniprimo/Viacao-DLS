package com.viacao.gatopreto.garagem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_linha")
public class Linha {
	
	@Id
	private Long linha;	
	
	private String itinerarioIda;

	private String itinerarioVolta;

	private String consorcio;
	
}
