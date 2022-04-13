package com.movie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.movie.entity.FilmeEntity;
import com.movie.exception.FilmeJaCadastradoException;
import com.movie.exception.NaoEncontradoException;
import com.movie.repository.FilmeRepository;
import com.movie.vo.FilmePostVo;
import com.movie.vo.FilmeVo;

@Service
public class FilmeService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ScoreService.class);

	@Autowired
	FilmeRepository filmeRespFilmeRepository;

	public Page<FilmeVo> buscarTodosFilmes(Pageable pageable) {

		Page<FilmeEntity> filmesEntities = filmeRespFilmeRepository.findAll(pageable);

		Page<FilmeVo> FilmesVos = filmesEntities.map(entity -> new FilmeVo(entity));

		return FilmesVos;
	}

	public FilmeVo buscarTodosFilmePorId(Long id) {

		FilmeEntity filmesEntitie;
		FilmeVo FilmesVos = null;
		try {
			filmesEntitie = filmeRespFilmeRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Nenhum Filmer encontrado pelo ID:"+id));

			FilmesVos = new FilmeVo(filmesEntitie);
		}catch (NaoEncontradoException e) {
			logger.error("NENHUM FILMER ENCONTRADO COM ID:"+id);
			throw new NaoEncontradoException("Nenhum Filmer encontrado pelo ID:"+ id,e);
		}
		catch (Exception e) {

			e.printStackTrace();
		}

		return FilmesVos;
	}

	public FilmePostVo cadastraFilme(FilmePostVo filmeVo) {

		FilmeEntity filmesEntitie = filmeRespFilmeRepository.findByTitulo(filmeVo.getTitulo());

		if (filmesEntitie != null) {
			logger.error("FILME JÁ CADASTRADO!");
			throw new FilmeJaCadastradoException("Filme já cadastrado!");
		}

		filmesEntitie = filmeRespFilmeRepository.save(new FilmeEntity(filmeVo));

		return new FilmePostVo(filmesEntitie);
	}

}
