package com.viacao.gatopreto.handleExceptions;

import java.time.Instant;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.viacao.gatopreto.handleExceptions.dto.DefaultError;
import com.viacao.gatopreto.handleExceptions.dto.StandartError;
import com.viacao.gatopreto.handleExceptions.exceptions.ErroAoDeletarFuncionario;
import com.viacao.gatopreto.handleExceptions.exceptions.FalhaAoBuscarAsException;
import com.viacao.gatopreto.handleExceptions.exceptions.FalharAoRealziarMapper;
import com.viacao.gatopreto.handleExceptions.exceptions.ObjetoNaoFoiSalvoException;

@ControllerAdvice
public class ApplicationHandleExceptions extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
		System.out.println("Entro no Erro do Handler");
		
		DefaultError error = new DefaultError(HttpStatus.BAD_GATEWAY.value(), "Falha Tecnica !!!");
		
		
		return new ResponseEntity(error, HttpStatus.BAD_GATEWAY);
		
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandartError> pessoaNaoEncontrado(EntityNotFoundException e, HttpServletRequest request){
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Sem registro do coletivo citado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(ObjetoNaoFoiSalvoException.class)
	public ResponseEntity<StandartError> PessoaNaoFoiSalvoException(ObjetoNaoFoiSalvoException e, HttpServletRequest request){
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Verifique se todos os campos estão completos");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(FalhaAoBuscarAsException.class)
	public ResponseEntity<StandartError> FalhaAoBuscarAsException(FalhaAoBuscarAsException e, HttpServletRequest request){
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Funcionário não encontrado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(ErroAoDeletarFuncionario.class)
	public ResponseEntity<StandartError> ErroAoDeletarFuncionario(ErroAoDeletarFuncionario e, HttpServletRequest request){
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Não foi possivel deletar o Funcionario citado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(FalharAoRealziarMapper.class)
	public ResponseEntity<StandartError> FalharAoRealziarMapper(FalharAoRealziarMapper e, HttpServletRequest request){
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Não foi possivel construir entidade");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}



	
}
