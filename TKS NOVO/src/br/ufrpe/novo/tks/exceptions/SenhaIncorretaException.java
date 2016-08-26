package br.ufrpe.novo.tks.exceptions;

public class SenhaIncorretaException extends Exception{
	public SenhaIncorretaException(){
		super("Senha incorreta");
	}
}
