package com.api.livros.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.api.livros.domain.DetalhesErros;
import com.api.livros.services.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	DetalhesErros erro = new DetalhesErros();
	
	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalhesErros> handlerLivroNaoEncontradoException(LivroNaoEncontradoException e, HttpServletRequest request){
		
		erro.setStatus(404l);
		erro.setTitulo("Livro não encontrado.");
		erro.setMensagemErro("https://stackoverflow.com/");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<DetalhesErros> handlerNumberFormatException(NumberFormatException e, HttpServletRequest request){
		
		erro.setStatus(401l);
		erro.setTitulo("URI invalida.");
		erro.setMensagemErro("https://stackoverflow.com/");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<DetalhesErros> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request){
		
		erro.setStatus(401l);
		erro.setTitulo("URI invalida.");
		erro.setMensagemErro("https://stackoverflow.com/");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
