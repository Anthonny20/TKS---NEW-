package br.ufrpe.novo.tks.exceptions;

public class UsuarioJaCadastradoException extends Exception{
	private String matricula;
	public UsuarioJaCadastradoException(String matricula){
		super("MATR�CULA: " + matricula + " J� CADASTRADA.");
		this.matricula = matricula;
	}
	public String getMatricula(){
		return matricula;
	}
}
