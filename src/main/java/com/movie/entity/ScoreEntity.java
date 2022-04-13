package com.movie.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SCORE_TB")
public class ScoreEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name ="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@ManyToOne
	@JoinColumn(name ="ID_FILME")
	private FilmeEntity filme;
	@ManyToOne
	@JoinColumn(name ="ID_USER")
	private UsuarioEntity usuario;
	
	@Column(name = "VALOR",nullable = false)
	private Double valor;
	
	
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public FilmeEntity getFilme() {
		return filme;
	}
	public void setFilme(FilmeEntity filme) {
		this.filme = filme;
	}
	public UsuarioEntity getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
	
	
}
