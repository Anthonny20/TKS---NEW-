package br.ufrpe.novo.tks.negocios;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.novo.tks.dados.IRepositorioEscalaMes;
import br.ufrpe.novo.tks.dados.IRepositorioPessoa;
import br.ufrpe.novo.tks.exceptions.EscalaNaoEncontradaException;
import br.ufrpe.novo.tks.exceptions.LoginIncorretoException;
import br.ufrpe.novo.tks.exceptions.SenhaIncorretaException;
import br.ufrpe.novo.tks.exceptions.UsuarioJaCadastradoException;
import br.ufrpe.novo.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.novo.tks.negocios.beans.Administrador;
import br.ufrpe.novo.tks.negocios.beans.EscalaMes;
import br.ufrpe.novo.tks.negocios.beans.Funcionario;
import br.ufrpe.novo.tks.negocios.beans.Pessoa;

public class CadastroPessoa {
	private IRepositorioPessoa pessoas;
	private IRepositorioEscalaMes escalas;
	
	public CadastroPessoa(IRepositorioEscalaMes repEsc, IRepositorioPessoa repositorio){
		if(repositorio != null & repEsc != null){
			this.pessoas = repositorio;
			this.escalas = repEsc;
		}else{
			IllegalArgumentException rep = new IllegalArgumentException("O repositório está com erro");
			throw rep;
		}
		
	}
	
	public void cadastrarFuncionario(String nome, String cargo, char sexo, String matricula, boolean motorista, String senha) throws UsuarioJaCadastradoException{
		if(this.pessoas.procurar(matricula) == null){
			Pessoa funcionario = new Funcionario(nome, cargo, sexo, matricula, motorista, senha);
			this.pessoas.cadastrar(funcionario);
		}else{
			UsuarioJaCadastradoException usr = new UsuarioJaCadastradoException(matricula);
			throw usr;
		}
	}
	
	public void cadastrarAdministrador(String nome, char sexo, String matricula, String senha) throws UsuarioJaCadastradoException{
		if(this.pessoas.procurar(matricula) == null){
			Pessoa funcionario = new Administrador(nome, sexo, matricula, senha);
			this.pessoas.cadastrar(funcionario);
			this.pessoas.salvarbd();
		}else{
			UsuarioJaCadastradoException usr = new UsuarioJaCadastradoException(matricula);
			throw usr;
		}
	}
	
	public void cadastrarFuncionario(Funcionario f) throws UsuarioJaCadastradoException{
		if(f != null){
			if(this.pessoas.procurar(f.getMatricula()) == null){
				this.pessoas.cadastrar(f);
				this.pessoas.salvarbd();
			}else{
				UsuarioJaCadastradoException usr = new UsuarioJaCadastradoException(f.getMatricula());
				throw usr;
			}
		}else{
			IllegalArgumentException rep = new IllegalArgumentException("O usuário está vazio");
			throw rep;
		}
	}
	
	public void cadastrarAdministrador(Administrador a) throws UsuarioJaCadastradoException{
		if(a != null){
			if(this.pessoas.procurar(a.getMatricula()) == null){
				this.pessoas.cadastrar(a);
				this.pessoas.salvarbd();
			}else{
				UsuarioJaCadastradoException usr = new UsuarioJaCadastradoException(a.getMatricula());
				throw usr;
			}
		}else{
			IllegalArgumentException rep = new IllegalArgumentException("O usuário está vazio");
			throw rep;
		}
	}
	
	public void remover(String matricula) throws UsuarioNaoEncontradoException{
		Pessoa pessoa = this.pessoas.procurar(matricula);
		if(pessoa != null){
			this.pessoas.remover(pessoa);
			this.pessoas.salvarbd();
		}else{
			UsuarioNaoEncontradoException usr = new UsuarioNaoEncontradoException(matricula);
			throw usr;
		}
	}
	
	public void remover(int index){
		this.pessoas.remover(index);
	}
	
	public Pessoa procurar(String matricula) throws UsuarioNaoEncontradoException{
		Pessoa procurado = null;
		procurado = this.pessoas.procurar(matricula);
		if(procurado == null){
			UsuarioNaoEncontradoException usr = new UsuarioNaoEncontradoException(matricula);
			throw usr;
		}
		return procurado;
	}
	
	public boolean login(String matricula, String senha) throws LoginIncorretoException, SenhaIncorretaException{
		boolean efetuado = false;
		if(matricula != null && senha != null){
			Pessoa auxiliar = this.pessoas.procurar(matricula);
			if(auxiliar != null){
				if(auxiliar.getSenha().equals(senha)){
					efetuado = true;
				}else{
					SenhaIncorretaException x = new SenhaIncorretaException();
					throw x;
				}
			}else{
				LoginIncorretoException x = new LoginIncorretoException();
				throw x;
			}
		}
		return efetuado;
	}
	
