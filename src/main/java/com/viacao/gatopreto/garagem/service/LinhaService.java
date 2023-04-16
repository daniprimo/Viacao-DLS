package com.viacao.gatopreto.garagem.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viacao.gatopreto.garagem.model.Linha;
import com.viacao.gatopreto.garagem.repository.LinhaRepository;
import com.viacao.gatopreto.handleExceptions.exceptions.ErroAoDeletarFuncionario;
import com.viacao.gatopreto.handleExceptions.exceptions.FalhaAoBuscarAsException;
import com.viacao.gatopreto.handleExceptions.exceptions.ObjetoNaoFoiSalvoException;

@Service
public class LinhaService {

	private static final Logger LOG = LoggerFactory.getLogger(LinhaService.class);
	
	@Autowired
	private LinhaRepository linhaRepository;
	
	public Linha criarNovoLinha (Linha linha) {
		try {
			LOG.info("SERVICE LINHA - Registrando novo linha {}.", linha);
			Linha linhaSalva = linhaRepository.save(linha);
			LOG.info("SERVICE LINHA - Linha {} registrada com sucesso!.", linha);
			return linhaSalva;
		} catch (Exception e) {
			LOG.info("SERVICE LINHA - Linha {} não foi registrada devido falha!!.", linha);
			throw new ObjetoNaoFoiSalvoException(String .format("SERVICE LINHA - Linha %d não foi registrado devido falha.", linha));
		}
	}

	public List<Linha> buscandoTodasAsLinhas() {
		try {
			LOG.info("SERVICE LINHA - Buscando todas as linhas");
			List<Linha> linhas = linhaRepository.findAll();
			LOG.info("SERVICE LINHA - Buscando todas as linhas com sucesso!");
			return linhas;
		} catch (Exception e) {
			LOG.info("SERVICE LINHA - Erro ao buscar toads linhas!");
			throw new FalhaAoBuscarAsException(String.format("SERVICE LINHA - Erro ao "
					+ "buscar todas linhas"));
		}
	}

	public Linha buscandoLinhaPeloNumero(Long linha) {
		try {
			LOG.info("SERVICE LINHA - Linha {} sendo buscada no banco dedados", linha);
			Optional<Linha> optional = linhaRepository.findById(linha);
			LOG.info("SERVICE LINHA - Linha {} encontrada com sucesso.", optional.get().getLinha());
			return optional.get();
		} catch (Exception e) {
			LOG.info("SERVICE LINHA - Linha {} não  encontrada.", linha);
			throw new FalhaAoBuscarAsException(String.format("SERVICE LINHA - "
					+ "Linha %d não foi encontrada por ser numero invalido ou inexistente", linha));
			// TODO: handle exception
		}
	}

	public Linha buscandoLinhaPeloNumeroParaAtualizar(Long linha, Linha objetoLinhaAtualizado) {
		LOG.info("SERVICE LINHA - Buscando a linha {} com os dados atuais.", linha);
		Linha linhaAtual = buscandoLinhaPeloNumero(linha);
		try {
			LOG.info("SERVICE LINHA - Linha {} encontrada e sendo atualizado.", linha);
			objetoLinhaAtualizado.setLinha(linhaAtual.getLinha());			
			LOG.info("SERVICE LINHA - Linha {} atualizada com sucesso.", linha);
			return objetoLinhaAtualizado;
		} catch (Exception e) {
			LOG.info("SERVICE LINHA - Erro ao atualizar a linha {}.", linha);
			throw new ObjetoNaoFoiSalvoException(String.format("SERVICE LINHA - "
					+ "Falha ao atualizar a Linha %d", linha));
		}
	}

	public void deletarLinhaPorNumeroDaLinha(Long linha) {
		try {
			LOG.info("SERVICE LINHA - Deletando a linha {}.", linha);
			linhaRepository.deleteById(linha);
			LOG.info("SERVICE LINHA - Deletar a linha {} com sucesso!!", linha);
		} catch (Exception e) {
			LOG.info("SERVICE LINHA - Falha ao Deletar a linha {}.", linha);
			throw new ErroAoDeletarFuncionario(String.format("SERVICE LINHA - "
					+ "Erro ao deletar a linha %d", linha));
		}
	}
	
	
}
