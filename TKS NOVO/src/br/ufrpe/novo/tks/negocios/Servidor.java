package br.ufrpe.novo.tks.negocios;

import java.util.List;

import br.ufrpe.novo.tks.dados.RepositorioEscalaMes;
import br.ufrpe.novo.tks.dados.RepositorioPessoa;
import br.ufrpe.novo.tks.exceptions.EscalaNaoEncontradaException;
import br.ufrpe.novo.tks.exceptions.UsuarioJaCadastradoException;
import br.ufrpe.novo.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.novo.tks.negocios.beans.Administrador;
import br.ufrpe.novo.tks.negocios.beans.EscalaMes;
import br.ufrpe.novo.tks.negocios.beans.Funcionario;
import br.ufrpe.novo.tks.negocios.beans.Pessoa;

public class Servidor {
	private static Servidor instance;
	private CadastroPessoa pessoa;
	
	private Servidor(){
		this.pessoa = new CadastroPessoa(RepositorioEscalaMes.getInstance(), RepositorioPessoa.getInstance());
	}
	
	public static Servidor getInstance() {
        if (instance == null) {
            instance = new Servidor();
        }
        return instance;
    }
	
	public void cadastrar(Pessoa p) throws UsuarioJaCadastradoException{
		if(p instanceof Administrador){
			pessoa.cadastrarAdministrador((Administrador) p);
		}else if(p instanceof Funcionario){
			pessoa.cadastrarFuncionario((Funcionario) p);
		}
		Servidor.getInstance().salvardb();
		
	}
	
	public void cadastrarFuncionario(String nome, String cargo, char sexo, String matricula, boolean motorista, String senha) throws UsuarioJaCadastradoException{
		pessoa.cadastrarFuncionario(nome, cargo, sexo, matricula, motorista, senha);
		Servidor.getInstance().salvardb();
	}
	
	public void cadastrarAdministrador(String nome, char sexo, String matricula, String senha) throws UsuarioJaCadastradoException{
		pessoa.cadastrarAdministrador(nome, sexo, matricula, senha);
		Servidor.getInstance().salvardb();
	}
	
	public Pessoa procurar(String matricula) throws UsuarioNaoEncontradoException{
		return pessoa.procurar(matricula);
	}
	
	public void remover(String matricula) throws UsuarioNaoEncontradoException{
		pessoa.remover(matricula);
		Servidor.getInstance().salvardb();
	}
	
	public void remover(int index){
		pessoa.remover(index);
		Servidor.getInstance().salvardb();
	}
	
	public List<Pessoa> getPessoas() {
	    return this.pessoa.getPessoas();
	}
	
	public List<Pessoa> getFuncionariosList(){
		return this.pessoa.getFuncionariosList();
	}
	
	public void salvardb(){
		RepositorioPessoa.getInstance().salvarbd();
	}
	
	
	//-------------------------------------------------------------------
	
	public void cadastrarEscala(EscalaMes nova){
		this.pessoa.cadastrarEscala(nova);
	}
	
	public EscalaMes procurarEscala(String mesAno) throws EscalaNaoEncontradaException{
		return this.pessoa.procurarEscala(mesAno);
	}
	/*
	public EscalaMes selecionarPessoas(int dias, int qtdDia) throws UsuarioNaoEncontradoException{
		return this.pessoa.selecionarPessoas(dias, qtdDia);
	}
	*/

	public void removerEscala(EscalaMes procurarEscala) {
		this.pessoa.removerEscala(procurarEscala);
		
	}

}