package com.viacao.gatopreto.rh.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viacao.gatopreto.handleExceptions.exceptions.FalharAoRealziarMapper;
import com.viacao.gatopreto.rh.dto.PessoaDTO;
import com.viacao.gatopreto.rh.model.Pessoa;
import com.viacao.gatopreto.rh.model.enums.EnumsCargos;

public class PessoaMapper {

	private static final Logger LOG = LoggerFactory.getLogger(PessoaMapper.class);

	public static Pessoa fromEntity(PessoaDTO dto) {
		LOG.info("MAPPER PESSOA - Construindo Pessoa com o dto {}", dto);
		Pessoa pessoa = new Pessoa(dto.getNome(), dto.getIdade(), dto.getRg(), dto.getCpf(), dto.getEmail(),
				EnumsCargos.MOTORISTA, dto.getCep(), dto.getLogradouro(), dto.getAdmissao());
		return pessoa;
	}

	public static Pessoa fromEntityUpdate(PessoaDTO dto, Pessoa funcionarioDadosAtual) {
		try {
			
			funcionarioDadosAtual.setNomeCompleto(dto.getNome());
			funcionarioDadosAtual.setIdade(dto.getIdade());
			funcionarioDadosAtual.setDocumentoRg(dto.getRg());
			funcionarioDadosAtual.setDocumentoCpf(dto.getCpf());
			funcionarioDadosAtual.setEmail(dto.getEmail());
			funcionarioDadosAtual.setCargo(dto.getCargo());
			funcionarioDadosAtual.setCep(dto.getCep());
			funcionarioDadosAtual.setLogradouro(dto.getLogradouro());
			funcionarioDadosAtual.setAdmissao(dto.getAdmissao());
			LOG.info(String.format("MAPPER RH - Dados atualizados do funcionario id={}", funcionarioDadosAtual.getId()));	
		return funcionarioDadosAtual;
		} catch (Exception e) {
			LOG.info(String.format("MAPPER RH - Erro ao atualizar dados do funcionario id={}", funcionarioDadosAtual.getId()));	
			throw new FalharAoRealziarMapper(String.format("MAPPER RH - Erro ao construir funcionario para atualizar id={}", funcionarioDadosAtual.getId()));
		}
		
	}

}
