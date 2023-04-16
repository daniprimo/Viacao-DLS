package com.viacao.gatopreto.garagem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viacao.gatopreto.garagem.model.Coletivo;
import com.viacao.gatopreto.garagem.service.ColetivoService;

@RestController
@RequestMapping("garagem/coletivo")
public class ColetivoController {

	private static final Logger LOG = LoggerFactory.getLogger(ColetivoController.class);
	
	@Autowired
	private ColetivoService coletivoService;
	
	@PostMapping
	public ResponseEntity<Coletivo> novoColetivo (@RequestBody Coletivo coletivo) {
		LOG.info("CONTROLLER COLETIVO - Coletivo {} Sendo salvo", coletivo.getPrefixo());
		return ResponseEntity.ok(coletivoService.novoColetivo(coletivo));
	}
	
	@GetMapping
	public ResponseEntity<List<Coletivo>> buscarTodosOsColetivo () {
		LOG.info("CONTROLLER COLETIVO - Listas de coletivos");
		return ResponseEntity.ok(coletivoService.buscarTodosColetivo());
	}
	
	@GetMapping("{prefixo}")
	public ResponseEntity<Coletivo> buscarColetivoPorPrefixo (@PathVariable Long prefixo) {
		LOG.info("CONTROLLER COLETIVO - Buscando coletivo com prefixo {}", prefixo);
		return ResponseEntity.ok(coletivoService.buscarColetivoPorPrefixo(prefixo));
	}
	
	@PutMapping("{prefixo}")
	public ResponseEntity<Coletivo> alterarColetivoPorPrefixo (@PathVariable Long prefixo, @RequestBody Coletivo coletivo) {
		LOG.info("CONTROLLER COLETIVO - Buscando coletivo com prefixo {}", prefixo);
		return ResponseEntity.ok(coletivoService.atualziarColetivoPorPrefixo(prefixo, coletivo));
	}
	
	@DeleteMapping("{prefixo}")
	public void deletarColetivoPorPrefixo (@PathVariable Long prefixo) {
		LOG.info("CONTROLLER COLETIVO - Buscando coletivo com prefixo {}", prefixo);
		coletivoService.deletarColetivoPorPrefixo(prefixo);
	}
	
}
