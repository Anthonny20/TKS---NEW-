package br.ufrpe.novo.tks.negocios;

import java.util.Scanner;

import br.ufrpe.novo.tks.negocios.beans.EscalaMes;
import br.ufrpe.novo.tks.dados.IRepositorioEscalaMes;
import br.ufrpe.novo.tks.dados.IRepositorioPessoa;
import br.ufrpe.novo.tks.dados.RepositorioPessoa;
import br.ufrpe.novo.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.novo.tks.negocios.beans.Selecionado;
import br.ufrpe.novo.tks.negocios.beans.Pessoa;

public class SelecaoPessoas {
	private IRepositorioPessoa pessoas;
	private IRepositorioEscalaMes escala;
	
	
	public SelecaoPessoas(IRepositorioPessoa repositorio, IRepositorioEscalaMes escalaRep){
		if(repositorio != null & escalaRep != null){
			this.pessoas = repositorio;
			this.escala = escalaRep;
		}else{
			IllegalArgumentException rep = new IllegalArgumentException("O repositório está com erro");
			throw rep;
		}
		
	}
	
	public void cadastrar(EscalaMes novo, int dias, int qtdDia) throws UsuarioNaoEncontradoException{
		this.escala.cadastrarEscala(this.selecionarPessoas(dias, qtdDia));
	}
	
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
			 selecionados[i] = new Selecionado(Servidor.getInstance().procurar(s), q);
			 
		 }
		 
		 int pqp = 0;
		 do{
			 for(int i = 0; i < selecaoDias.length; i++){
				 for(int j = 0; j < selecaoDias[i].length; j++){
					 if(selecionados[pqp].getMatricula() == selecaoDias[i][j]){
						 selecionados[pqp].setDiasSorteados(i);
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

}
