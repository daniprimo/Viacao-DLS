package com.viacao.gatopreto.garagem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tab_coletivo")
public class Coletivo {

	@Id
	@Min(100)
	private Long prefixo;
		
	private String placa;
	
	private String status;
	
	private String consorcio;
	
}
