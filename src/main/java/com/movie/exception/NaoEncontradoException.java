package com.movie.exception;

public class NaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NaoEncontradoException(String msg,Throwable tr){
		super(msg,tr);
	}
	public NaoEncontradoException(String msg){
		super(msg);
	}
	

	
	
}
