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

import com.viacao.gatopreto.garagem.model.Linha;
import com.viacao.gatopreto.garagem.service.LinhaService;

@RestController
@RequestMapping("garagem/linhas")
public class LinhaController {
	

	private static final Logger LOG = LoggerFactory.getLogger(LinhaController.class);
	
	@Autowired
	private LinhaService linhaService;

	@PostMapping
	public ResponseEntity<Linha> novaLinha (@RequestBody Linha linha) {
		LOG.info("LINHA CONTROLLER - Nova Linha {} sendo criada", linha);
		return ResponseEntity.ok(linhaService.criarNovoLinha(linha));
	}
	
	@GetMapping
	public ResponseEntity<List<Linha>> buscarTodasLinhas () {
		LOG.info("LINHA CONTROLLER - Buscando todas as linhas.");
		return ResponseEntity.ok(linhaService.buscandoTodasAsLinhas());
	}
	
	@GetMapping("{linha}")
	public ResponseEntity<Linha> buscarLinhasPeloNumeroDeLinha (@PathVariable Long linha) {
		LOG.info("LINHA CONTROLLER - Buscando linha pelo {}.", linha);
		return ResponseEntity.ok(linhaService.buscandoLinhaPeloNumero(linha));
	}
	
	@PutMapping("{linha}")
	public ResponseEntity<Linha> atualizarLinha (@PathVariable Long linha, @RequestBody Linha objetoLinhaAtual) {
		LOG.info("LINHA CONTROLLER - Buscando linha {} para ser atualizada.", linha);
		return ResponseEntity.ok(linhaService.buscandoLinhaPeloNumeroParaAtualizar(linha, objetoLinhaAtual));
	}
	
	@DeleteMapping("{linha}")
	public void deletarLinha (@PathVariable Long linha) {
		LOG.info("LINHA CONTROLLER - Deletando a linha {}.", linha);
		linhaService.deletarLinhaPorNumeroDaLinha(linha);
	}
	
}
