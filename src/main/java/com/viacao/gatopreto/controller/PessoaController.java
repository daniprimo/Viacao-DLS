package com.viacao.gatopreto.controller;

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

import com.viacao.gatopreto.rh.config.PessoaMapper;
import com.viacao.gatopreto.rh.dto.PessoaDTO;
import com.viacao.gatopreto.rh.model.Pessoa;
import com.viacao.gatopreto.rh.service.PessoaService;

@RestController
@RequestMapping("rh")
public class PessoaController {

	private static final Logger LOG = LoggerFactory.getLogger(PessoaController.class);
	
	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping
	public ResponseEntity<Pessoa> novoFuncionario (@RequestBody PessoaDTO pessoadDto) {
		Pessoa pessoa = PessoaMapper.fromEntity(pessoadDto);
		LOG.info("CONTROLLER RH - Registrando novo funcionario {}", pessoa);
		return ResponseEntity.ok(pessoaService.createFuncionario(pessoa));
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> buscarFuncionarios () {
		LOG.info("CONTROLLER RH - Buscando todos funcionario");
		return ResponseEntity.ok(pessoaService.buscarFuncionarios());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pessoa> buscarFuncionariosPorId (@PathVariable Long id) {
		LOG.info("CONTROLLER RH - Buscando funcionario pelo id {}", id);
		return ResponseEntity.ok(pessoaService.buscarFuncionarios(id));
	}

	@PutMapping("{id}")
	public ResponseEntity<Pessoa> alterarFuncionariosPorId (@PathVariable Long id, @RequestBody Pessoa pessoa) {
		LOG.info("CONTROLLER RH - Buscando funcionario pelo id {} para atualizar os dados", id);
		return ResponseEntity.ok(pessoaService.alterarFuncionario(id, pessoa));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletarFuncionariosPorId (@PathVariable Long id) {
		LOG.info("CONTROLLER RH - Buscando funcionario pelo id {} para deletar funcionario do banco", id);
		pessoaService.deletarFuncionario(id);
		return ResponseEntity.ok(String.format("Funcionario do codigo {} excluido com sucesso", id));
	}
	
	
	
}
