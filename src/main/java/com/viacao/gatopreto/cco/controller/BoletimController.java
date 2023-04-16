package com.viacao.gatopreto.cco.controller;

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

import com.viacao.gatopreto.cco.model.Boletim;
import com.viacao.gatopreto.cco.service.BoletimService;

@RestController
@RequestMapping("cco/boletim")
public class BoletimController {

	private static final Logger LOG = LoggerFactory.getLogger(BoletimController.class);
	
	@Autowired
	private BoletimService boletimService;
	
	@PostMapping
	public ResponseEntity<Boletim> novaOcorrencia (@RequestBody Boletim boletim) {
		LOG.info("CONTROLLER BOLETIM - Ocorrencia nova sendo registrada");
		return ResponseEntity.ok(boletimService.novaOcorrencia(boletim));
	}
	
	@GetMapping
	public ResponseEntity<List<Boletim>> buscarTodasOcorrencia () {
		LOG.info("CONTROLLER BOLETIM - Buscando todas as ocorrencias");
		return ResponseEntity.ok(boletimService.todasAsOcorrencia());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Boletim> buscarOcorrenciaPeloId (@PathVariable Long id) {
		LOG.info("CONTROLLER BOLETIM - Buscando a ocorrencia de id {}", id);
		return ResponseEntity.ok(boletimService.ocorrenciaBuscadaPorId(id));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Boletim> atuaizarOcorrenciaPeloId (@PathVariable Long id, @RequestBody Boletim boletim) {
		LOG.info("CONTROLLER BOLETIM - Atualizar a ocorrencia pelo id {}", id);
		return ResponseEntity.ok(boletimService.ocorrenciaBuscadaPorIdAtualizada(id, boletim));
	}
	
	@DeleteMapping("{id}")
	public void deletarOcorrenciaPeloId (@PathVariable Long id) {
		LOG.info("CONTROLLER BOLETIM - Deletar a ocorrencia pelo id {}", id);
		boletimService.ocorrenciaDeletadaPorId(id);
	}
	
}
