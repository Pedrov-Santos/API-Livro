package com.api.livros.domain;

import java.util.Date;

public class Comentario {

	
	private Long id;
	
	private String texto;
	
	private String usuario;
	
	private Date publicacaoComentario;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getPublicacaoComentario() {
		return publicacaoComentario;
	}

	public void setPublicacaoComentario(Date publicacaoComentario) {
		this.publicacaoComentario = publicacaoComentario;
	}
	
	
	
	
}
