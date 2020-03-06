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

import com.api.livros.domain.Autor;
import com.api.livros.domain.Comentario;
import com.api.livros.domain.Livro;
import com.api.livros.repository.AutoresRepository;
import com.api.livros.services.AutorService;
import com.api.livros.services.LivrosService;


@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosService serviceLivros;
	
	@Autowired
	private AutorService serviceAutor;
	
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
		
		Optional<Livro> livro = serviceLivros.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(livro);	
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		
		serviceLivros.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "{/id}")
	public ResponseEntity<Void> editar(@RequestBody Livro livro , @PathVariable Long id) {
		
		livro.setId(id);
		serviceLivros.salvar(livro);
		return ResponseEntity.notFound().build();
	}

	
	@PostMapping(value = "/{id}/comentarios")
	public ResponseEntity<Void> comentar(@PathVariable("id") Long livroId,@RequestBody Comentario comentario) {
		
		serviceLivros.salvarComentario(livroId, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return  ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/{id}/comentarios")
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable ("id") Long livroId){
		
		List<Comentario> comentarios = serviceLivros.listarComentario(livroId);
		
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
	@DeleteMapping(value = "/{id}/comentarios/{id}")
	public ResponseEntity<Void> deletarComentario(@PathVariable ("id") Long livroId ,@PathVariable("id") Long id) {
		
		serviceLivros.deletarComentario(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/autores")
	public ResponseEntity<List<Autor>> listarAutores() {
		
		return ResponseEntity.status(HttpStatus.OK).body(serviceAutor.listarAutor()) ;
	}
	
}
