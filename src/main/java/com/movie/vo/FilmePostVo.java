package com.movie.vo;

import com.movie.entity.FilmeEntity;

public class FilmePostVo {

	private Long id;
	
	private String titulo;
	
	private String image;
	
	

	public FilmePostVo() {
		super();
	}

	public FilmePostVo(FilmeEntity entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.image = entity.getImage();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
