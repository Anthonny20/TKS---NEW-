package br.ufrpe.novo.tks.negocios.beans;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pessoa implements Serializable, Comparable<Pessoa> {

	private static final long serialVersionUID = 1944023678680285924L;
	private String nome;
	private char sexo;
	private String matricula;
	private String senha;

	public Pessoa(String nome, char sexo, String matricula, String senha) {
		this.nome = nome;
		this.sexo = sexo;
		this.matricula = matricula;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setSenha(String novaSenha) {
		this.senha = novaSenha;
	}

	public String getSenha() {
		return this.senha;
	}
	
	public StringProperty getSenhaProperty(){
		StringProperty senha = new SimpleStringProperty(this.senha);
		return senha;
	}

	public String toString() {
		return ("Nome: " + this.getNome() + "\n" + "Sexo: " + this.getSexo() + "\n" + "Matricula: "
				+ this.getMatricula() + "\n");
	}

	public StringProperty getMatriculaProperty() {
		StringProperty matricula = new SimpleStringProperty(this.matricula);
		return matricula;
	}

	public StringProperty getNomeProperty() {
		StringProperty nome = new SimpleStringProperty(this.nome);
		return nome;
	}
	
	public boolean equals(Object o){
		boolean resultado = false;
		if(o != null){
			if(o instanceof Pessoa)
			if(((Pessoa)o).matricula.equals(this.matricula) && ((Pessoa)o).nome.equals(this.nome) 
					&& ((Pessoa)o).getSexo() == this.sexo){
				resultado = true;
			}
		}
		return resultado;
	}
	
	public int compareTo(Pessoa p2){
		if(Integer.parseInt(this.getMatricula()) < Integer.parseInt(p2.getMatricula())){
			return -1;
		}
		if(Integer.parseInt(this.matricula) > Integer.parseInt(p2.getMatricula())){
			return 1;
		}
		else{
			return 0;
		}
	}

}
