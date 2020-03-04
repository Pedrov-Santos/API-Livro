package com.api.livros.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.livros.domain.Livro;
import com.api.livros.services.LivrosService;
import com.api.livros.services.exceptions.LivroNaoEncontradoException;


@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosService serviceLivros;
	
	@GetMapping
	public ResponseEntity<List<Livro>> listar() {
		
		return ResponseEntity.status(HttpStatus.OK).body(serviceLivros.listar()) ;
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
		
		livro = serviceLivros.salvar(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		
		Optional<Livro> livro = null;
		
		try {
			 livro = serviceLivros.buscar(id);
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(livro);	
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		
		try {
			serviceLivros.deletar(id);
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "{/id}")
	public ResponseEntity<Void> editar(@RequestBody Livro livro , @PathVariable Long id) {
		
		livro.setId(id);
		try {
			serviceLivros.salvar(livro);
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.noContent().build(); 
		}
		
		
		return ResponseEntity.notFound().build();
	}

	
	
	
}
