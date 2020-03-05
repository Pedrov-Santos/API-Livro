package com.api.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.livros.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
