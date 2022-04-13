package com.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.exception.FilmeJaCadastradoException;
import com.movie.exception.NaoEncontradoException;
import com.movie.service.FilmeService;
import com.movie.vo.FilmePostVo;
import com.movie.vo.FilmeVo;

@RestController
@RequestMapping("/filme")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilmeController {

	@Autowired
	FilmeService filmeService;

	@PostMapping
	public ResponseEntity<FilmePostVo> cadastraFilme(@RequestBody FilmePostVo filmeVo) {
		ResponseEntity<FilmePostVo> resposta = null;
		try {
			resposta = ResponseEntity.status(HttpStatus.CREATED).body(filmeService.cadastraFilme(filmeVo));

		} catch (FilmeJaCadastradoException ex) {
			resposta = ResponseEntity.status(HttpStatus.BAD_REQUEST).header("menssagem:", ex.getMessage()).build();

		} catch (Exception e) {
			resposta = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("menssagem:", e.getMessage())
					.build();
		}
		return resposta;
	}

	@GetMapping
	public ResponseEntity<Page<FilmeVo>> buscaTodosFilmes(@PageableDefault(size = 20, sort = "titulo") Pageable page) {
		ResponseEntity<Page<FilmeVo>> resposta = null;

		try {
			resposta = ResponseEntity.ok(filmeService.buscarTodosFilmes(page));

		} catch (NaoEncontradoException ex) {
			resposta = ResponseEntity.status(HttpStatus.NOT_FOUND).header("menssagem:", ex.getMessage()).build();
		} catch (Exception e) {
			resposta = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("menssagem:", e.getMessage())
					.build();
		}
		return resposta;

	}

	@GetMapping("/{id}")
	public ResponseEntity<FilmeVo> buscaTodosFilmes(@PathVariable Long id) {
		ResponseEntity<FilmeVo> resposta;
		try {
			resposta = ResponseEntity.ok(filmeService.buscarTodosFilmePorId(id));
		} catch (NaoEncontradoException ex) {
			resposta = ResponseEntity.status(HttpStatus.NOT_FOUND).header("menssagem:", ex.getMessage()).build();
		} catch (Exception e) {
			resposta = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("menssagem:", e.getMessage())
					.build();
		}
		return resposta;

	}
}
