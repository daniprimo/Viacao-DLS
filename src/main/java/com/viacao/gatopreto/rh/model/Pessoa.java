package com.viacao.gatopreto.rh.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.viacao.gatopreto.rh.model.enums.EnumsCargos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_funcionarios")
public class Pessoa {

		

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(unique = true)
	private String nomeCompleto;
	
	@NonNull
	@Min(18)
	private Integer idade;
	
	private String documentoRg;
	
	@CPF
	@NonNull
	private String documentoCpf;

	@Email(message = "Email invalido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	
	private String cargo;

	private String cep;
		
	private String logradouro;
	
	private String numero;
	
	private String cidade;

	private String estado;

	@NotNull
	private LocalDate admissao;

	private LocalDate demissao;
	
	private LocalDate dataDeRegistro = LocalDate.now();

	
	public Pessoa(String nome, Integer idade, String rg, String cpf, String email, EnumsCargos motorista, String cep,
			String logradouro, LocalDate admissao) {

		this.nomeCompleto = nome;
		this.idade = idade;
		this.documentoRg = rg;
		this.documentoCpf = cpf;
		this.email = email;
		this.cargo = EnumsCargos.MOTORISTA.getCargo();
		this.cep = cep;
		this.logradouro = logradouro;
		this.admissao = admissao;
	}
	
}
