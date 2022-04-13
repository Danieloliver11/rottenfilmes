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
import com.movie.impl.RestPageImpl;
import com.movie.vo.FilmePostVo;
import com.movie.vo.FilmeVo;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
class FilmeControllerTest extends AbstractControllerIT {

	private static final String ENDERECO = "/filme";
	private static StringBuilder path = new StringBuilder(URL);

	@Test
	@Order(1)
	void esperaSalvarUmFilme() {

		FilmePostVo filmeVo = gerarFilme();

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<FilmePostVo> entity = new HttpEntity<FilmePostVo>(filmeVo, headers);
		ParameterizedTypeReference<FilmePostVo> responseType = new ParameterizedTypeReference<>() {
		};

		ResponseEntity<FilmePostVo> response = restTemplate.exchange(path.toString(), HttpMethod.POST, entity,
				responseType);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

	@Test
	@Order(2)
	void esperaAcharFilmes() {

		ParameterizedTypeReference<RestPageImpl<FilmeVo>> responseType = new ParameterizedTypeReference<RestPageImpl<FilmeVo>>() {
		};

		ResponseEntity<RestPageImpl<FilmeVo>> response = restTemplate.exchange(path.toString(), HttpMethod.GET, null,
				responseType);

		assertEquals(HttpStatus.OK, response.getStatusCode());

	}
	@Test
	@Order(3)
	void esperaUmFilmePorId() {
		path.append("/1");
		
		ParameterizedTypeReference<FilmeVo> responseType = new ParameterizedTypeReference<>() {
		};
		ResponseEntity<FilmeVo> response = restTemplate.exchange(path.toString(), HttpMethod.GET, null,
				responseType);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(response.getBody().getId(), 1);
	}
	
	@Test
	@Order(4)
	void esperaErroDeUmFilmePorId() {
		path.append("/9999");
		
		ParameterizedTypeReference<FilmeVo> responseType = new ParameterizedTypeReference<>() {
		};
		ResponseEntity<FilmeVo> response = restTemplate.exchange(path.toString(), HttpMethod.GET, null,
				responseType);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	@Order(5)
	void esperaNaoSalvarUmfilmeComUmnomeJaCadastrado() {
		
		FilmePostVo vo =  gerarFilme();
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<FilmePostVo> filmeVo = new HttpEntity<FilmePostVo>(vo, headers);
		
		ParameterizedTypeReference<FilmePostVo> responseType = new ParameterizedTypeReference<>() {
		};
		
		ResponseEntity<FilmePostVo> response = restTemplate.exchange(path.toString(), HttpMethod.POST, filmeVo,
				responseType);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	
		

	private FilmePostVo gerarFilme() {
		FilmePostVo vo = new FilmePostVo();
		vo.setImage("https://br.web.img3.acsta.net/pictures/19/11/29/17/57/5161763.jpg");
		vo.setTitulo("Batman");
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
