package com.viacao.gatopreto.garagem.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viacao.gatopreto.garagem.model.Coletivo;
import com.viacao.gatopreto.garagem.repository.ColetivoRepository;
import com.viacao.gatopreto.handleExceptions.exceptions.ErroAoDeletarFuncionario;
import com.viacao.gatopreto.handleExceptions.exceptions.ObjetoNaoFoiSalvoException;

@Service
public class ColetivoService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ColetivoService.class);
	
	@Autowired
	private ColetivoRepository coletivoRepository;
	
	public Coletivo novoColetivo (Coletivo coletivo) {
		try {
			LOG.info("SERVICE COLETIVO - Coletivo {} sendo salvo", coletivo.getPrefixo());
			Coletivo coletivoSalvo = coletivoRepository.save(coletivo);
			LOG.info("SERVICE COLETIVO - Coletivo {} foi salvo", coletivo.getPrefixo());
			return coletivoSalvo;
		} catch (Exception e) {
			LOG.info("SERVICE COLETIVO - Coletivo {} não foi salvo houve algum erro", coletivo.getPrefixo());
			throw new ObjetoNaoFoiSalvoException(String.format("SEVICE COLETIVO - Coletivo não foi salvo prefico {}"
					+ " verifique se os campos estão corretos, ou se ja não existe", coletivo.getPrefixo()));
		}
	}

	public List<Coletivo> buscarTodosColetivo() {
		try {
			LOG.info("SERVICE COLETIVO - Buscando todos os coletivos da base de dados");
			List<Coletivo> coletivos = coletivoRepository.findAll();
			LOG.info("SERVICE COLETIVO - {} coletvos foram baixados", coletivos.size());
			return coletivos;
		} catch (Exception e) {
			LOG.info("SERVICE COLETIVO - Erro ao buscar todos os coletivos");
			throw new ErroAoDeletarFuncionario(String.format("SERVICE COLETIVO - "
					+ "Erro ao buscar todos os coletivos"));
		}
	}
	
	public Coletivo buscarColetivoPorPrefixo(Long prefixo) {
		try {
			LOG.info("SERVICE COLETIVO - Buscando coletivo pelo prefixo {}", prefixo);
			Optional<Coletivo> coletivos = coletivoRepository.findById(prefixo);
			LOG.info("SERVICE COLETIVO - Coletvo de prefixo {} "
					+ "foram encontrados", coletivos.get().getPrefixo());
			return coletivos.get();
		} catch (Exception e) {
			LOG.info("SERVICE COLETIVO - Erro ao buscar todos os coletivos");
			throw new ErroAoDeletarFuncionario(String.format("SERVICE COLETIVO - "
					+ "Erro ao buscar coletivo do prefixo %d invalido ou não existente", prefixo));
		}
	}
	
	public Coletivo atualziarColetivoPorPrefixo(Long prefixo, Coletivo coletivo) {
		Coletivo coletivoAtual = buscarColetivoPorPrefixo(prefixo);
		try {
			LOG.info("SERVICE COLETIVO - Atualizando dados do coletivo {}", prefixo);
			coletivo.setPrefixo(coletivoAtual.getPrefixo());
			LOG.info("SERVICE COLETIVO - Dados atualizado do coletivo"
					+ " de prefixo {}", coletivo.getPrefixo());
			return coletivo;
		} catch (Exception e) {
			LOG.info("SERVICE COLETIVO - Erro ao atualizar o coletivo,"
					+ " de prefico {}", prefixo);
			throw new ErroAoDeletarFuncionario(String.format("SERVICE COLETIVO - "
					+ "Erro ao atualziar o coletivo do prefixo %d invalido ou inexistente", prefixo));
		}
	}
	
	public void deletarColetivoPorPrefixo(Long prefixo) {
		Coletivo coletivoAtual = buscarColetivoPorPrefixo(prefixo);
		try {
			LOG.info("SERVICE COLETIVO - Excluindo coletivo {}", prefixo);
			coletivoRepository.deleteById(prefixo);
			LOG.info("SERVICE COLETIVO - Coletivo {} deletado com sucesso {}", prefixo);
		} catch (Exception e) {
			LOG.info("SERVICE COLETIVO - Erro ao deletar o coletivo,"
					+ " de prefico {}", prefixo);
			throw new ErroAoDeletarFuncionario(String.format("SERVICE COLETIVO - "
					+ "Erro ao deletar o coletivo de prefixo %d invalido ou inexistente", prefixo));
		}
	}
	
	
}
