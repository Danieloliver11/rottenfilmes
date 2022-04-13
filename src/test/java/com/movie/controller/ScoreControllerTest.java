package com.movie.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.movie.AbstractControllerIT;
import com.movie.vo.FilmeVo;
import com.movie.vo.ScoreVo;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
class ScoreControllerTest extends AbstractControllerIT {

	private static final String ENDERECO = "/score";
	private static StringBuilder path = new StringBuilder(URL);

	@Test
	@Order(1)
	void esperaSalvarUmFilme() {

		ScoreVo scoreVo = gerarScore();

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ScoreVo> entity = new HttpEntity<ScoreVo>(scoreVo, headers);
		ParameterizedTypeReference<FilmeVo> responseType = new ParameterizedTypeReference<>() {
		};

		ResponseEntity<FilmeVo> response = restTemplate.exchange(path.toString(), HttpMethod.PUT, entity,
				responseType);

		assertEquals(HttpStatus.OK, response.getStatusCode());

	}
	
	@Test
	@Order(2)
	void esperaNaoRegistrarUmScoreComOMesmoUsuarioParaOFilme() {

		ScoreVo scoreVo = gerarScore();

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ScoreVo> entity = new HttpEntity<ScoreVo>(scoreVo, headers);
		ParameterizedTypeReference<FilmeVo> responseType = new ParameterizedTypeReference<>() {
		};

		ResponseEntity<FilmeVo> response = restTemplate.exchange(path.toString(), HttpMethod.PUT, entity,
				responseType);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

	private ScoreVo gerarScore() {
		ScoreVo vo = new ScoreVo();

		vo.setEmail("daniel@hotmail.com");
		vo.setFilmeId(1l);
		vo.setScore(4.5);

		return vo;
	}

	@BeforeEach
	void inicializar() {
		path.append(ENDERECO);
	}

	@AfterEach
	void finalizar() {
		path = new StringBuilder(URL);
	}

}
