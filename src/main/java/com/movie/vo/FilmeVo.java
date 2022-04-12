package com.movie.vo;

import com.movie.entity.FilmeEntity;

public class FilmeVo {

	private Long id;
	private String titulo;
	private Double score;
	private Integer conta;
	private String image;

	public FilmeVo() {
	}

	public FilmeVo(FilmeEntity filmeEntiy) {
		this.id = filmeEntiy.getId();
		this.titulo = filmeEntiy.getTitulo();
		this.conta = filmeEntiy.getConta();
		this.image = filmeEntiy.getImage();
		this.score = filmeEntiy.getScore();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
