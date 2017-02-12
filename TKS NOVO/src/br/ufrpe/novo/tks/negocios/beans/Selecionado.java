package br.ufrpe.novo.tks.negocios.beans;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Selecionado implements Serializable, Comparable<Selecionado>{
	
	private static final long serialVersionUID = -2195019469268266619L;
	
	private Funcionario selecionado;
	private ArrayList<String> diasSorteados;
	
	public Selecionado(Funcionario f){
		this.selecionado = f;
		this.diasSorteados = new ArrayList<String>();
	}
	
	public Funcionario getFunc(){
		return this.selecionado;
	}
	
	public Funcionario getSelecionado(){
		return this.selecionado;
	}
	
	public String getNome(){
		return selecionado.getNome();
	}
	
	public String getMatricula(){
		return selecionado.getMatricula();
	}
	
	public String getCargo(){
		return selecionado.getCargo();
	}
	
	public boolean isMotorista(){
		return selecionado.isMotorista();
	}
	
	public ArrayList<String> getDiasSorteados(){
		return this.diasSorteados;
	}
	
	public String getDiaSorteado(int i){
		return this.diasSorteados.get(i);
	}
	
	public void showDiasSorteados(){
		for(String x : diasSorteados){
			System.out.print(x);
		}
	}
	
	public void addDiasSorteados(String dia){
		if(dia != null){
			this.diasSorteados.add(dia);
		}
		
	}
	
	public StringProperty getDiasSorteadosProperty(){
		String x = "";
		for(String r : diasSorteados){
			 x += r + " ";
		}
		StringProperty resultado = new SimpleStringProperty(x.substring(0, (x.length())));
		return resultado;
	}
	
	public StringProperty getNomeProperty(){
		StringProperty resultado = new SimpleStringProperty(selecionado.getNome());
		return resultado;
	}
	
	public StringProperty getMatriculaProperty(){
		StringProperty resultado = new SimpleStringProperty(this.selecionado.getMatricula());
		return resultado;
	}
	
	public StringProperty getCargoProperty(){
		StringProperty resultado = new SimpleStringProperty(this.selecionado.getCargo());
		return resultado;
	}
	
	public StringProperty getFuncaoProperty(){
		if(this.selecionado.isMotorista()){
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
		StringProperty resultado = new SimpleStringProperty("" + this.diasSorteados.size());
		return resultado;
	}


	public boolean equals(Object obj) {
		boolean resultado = false;
		if(obj instanceof Selecionado){
			Selecionado temp = (Selecionado) obj;
			if(temp.selecionado.getMatricula().equals(this.selecionado.getMatricula())){
				resultado = true;
			}
		}
		return resultado;
	}
	
	public String toString(){
		return ("\n" + selecionado.getMatricula() + " - " + selecionado.getNome() + " "  + selecionado.getSexo() + " " + this.getDiasSorteados() );
	}
	
	public int compareTo(Selecionado s2){
		if(Integer.parseInt(this.getMatricula()) > Integer.parseInt(s2.getMatricula()) ){
			return 1;
		}
		if(Integer.parseInt(this.getMatricula()) < Integer.parseInt(s2.getMatricula()) ){
			return -1;
		}
		else{
			return 0;
		}
	}

}
