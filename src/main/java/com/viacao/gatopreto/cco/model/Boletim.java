package com.viacao.gatopreto.cco.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tb_boletimDeOcorrencia")
public class Boletim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private ColetivoBoletim coletivo;
	
	@Embedded
	private LinhaBoletim linha;
	
	private String classificacao;

	private String descricao;
	
	private String acaoTomada;
	
	private LocalDate primeiroRegistro;
	
	private LocalDate fehamentoRegistro;
	
}
