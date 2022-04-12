package com.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.entity.FilmeEntity;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {
	
	FilmeEntity findByTitulo(String titulo);
}
