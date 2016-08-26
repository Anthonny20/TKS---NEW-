package br.ufrpe.novo.tks.exceptions;

public class LoginIncorretoException extends Exception{
	public LoginIncorretoException(){
		super("Número da matrícula incorreto");
	}
}
