package com.viacao.gatopreto.cco.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ColetivoBoletim {

	private Long prefixo;
	private String placa;
	private String status;
	
}
