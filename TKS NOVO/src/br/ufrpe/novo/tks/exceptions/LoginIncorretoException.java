package br.ufrpe.novo.tks.exceptions;

public class LoginIncorretoException extends Exception{
	public LoginIncorretoException(){
		super("N�mero da matr�cula incorreto");
	}
}
