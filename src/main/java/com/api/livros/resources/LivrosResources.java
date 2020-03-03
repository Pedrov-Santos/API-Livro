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
import com.api.livros.repository.LivroRepositiry;


@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivroRepositiry RepositoryLivro;
	
	@GetMapping
	public List<Livro> listar() {
		
		return RepositoryLivro.findAll() ;
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
		
		livro = RepositoryLivro.save(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		
		Optional<Livro> livro = RepositoryLivro.findById(id);
		
			if(livro.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
		return ResponseEntity.status(HttpStatus.OK).body(livro);	
	}

	@DeleteMapping(value = "/{id}")
	public void deletar(@PathVariable("id") Long id) {
		RepositoryLivro.deleteById(id);
	}
	
	@PutMapping(value = "{/id}")
	public void editar(@RequestBody Livro livro , @PathVariable Long id) {
		
		livro.setId(id);
		RepositoryLivro.save(livro);
	}

	
	
	
}
