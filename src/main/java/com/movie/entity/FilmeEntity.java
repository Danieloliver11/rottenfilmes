package com.movie.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.movie.vo.FilmePostVo;

@Entity
@Table(name = "FILME_TB")
public class FilmeEntity  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name ="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(name ="TITULO")
	private String titulo;
	@Column(name ="CONTA")
	private Integer conta;
	@Column(name ="IMAGE")
	private String image;
																//fetch = FetchType.EAGER
	@OneToMany(mappedBy = "filme",cascade = CascadeType.ALL)
	private Set<ScoreEntity> scorePks = new HashSet<>();
	
	@Column(name ="SCORE")
	private Double score;
	
	

//	public List<ScoreEntity> getScorePks() {
//		return scorePks;
//	}
//
//	public void setScorePks(List<ScoreEntity> scorePks) {
//		this.scorePks = scorePks;
//	}
	

	public FilmeEntity(FilmePostVo vo) {
	this.titulo = vo.getTitulo();
	this.image = vo.getImage();
}
	
	public FilmeEntity() {
	super();
}

	public Long getId() {
		return Id;
	}

	public Set<ScoreEntity> getScorePks() {
		return scorePks;
	}

	public void setScorePks(Set<ScoreEntity> scorePks) {
		this.scorePks = scorePks;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmeEntity other = (FilmeEntity) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
	
	
	

}
