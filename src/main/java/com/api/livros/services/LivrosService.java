package com.api.livros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
  import org.springframework.stereotype.Service;

import com.api.livros.domain.Livro;
import com.api.livros.repository.LivroRepositiry;
import com.api.livros.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

	
	@Autowired
	private LivroRepositiry RepositoryLivro;
	 
	
	public List<Livro> listar(){
		return RepositoryLivro.findAll();
	}
	
	public Optional<Livro> buscar(Long id) {
		Optional<Livro> livro = RepositoryLivro.findById(id);
		if(livro == null) {
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
}