	public List<Pessoa> getPessoas() {
	    return this.pessoas.getUsuarios();
	}
	
	public List<Pessoa> getFuncionariosList() {
		List<Pessoa> func = new ArrayList<>();
		List<Pessoa> lista = this.getPessoas();
		for(Pessoa p : lista){
			if(p instanceof Funcionario){
				func.add(p);
			}
		}
		return func;
	}
	
	
	//--------------------------------------------------------------------------------------
	
	public void cadastrarEscala(EscalaMes nova){
		this.escalas.cadastrarEscala(nova);
	}
	
	public EscalaMes procurarEscala(String mesAno) throws EscalaNaoEncontradaException{
		EscalaMes resultado = new EscalaMes();
		resultado = this.escalas.procurarEscala(mesAno);
		if(resultado == null){
			EscalaNaoEncontradaException ene = new EscalaNaoEncontradaException();
			throw ene;
		}
		return resultado;
	}
	
	public void removerEscala(EscalaMes escala){
		this.escalas.removerEscala(escala);
	}

	/*
		 public EscalaMes selecionarPessoas(int dias, int qtdDia) throws UsuarioNaoEncontradoException{
		 String [] [] selecaoDias = new String [dias] [qtdDia];
		 Scanner sc = new Scanner(System.in);
		 int contador = 0;
		 
		 //CARREGA UM ARRAY COM MATRICULAS POR DIA
		 for(int i = 0; i < selecaoDias.length; i++){
			 for(int j = 0; j < selecaoDias[i].length; j++){
				 System.out.println("Insira a matricula do funcionario:");
				 selecaoDias[i][j] = sc.nextLine();
			 }
		 }
		 sc.close();
		 String [] matricula = new String [dias * qtdDia];
		 int x = 0;
		 //sc.close();
		 //CARREGA EM VAR MATRICULA TODAS AS MATRICULAS REPETINDO-AS.
		 for(int i = 0; i < selecaoDias.length; i++){
			 for(int j = 0; j < selecaoDias[i].length; j++){
				 matricula[x] = selecaoDias[i][j];
				 x++;
				 if(x == matricula.length){
					 break;
				 }
				 }
			 }
		
		 String [] matriculaTemp = matricula;
		 int count = 0;
		 
		 for(int i = 0; i < matricula.length; i++){
			 for(int j = 0; j < matriculaTemp.length - count; j++){
				 if(i < j & matriculaTemp[j] != null){
					 if(matricula[i].equals(matriculaTemp[j])){
						 count++;
						 matriculaTemp[j] = matriculaTemp[matriculaTemp.length - count];
						 matriculaTemp[matriculaTemp.length-count] = null;
						 j -= 1;
					 }
				 }
			 }
		 }
		 
		 for(int i = 0; i < matriculaTemp.length; i++){
			 if(matriculaTemp[i] != null){
				 contador++;
			 }
		 }
		 
//OK AKI.
		 String [] matriculaTemp2 = new String[contador];
		 int x1 = 0;
		 
		 for(int i = 0; i < matricula.length; i++){
			 if(matriculaTemp[i] != null){
				 matriculaTemp2[x1] = matriculaTemp[i];
				 x1+=1;
			 }
		 }
		 
		 Selecionado [] selecionados = new Selecionado[contador];
		 //CRIA UM ARRAY DE SELECIONADOS DO TAMANHO EXATO DA QUANTIA DE FUNCIONARIOS DIFERENTES ESCOLHIDOS.
		 for(int i = 0; i < selecionados.length; i++){

			 String s = matriculaTemp2[i];
			 //OBSERVA QUANTAS VEZES A MATRICULA REPETIU E RETORNA INT.
			 int q = this.contarServicos(selecaoDias, s);
			 
			 //CRIA UM SELECIONADO NA POSIÇAO DISPONIVEL COM UM ARRAY DE SERVIÇOS DE TAMANHO q.
			 selecionados[i] = new Selecionado(this.pessoas.procurar(s), q);
			 
		 }
		 
		 int pqp = 0;
		 do{
			 
			 for(int i = 0; i < selecaoDias.length; i++){
				 for(int j = 0; j < selecaoDias[i].length; j++){
					 if(selecionados[pqp].getMatricula().equals(selecaoDias[i][j])){
						 selecionados[pqp].setDiasSorteados(i+1);
						 
					 }
				 }
			 }
			 pqp++;
			 
		 }while(pqp < selecionados.length);
		 
		 EscalaMes nova = new EscalaMes();
		 nova.setVoluntarios(selecionados);
		 
		 return nova;
			 
	}
	
	private int contarServicos(String [] [] array, String matricula){
		int resultado = 0;
		for(int i = 0; i < array.length; i ++){
			for(int j = 0; j < array[i].length; j++){
				if(matricula.equals(array[i][j])){
					resultado += 1;
				}
			}
			
		}
		return resultado;
	}
	*/
	
}