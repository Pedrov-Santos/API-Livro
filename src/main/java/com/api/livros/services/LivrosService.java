package com.api.livros.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.api.livros.domain.Comentario;
import com.api.livros.domain.Livro;
import com.api.livros.repository.ComentarioRepository;
import com.api.livros.repository.LivroRepositiry;
import com.api.livros.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

	
	@Autowired
	private LivroRepositiry RepositoryLivro;
	
	@Autowired
	private ComentarioRepository RepositoryComentario;
	 
	
	public List<Livro> listar(){
		return RepositoryLivro.findAll();
	}
	
	public Optional<Livro> buscar(Long id) {
		Optional<Livro> livro = RepositoryLivro.findById(id);
		if(livro.isEmpty()) {
			throw new LivroNaoEncontradoException("Livro não encontrado.");
		}
		
		return livro;
	}
	
	public Livro salvar(Livro livro) {
		livro.setId(null);
		return  RepositoryLivro.save(livro);
	}
	
	public void deletar(Long id) {
		try {
			RepositoryLivro.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("Livro não encontrado.");
		}
		
	}
	
	public void editar(Livro livro) {
		verificarExistencia(livro);
		RepositoryLivro.save(livro);
		 
		
	}
	
	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}
	
	public Comentario salvarComentario(Long livroId , Comentario comentario) { 
		Livro livro = buscar(livroId).get();
		
		comentario.setLivro(livro);
		comentario.setPublicacaoComentario(new Date());
		
		return RepositoryComentario.save(comentario); 
	}
}
