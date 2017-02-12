package br.ufrpe.novo.tks.dados;

import java.util.List;

import br.ufrpe.novo.tks.negocios.beans.Pessoa;

public interface IRepositorioPessoa {
	void cadastrar(Pessoa p);
	
	void remover(Pessoa p);
	
	void remover(int index);
	
	Pessoa procurar(String matricula);
	
	List<Pessoa> getUsuarios();
	
	void salvarbd();
}