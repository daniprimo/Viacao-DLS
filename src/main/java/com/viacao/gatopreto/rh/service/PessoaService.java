package com.viacao.gatopreto.rh.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viacao.gatopreto.controller.PessoaController;
import com.viacao.gatopreto.handleExceptions.exceptions.ErroAoDeletarFuncionario;
import com.viacao.gatopreto.handleExceptions.exceptions.FalhaAoBuscarAsException;
import com.viacao.gatopreto.handleExceptions.exceptions.ObjetoNaoFoiSalvoException;
import com.viacao.gatopreto.rh.config.PessoaMapper;
import com.viacao.gatopreto.rh.dto.PessoaDTO;
import com.viacao.gatopreto.rh.model.Pessoa;
import com.viacao.gatopreto.rh.repository.PessoaRepository;

@Service
public class PessoaService {

	private static final Logger LOG = LoggerFactory.getLogger(PessoaController.class);
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa createFuncionario(Pessoa pessoa) {
		LOG.info("SERVICE RH - Registranto novo funcionario nome={}", pessoa.getNomeCompleto());
		try {
			LOG.info("SERVICE RH - Funcionario sendo salvo nome={}", pessoa.getNomeCompleto());
			Pessoa pessoaSalva = pessoaRepository.save(pessoa);
			LOG.info("SERVICE RH - Funcionario salvo com sucesso {}", pessoaSalva.getNomeCompleto());
			return pessoaSalva;
		} catch (Exception e) {
			LOG.info("SERVICE RH - Funcionario de nome={} não foi salvo", pessoa.getNomeCompleto());
			throw new ObjetoNaoFoiSalvoException("SERVICE RH - Não foi possivel salvar o funcionario, devido campos incorretos"
					+ "ou o funcionario já existentes");
		}
	}

	public List<Pessoa> buscarFuncionarios() {
		try {
			LOG.info("SERVICE RH - Buscando todos funcionarios");
			List<Pessoa> listaDeFuncionarios = pessoaRepository.findAll();
			LOG.info("SERVICE RH - Sucesso ao buscar {} funcionarios ", listaDeFuncionarios.size());
			return listaDeFuncionarios;
		} catch (Exception e) {
			LOG.info("SERVICE RH - Falha ao buscar todos os funcionarios");
			throw new FalhaAoBuscarAsException("SERVICE RH - A falha está ao buscar todos os funcioanrios,"
					+ "do Banco de dados");
		}
	}

	public Pessoa buscarFuncionarios(Long id) {
		try {
			LOG.info("SERVICE RH - BuscandoFuncionario pelo id={}", id);
			Optional<Pessoa> optional = pessoaRepository.findById(id);
			Pessoa pessoa = optional.get();
			LOG.info("SERVICE RH - Funcionario encontrado nome={}", pessoa.getNomeCompleto());
			return pessoa;
		} catch (Exception e) {
			LOG.info("SERVICE RH - Falha ao buscar funcionario pelo id = {}", id);
			throw new FalhaAoBuscarAsException(String.format("SERVICE RH - Falha ao buscar o funcionario"
					+ " pelo id=%d", id));
		}
	}

	public Pessoa alterarFuncionario(Long id, Pessoa dadosAtualizados) {
			Optional<Pessoa> optional = pessoaRepository.findById(id);
		try {
			Pessoa dadoAtuais = optional.get();
			dadosAtualizados.setId(dadoAtuais.getId());
			LOG.info("SERVICE RH - Funcionario do id={} encontrado e atualizado", dadoAtuais.getId());
			return pessoaRepository.save(dadosAtualizados);
		} catch (Exception e) {
			LOG.info("SERVICE RH - Falha ao buscar funcionario pelo id = {} não foi possivel salvar dados alterados", id);
			throw new FalhaAoBuscarAsException(String.format("SERVICE RH - Erro ao atualizar o funcionario codigo=%d", id));
		}

	}

	public void deletarFuncionario(Long id) {
		Pessoa funcionario = buscarFuncionarios(id);
		try {			
			pessoaRepository.delete(funcionario);
			LOG.info("SERVICE RH - Funcionario deletado {}", funcionario);
		} catch (Exception e) {
			LOG.info("SERVICE RH - Erro ao deletar funcionario com id {}", id);
			throw new ErroAoDeletarFuncionario(String.format("SERVICE RH - Falha ao excluir o funcionario codigo=%d", id));
		}
	}
	
}
