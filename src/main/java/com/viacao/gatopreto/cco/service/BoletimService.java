package com.viacao.gatopreto.cco.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viacao.gatopreto.cco.model.Boletim;
import com.viacao.gatopreto.cco.repository.BoletimRepository;
import com.viacao.gatopreto.handleExceptions.exceptions.ObjetoNaoFoiSalvoException;

@Service
public class BoletimService {

	private static final Logger LOG = LoggerFactory.getLogger(BoletimService.class);
	
	@Autowired
	private BoletimRepository boletimRepository;
	
	public Boletim novaOcorrencia (Boletim boletim) {
		try {
			LOG.info("SERVICE BOLETIM - Registrando nova ocorrencia {}", boletim.getClassificacao());
			Boletim ocorenciaNova = boletimRepository.save(boletim);
			LOG.info("SERVICE BOLETIM - Ocorrencia de id={} registrada com sucesso!", boletim.getId());
			return ocorenciaNova;
		} catch (Exception e) {
			LOG.info("SERVICE BOLETIM - Ocorrência de {} não foi registrada!", boletim.getClassificacao());
			throw new ObjetoNaoFoiSalvoException(String.format("SERVICE BOLETIM - Ocorrencia de %s não foi salva, "
					+ "confira os dados novamente", boletim.getClassificacao()));
		}
	}
	
	public List<Boletim> todasAsOcorrencia () {
		try {
			LOG.info("SERVICE BOLETIM - Buscando todas as ocorrencias registradas.");
			List<Boletim> todosBoletins = boletimRepository.findAll();
			LOG.info("SERVICE BOLETIM - {} encontradas!", todosBoletins.size());
			return todosBoletins;
		} catch (Exception e) {
			LOG.info("SERVICE BOLETIM - Não foi possivel buscar todas as ocorrências");
			throw new ObjetoNaoFoiSalvoException(String.format("SERVICE BOLETIM - Ocorrencias não foram encontradas."));
		}
	}
	
	public Boletim ocorrenciaBuscadaPorId (Long id) {
		try {
			LOG.info("SERVICE BOLETIM - Buscando ocorrencia pelo id {}.", id);
			Optional<Boletim> optional = boletimRepository.findById(id);
			LOG.info("SERVICE BOLETIM - Ocorrencia de {} foi encontrada pelo {}!", optional.get().getClassificacao(), optional.get().getId());
			return optional.get();
		} catch (Exception e) {
			LOG.info("SERVICE BOLETIM - Não foi possivel encontrar o id {}.", id);
			throw new ObjetoNaoFoiSalvoException(String.format("SERVICE BOLETIM - Ocorrencias do id %S não foi encontrada.", id));
		}
	}
	
	public Boletim ocorrenciaBuscadaPorIdAtualizada (Long id, Boletim boletim) {
		Boletim ocorrenciaAtual = ocorrenciaBuscadaPorId(id);
		try {
			LOG.info("SERVICE BOLETIM - Ocorrencia {} sendo atualizada!", ocorrenciaAtual.getId());
			boletim.setId(ocorrenciaAtual.getId());
			LOG.info("SERVICE BOLETIM - Ocorrencia {} atualizado com sucesso!", boletim.getId());
			return boletim;
		} catch (Exception e) {
			LOG.info("SERVICE BOLETIM - Não foi possivel atualizar a ocorrencia do id {}.", id);
			throw new ObjetoNaoFoiSalvoException(String.format("SERVICE BOLETIM - Ocorrencias do id %S não foi atualizada.", id));
		}
	}
	
	public void ocorrenciaDeletadaPorId (Long id) {
			LOG.info("SERVICE BOLETIM - Ocorrencia {} sendo deletada!", id);
			boletimRepository.deleteById(id);
			LOG.info("SERVICE BOLETIM - Ocorrencia {} foi deletada!", id);
	}
	
}
