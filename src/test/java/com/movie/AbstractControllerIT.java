package com.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.movie.repository.FilmeRepository;
import com.movie.repository.ScoreRepository;
import com.movie.repository.UsuarioRepository;

public abstract class AbstractControllerIT implements BaseControllerIT {

	@Autowired
	protected TestRestTemplate restTemplate;

	@Autowired
	protected FilmeRepository FilmeRepository;
	
	@Autowired
	protected ScoreRepository scoreRepository;
	
	@Autowired
	protected UsuarioRepository usuarioRepository;


}
