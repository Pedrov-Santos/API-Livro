package com.api.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.livros.domain.Livro;

public interface LivroRepositiry extends JpaRepository<Livro, Long> {

}
