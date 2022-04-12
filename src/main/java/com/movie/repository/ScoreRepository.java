package com.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movie.entity.ScoreEntity;

@Repository
public interface ScoreRepository  extends JpaRepository<ScoreEntity, Long>{
	
	@Query("select s from ScoreEntity s where s.filme.id = ?1 and  s.usuario.idUsuario = ?2")
	List<ScoreEntity> findByFilmeIdAndUsuarioIdUsuario(Long id,Long idUsuario);

}
