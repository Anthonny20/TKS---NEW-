package br.ufrpe.novo.tks.negocios.beans;

import java.io.Serializable;
import java.util.Arrays;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Selecionado implements Serializable{
	
	private static final long serialVersionUID = -2195019469268266619L;
	private String nome;
	private String matricula;
	private String cargo;
	private int [] diasSorteados;
	private boolean motorista;
	
	public Selecionado(Pessoa p, int qtd){
		this.nome = p.getNome();
		this.matricula = p.getMatricula();
		this.cargo = ((Funcionario) p).getCargo();
		this.motorista = ((Funcionario) p).isMotorista();
		this.diasSorteados = new int [qtd];
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getMatricula(){
		return this.matricula;
	}
	
	public String getCargo(){
		return this.cargo;
	}
	
	public boolean isMotorista(){
		return this.motorista;
	}
	
	public int [] getDiasSorteados(){
		return this.diasSorteados;
	}
	
	public int getDiaSorteado(int i){
		return this.diasSorteados[i];
	}
	
	public void showDiasSorteados(){
		for(int i = 0; i < this.diasSorteados.length; i++){
			System.out.print(this.getDiaSorteado(i));
		}
	}
	
	public String getDiaStringFormat(int i){
		String resultado = "" + this.getDiaSorteado(i);
		return resultado;
	}
	
	public void setDiasSorteados(int dia){
		for(int i = 0; i < this.diasSorteados.length; i++){
			if(this.diasSorteados[i] == 0){
				this.diasSorteados[i] = dia;
				break;
			}
		}
	}
	
	public StringProperty getDiasSorteadosProperty(){
		String r = Arrays.toString(this.diasSorteados);		
		StringProperty resultado = new SimpleStringProperty(r);
		return resultado;
	}
	
	public StringProperty getNomeProperty(){
		StringProperty resultado = new SimpleStringProperty(this.nome);
		return resultado;
	}
	
	public StringProperty getMatriculaProperty(){
		StringProperty resultado = new SimpleStringProperty(this.matricula);
		return resultado;
	}
	
	public StringProperty getCargoProperty(){
		StringProperty resultado = new SimpleStringProperty(this.cargo);
		return resultado;
	}
	
	public StringProperty getFuncaoProperty(){
		if(this.motorista){
			String x = "Motorista";
			StringProperty resultado = new SimpleStringProperty(x);
			return resultado;
		}
		else{
			String x = "Comandante";
			StringProperty resultado = new SimpleStringProperty(x);
			return resultado;
		}
	}
	
	public StringProperty getTotalDiasProperty(){
		StringProperty resultado = new SimpleStringProperty("" + this.diasSorteados.length);
		return resultado;
	}
	
	public String toString(){
		return ("\n" + this.cargo + " " +this.matricula + " " + this.nome + " " + Arrays.toString(this.diasSorteados) + " " + this.motorista);
	}
	
	

}
