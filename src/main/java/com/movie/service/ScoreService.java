package com.movie.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.movie.entity.FilmeEntity;
import com.movie.entity.ScoreEntity;
import com.movie.entity.UsuarioEntity;
import com.movie.exception.NaoEncontradoException;
import com.movie.exception.ScoreJaCadastradoException;
import com.movie.repository.FilmeRepository;
import com.movie.repository.ScoreRepository;
import com.movie.repository.UsuarioRepository;
import com.movie.vo.FilmeVo;
import com.movie.vo.ScoreVo;


@Service
public class ScoreService {

	@Autowired
	private FilmeRepository filmeRepository;
	@Autowired
	private ScoreRepository scoreRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ScoreService.class);
	
	@Transactional
	public FilmeVo salvarScore(ScoreVo scoreVo) throws RuntimeException {
		
		FilmeEntity filmeEntity = null;

		UsuarioEntity usuarioEntity =  usuarioRepository.findByEmail(scoreVo.getEmail());
		if (usuarioEntity == null) {
			 usuarioEntity = new UsuarioEntity(scoreVo.getEmail());
			 logger.info("USUARIO SALVO NA APLICAÇÃO");
			 usuarioEntity = usuarioRepository.saveAndFlush(usuarioEntity);
		}
		
		
		try {
			 filmeEntity = filmeRepository.findById(scoreVo.getFilmeId()).orElseThrow(() -> new NaoEncontradoException("NENHUM FILME ENCONTRADO PELO ID:"+scoreVo.getFilmeId()));
			
			
		}catch (NaoEncontradoException e) {
			logger.error("NENHUM FILME ENCONTRADO!");
			throw new NaoEncontradoException("NENHUM FILME ENCONTRADO PELO ID:"+scoreVo.getFilmeId());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		ScoreEntity scoreEntity = new ScoreEntity();
		
		scoreEntity.setFilme(filmeEntity);
		scoreEntity.setUsuario(usuarioEntity);
		scoreEntity.setValor(scoreVo.getScore());
		
		
		
		try {
			verificaRepeticaoDevoto(scoreEntity);
		}catch(ScoreJaCadastradoException ex) {
			logger.error("JÁ EXISTE SCORE CADASTRADO COM ESSE USUARIO");
			throw new ScoreJaCadastradoException("já existe Score cadastrado com esse Usuario",ex);
		} 
		catch (Exception e) {
			e.getStackTrace();
		}
		
		scoreRepository.save(scoreEntity);
		
		
		double soma = 0.0;
		for (ScoreEntity filme : filmeEntity.getScorePks()) {
			soma += filme.getValor();
		}
		
		double avg = (soma / filmeEntity.getScorePks().size());
		filmeEntity.setScore(avg);
		filmeEntity.setConta(filmeEntity.getScorePks().size());
		
		filmeEntity =  filmeRepository.save(filmeEntity);
		
		return new FilmeVo(filmeEntity);
	}

	private void verificaRepeticaoDevoto(ScoreEntity scoreEntity) {
		List<ScoreEntity>  scores;
		scores = scoreRepository.findByFilmeIdAndUsuarioIdUsuario(scoreEntity.getFilme().getId(), scoreEntity.getUsuario().getIdUsuario());
		
		if (!scores.isEmpty()) {
			throw new ScoreJaCadastradoException("");
		}
	}

}
