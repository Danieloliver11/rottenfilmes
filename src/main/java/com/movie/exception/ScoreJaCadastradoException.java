package com.movie.exception;

public class ScoreJaCadastradoException extends RuntimeException {

 static final long serialVersionUID = 1L;

 
 	public ScoreJaCadastradoException(String msg, Throwable tr) {
 		super(msg, tr);
 	}
 	
 	public ScoreJaCadastradoException(String msg) {
 		super(msg);
 		
 	}
 	
}
