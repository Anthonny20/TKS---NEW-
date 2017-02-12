package br.ufrpe.novo.tks.negocios.beans;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EscalaNormal implements Serializable {
	
	private static final long serialVersionUID = 9081719589446480359L;
	
	private String [] diasBloqueados = new String [8];// DIAS Q NÃO PODEM SER SORTEADOS
	private int escala; // DETERMINA QUAL DIAS SERÃO POSTOS NO ARRAY DE BLOCK
	private ArrayList<String> diasPreferidos;
	private int diasQTD;
	private boolean voluntario;
	
	public EscalaNormal(int x){
		
		this.setDiasSorteados(x);
		this.voluntario = true;
		this.escala = x;
		this.diasPreferidos = new ArrayList<String>();
		this.diasQTD = 4;
		
		}
	
	public int getEscala(){
		return this.escala;
	}
	
	public int getDiasQTD(){
		return this.diasQTD;
	}
	
	public void setDiasQTD(int diasQTD){
		this.diasQTD = diasQTD;
	}
	
	public StringProperty getPreferidosProperty(){
		StringProperty resultado = new SimpleStringProperty();
		
		resultado = new SimpleStringProperty(diasPreferidos.toString().substring(1, diasPreferidos.toString().length() -1));
					
		return resultado;
	}
	
	public IntegerProperty getEscalaIntegerProperty(){
		
		IntegerProperty resultado = new SimpleIntegerProperty(this.escala);
		return resultado;
	}
	
	public void setEscala(int x){
		this.escala = x;
	}
	
	//RETORNA UM VALOR REFERENTE A QUANTIA DE SERVIÇOS QUE O FUNCIONARIO SE VOLUNTARIOU.
	public int getDiasPreferidosQtd(){
		return this.diasPreferidos.size();
	}
	
	public ArrayList<String> getDiasPreferidos(){
		return this.diasPreferidos;
	}
	
	public String getDiaPreferido(int i){
		return this.diasPreferidos.get(i);
	}
	
	public void setDiaPreferido(int i, String x){
	
		this.diasPreferidos.set(i, x);
	}
	
	public String [] getDiasBloqueados(){
		return this.diasBloqueados;
	}
	
	public String getDiaBloqueado(int i){
		return this.diasBloqueados[i];
	}
	
/*	public int getExtrasQtd(){
		return this.extrasQtd.length;
	}
*/
	public boolean isVoluntario(){
		return voluntario;
	}
	
	public void setVoluntario(boolean x){
		this.voluntario = x;
	}
	
	public void showExtras(int extrasQtd[]){//mostrar ao funcionario os dias sorteados
		for(int mostra : extrasQtd){
			System.out.println(mostra);
		}
	}
	
	public void setDiasSorteados(int x){
		if(x == 0){
			diasBloqueados[0] = "" + 32;
			diasBloqueados[1] = "" + 32;
			diasBloqueados[2] = "" + 32;
			diasBloqueados[3] = "" + 32;
			diasBloqueados[4] = "" + 32;
			diasBloqueados[5] = "" + 32;
			diasBloqueados[6] = "" + 32;
			diasBloqueados[7] = "" + 32;
		}
		else if(x == 1){
			diasBloqueados[0] = "0" + 1;
			diasBloqueados[1] = "0" + 5;
			diasBloqueados[2] = "0" + 9;
			diasBloqueados[3] = "" + 13;
			diasBloqueados[4] = "" + 17;
			diasBloqueados[5] = "" + 21;
			diasBloqueados[6] = "" + 25;
			diasBloqueados[7] = "" + 29;
		}
		else if(x == 2){
			diasBloqueados[0] = "0" + 2;
			diasBloqueados[1] = "0" + 6;
			diasBloqueados[2] = "" + 10;
			diasBloqueados[3] = "" + 14;
			diasBloqueados[4] = "" + 18;
			diasBloqueados[5] = "" + 22;
			diasBloqueados[6] = "" + 26;
			diasBloqueados[7] = "" + 30;
		}
		else if(x == 3){
			diasBloqueados[0] = "0" + 3;
			diasBloqueados[1] = "0" + 7;
			diasBloqueados[2] = "" + 11;
			diasBloqueados[3] = "" + 15;
			diasBloqueados[4] = "" + 19;
			diasBloqueados[5] = "" + 23;
			diasBloqueados[6] = "" + 27;
			diasBloqueados[7] = "" + 31;
		}
		else if(x == 4){
			diasBloqueados[0] = "0" + 4;
			diasBloqueados[1] = "0" + 8;
			diasBloqueados[2] = "" + 12;
			diasBloqueados[3] = "" + 16;
			diasBloqueados[4] = "" + 20;
			diasBloqueados[5] = "" + 24;
			diasBloqueados[6] = "" + 28;
			diasBloqueados[7] = "" + 32;
		
			
			}
	}
	
/*	public void validarDiasDiferentes(int extras[]){
		for(int i = 0; i < this.extrasQtd.length; i++){
			for(int j = 0; j < this.extrasQtd.length; j++){
				if(this.extrasQtd[i] == this.extrasQtd[j] && i != j){
					this.extrasQtd[j] = 0;
				}
			}
		}
	}*/

}
