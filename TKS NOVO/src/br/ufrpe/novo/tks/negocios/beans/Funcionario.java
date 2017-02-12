package br.ufrpe.novo.tks.negocios.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
	

	public IntegerProperty getEscalaProperty() {
		IntegerProperty resultado = new SimpleIntegerProperty();
		if(this.escala != null) {
			resultado = this.escala.getEscalaIntegerProperty(); 
		}
		return resultado;
	}
	
	public StringProperty getCargoProperty() {
		StringProperty cargo = new SimpleStringProperty(this.cargo);
		return cargo;
	}
	
	public StringProperty getPreferidosFuncProperty(){
		StringProperty resultado = new SimpleStringProperty("NÃO INFORMADO");
		if(this.escala != null && this.escala.getDiasPreferidos().size() > 0){
			resultado = this.escala.getPreferidosProperty();
		}
		return resultado;
	}
	
	public StringProperty getStringPropertyEscala(){
		StringProperty resultado = new SimpleStringProperty();
		int x = 0;
	    if(this.escala != null){
	    	x = this.escala.getEscala();
	
			if(x == 1){
				resultado = new SimpleStringProperty("1 - 5 - 9 - 13 - 17 - 21 - 25 - 29");
			}
			else if(x == 2){
				resultado = new SimpleStringProperty("2 - 6 - 10 - 14 - 18 - 22 - 26 - 30");
			}
			else if(x == 3){
				resultado = new SimpleStringProperty("3 - 7 - 11 - 15 - 19 - 23 - 27 - 31");
			}
			else if(x == 4){
				resultado = new SimpleStringProperty("4 - 8 - 12 - 16 - 20 - 24 - 28");
			}
			else if(x == 0){
				resultado = new SimpleStringProperty("EXPEDIENTE");
			}
			else{
				resultado = new SimpleStringProperty("EXPEDIENTE");
			}
	    }
		
		return resultado;
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
		this.escala = new EscalaNormal(0);
		this.escala = temp;
	}
	
	public void atualizaEscala(int escala, int extrasQtd){
		EscalaNormal escalaAtualizada = new EscalaNormal(escala);
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
	
	public boolean equals(Object o){
		boolean resultado = false;
		if(o != null){
			if(o instanceof Funcionario){
				if(super.equals(o)){
					if(this.cargo.equals(((Funcionario)o).getCargo()) && this.motorista == ((Funcionario)o).motorista ){
						resultado = true;
					}
				}
				
			}
		}
		
		return resultado;
	}
	
	public String toString(){
		return super.toString() + 
				"Cargo: " + this.cargo + "\n" +
				"Motorista: " + this.motorista + "\n";
	}
	
	
}