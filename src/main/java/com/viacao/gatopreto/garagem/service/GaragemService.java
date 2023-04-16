package com.viacao.gatopreto.garagem.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viacao.gatopreto.garagem.model.Garagem;
import com.viacao.gatopreto.garagem.repository.GaragemRepository;
import com.viacao.gatopreto.handleExceptions.exceptions.FalhaAoBuscarAsException;
import com.viacao.gatopreto.handleExceptions.exceptions.ObjetoNaoFoiSalvoException;

@Service
public class GaragemService {
	
	private static final Logger LOG = LoggerFactory.getLogger(GaragemService.class);
	
	@Autowired
	private GaragemRepository garagemRepository;
	
	public Garagem novaGaragem(Garagem garagem) {
		try {
			LOG.info("SERVICE GARAGEM - Registrando a garagem nome={}", garagem.getNome());
			garagemRepository.save(garagem);
			return garagem;		
		} catch (Exception e) {
			LOG.info("SERVICE GARAGEM - Falha ao Registrar a garagem nome={}", garagem.getNome());
			throw new ObjetoNaoFoiSalvoException(String.format("SERVICE GARAGEM - Falha ao registrar garagem de nome=%d", garagem.getNome()));

		}
	}
	
	public List<Garagem> buscarTodasGaragens() {
		try {
			List<Garagem> garagens = garagemRepository.findAll();
			LOG.info("SERVICE GARAGEM - Buscando todas as {} garagens", garagens.size());
			return garagens;			
		} catch (Exception e) {
			LOG.info("SERVICE GARAGEM - Falha ao buscando todas as garagens");
			throw new FalhaAoBuscarAsException("SERVICE GARAGEM - Falha ao buscar todas as garagens");
		}
	}
	
	public Garagem buscarGaragemPorId (Long id) {
		try {
			LOG.info("SERVICE GARAGEM - Buscando garagem de id={} em nossos banco de dados", id);
			Optional<Garagem> optional = garagemRepository.findById(id);
			LOG.info("SERVICE GARAGEM - Garagem de id={} encontrado", id);
			return optional.get();			
		} catch (Exception e) {
			LOG.info("SERVICE GARAGEM - Garagem de id={} encontrado", id);
			throw new FalhaAoBuscarAsException(String.format("SERVICE GARAGEM - Garagem com o id=%d não encontrada", id));
			// TODO: handle exception
		}
	}
	
	public Garagem atualziarDadosDaGaragem(Long id, Garagem garagem) {
			Garagem garagemAtual = buscarGaragemPorId(id);
		try {
			LOG.info("SERVICE GARAGEM - buscando garagem id={} para atualizar", id);
			garagem.setId(garagemAtual.getId());
			LOG.info("SERVICE GARAGEM - Attualizando a garagem nome={}", garagemAtual.getNome());
			return garagemRepository.save(garagem);			
		} catch (Exception e) {
			LOG.info("SERVICE GARAGEM - buscando garagem id={} para atualizar", id);
			throw new ObjetoNaoFoiSalvoException(String.format("SERVICE GARAGEM - Objeto não foi atualizado,"
					+ " por que não foi possivel salvar o mesmo no banco de dados"));
		}
	}
	
	public void deletarGaragemPorId (Long id) {
		garagemRepository.deleteById(id);
		LOG.info("SERVICE GARAGEM - Deletar garagem de id={}", id);
		
	}
	
}
