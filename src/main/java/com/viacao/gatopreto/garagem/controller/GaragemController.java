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

import com.viacao.gatopreto.garagem.model.Garagem;
import com.viacao.gatopreto.garagem.service.GaragemService;

@RestController
@RequestMapping("garagem")
public class GaragemController {

	private static final Logger LOG = LoggerFactory.getLogger(GaragemController.class);

	@Autowired
	private GaragemService garagemService;
	
	@PostMapping
	public ResponseEntity<Garagem> registrandoNovaGaragem(@RequestBody Garagem garagem) {
		LOG.info("CONTROLLER GARAGEM - Criando uma nova garagem nome={}", garagem.getNome());
		return ResponseEntity.ok(garagemService.novaGaragem(garagem));
	}
	
	@GetMapping
	public ResponseEntity<List<Garagem>> buscarTodasGaragens() {
		LOG.info("CONTROLLER GARAGEM - Buscando todas as {} garagens");
		return ResponseEntity.ok(garagemService.buscarTodasGaragens());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Garagem> buscarGaragemPorId(@PathVariable Long id) {
		LOG.info("CONTROLLER GARAGEM - Buscando a garagem com o id={}", id);
		return ResponseEntity.ok(garagemService.buscarGaragemPorId(id));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Garagem> atualizandosDadosDaGaragem(@PathVariable Long id, @RequestBody Garagem garagem) {
		LOG.info("CONTROLLER GARAGEM - Atualizando dados da garagem de id={}", id);
		return ResponseEntity.ok(garagemService.atualziarDadosDaGaragem(id, garagem));
	}
	
	@DeleteMapping("{id}")
	public void deletarGaragem(@PathVariable Long id) {
		LOG.info("CONTROLLER GARAGEM - Deletando a garagem de id={}", id);
		garagemService.deletarGaragemPorId(id);
	}

}
