package com.api.livros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.livros.domain.Autor;
import com.api.livros.repository.AutoresRepository;

@Service
public class AutorService {

	@Autowired
	private AutoresRepository repositoryAutor;
	
	public List<Autor> listarAutor(){
		return repositoryAutor.findAll();
	}
}
