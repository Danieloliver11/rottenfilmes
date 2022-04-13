package com.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.exception.NaoEncontradoException;
import com.movie.exception.ScoreJaCadastradoException;
import com.movie.service.ScoreService;
import com.movie.vo.FilmeVo;
import com.movie.vo.ScoreVo;

@RestController
@RequestMapping("/score")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ScoreController {

	@Autowired
	ScoreService scoreService;

	@PutMapping
	public ResponseEntity<FilmeVo> salvarAvaliacao(@RequestBody ScoreVo scoreVo) {

		ResponseEntity<FilmeVo> resposta ;
		try {
			 resposta = ResponseEntity.ok(scoreService.salvarScore(scoreVo));

		} catch (ScoreJaCadastradoException ex) {
			 resposta = ResponseEntity.status(HttpStatus.BAD_REQUEST).header("menssagem:", ex.getMessage()).build();
		}
		catch (NaoEncontradoException ex) {
			 resposta = ResponseEntity.status(HttpStatus.NOT_FOUND).header("menssagem:", ex.getMessage()).build();
		}catch (Exception e) {
			e.printStackTrace();
			 resposta = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("menssagem:", e.getMessage()).build();
		}
		return resposta;

	}
}
