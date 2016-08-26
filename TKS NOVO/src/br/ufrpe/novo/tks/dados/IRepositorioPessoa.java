package br.ufrpe.novo.tks.dados;

import java.util.ArrayList;

import br.ufrpe.novo.tks.negocios.beans.Pessoa;

public interface IRepositorioPessoa {
	void cadastrar(Pessoa p);
	
	void remover(Pessoa p);
	
	Pessoa procurar(String matricula);
	
	ArrayList<Pessoa> getUsuarios();
	
	void salvarbd();
}