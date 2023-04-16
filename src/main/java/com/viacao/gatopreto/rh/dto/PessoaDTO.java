package com.viacao.gatopreto.rh.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PessoaDTO {

	private String nome;
	private Integer idade;
	private String rg;
	private String cpf;
	private String email;
	private String cargo;
	private String cep;
	private String logradouro;
	private LocalDate admissao;
	
}
