package br.ufrpe.novo.tks.exceptions;

public class UsuarioNaoEncontradoException extends Exception{
	private String matricula;
	public UsuarioNaoEncontradoException(String matricula){
		super("MATRICULA: " + matricula + " N�O ENCONTRADA!!!");
		this.matricula = matricula;
	}
	public String getMatricula(){
		return matricula;
	}
}
