package br.ufrpe.novo.tks.negocios.beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Funcionario extends Pessoa {
	
	private static final long serialVersionUID = 4250263384116171487L;
	private String cargo;
	private boolean motorista;
	private EscalaNormal escala;
	
	
	public Funcionario(String nome, String cargo, char sexo, String matricula, boolean motorista, String senha){
		super(nome, sexo, matricula, senha);
		this.cargo = cargo;
		this.motorista = motorista;
	}
	
	public StringProperty cargoProperty() {
		StringProperty cargo = new SimpleStringProperty(this.cargo);
		return cargo;
	}
	
	public StringProperty motoristaProperty() {
		SimpleStringProperty motorista;
		if(this.motorista){
			motorista = new SimpleStringProperty("Motorista");
		}
		else{
			motorista = new SimpleStringProperty("Comandante");
		}
		
		return motorista;
	}
	
	public EscalaNormal getEscalaNormal(){
		return this.escala;
	}
	
	public void setEscalaNormalFunc(EscalaNormal temp){
		this.escala = new EscalaNormal(0, 0);
		this.escala = temp;
	}
	
	public void atualizaEscala(int escala, int extrasQtd){
		EscalaNormal escalaAtualizada = new EscalaNormal(escala, extrasQtd);
		this.escala = escalaAtualizada;
	}
	
	public void setEscala(int x){
		this.escala.setEscala(x);
	}
	
	public int escalaQtd(){
		return escala.getDiasPreferidosQtd();
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public boolean isMotorista() {
		return motorista;
	}

	public void setMotorista(boolean motorista) {
		this.motorista = motorista;
	}
	
	public boolean equals(Funcionario f){
		boolean resultado=false;
		
		if(((Pessoa)f).getMatricula() == ((Pessoa)this).getMatricula()){
			resultado=true;
		}
		
		return resultado;
	}
	
	public String toString(){
		return super.toString() + 
				"Cargo: " + this.cargo + "\n" +
				"Motorista: " + this.motorista + "\n";
	}
}