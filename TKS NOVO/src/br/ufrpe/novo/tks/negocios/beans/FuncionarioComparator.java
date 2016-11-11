package br.ufrpe.novo.tks.negocios.beans;

import java.util.Comparator;

public class FuncionarioComparator implements Comparator<Funcionario>{

	
	public int compare(Funcionario f1, Funcionario f2){
		return ((Pessoa)f1).getNome().compareTo(((Pessoa)f2).getNome());
	}
}
