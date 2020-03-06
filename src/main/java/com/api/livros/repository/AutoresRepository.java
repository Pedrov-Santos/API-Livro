package com.api.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.livros.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

}
