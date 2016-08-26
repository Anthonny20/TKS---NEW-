package br.ufrpe.novo.tks.negocios.beans;

import java.io.Serializable;

public class EscalaNormal implements Serializable {
	
	private static final long serialVersionUID = 9081719589446480359L;
	
	private int [] diasBloqueados = new int [8];// dias q nao podem ser sorteados
	private int escala; // determina qual serie de dias sera posta no array de blok
	private int [] diasPreferidos;
	private boolean voluntario;
	
	public EscalaNormal(int x, int qtd){
		
		this.voluntario = true;
		this.escala = x;
		this.diasPreferidos = new int[qtd];
		
		}
	
	public int getEscala(){
		return this.escala;
	}
	
	public void setEscala(int x){
		this.escala = x;
	}
	
	//RETORNA UM VALOR REFERENTE A QUANTIA DE SERVIÇOS QUE O FUNCIONARIO SE VOLUNTARIOU.
	public int getDiasPreferidosQtd(){
		return this.diasPreferidos.length;
	}
	
	public int[] getDiasPreferidos(){
		return this.diasPreferidos;
	}
	
	public int getDiaPreferido(int i){
		return this.diasPreferidos[i];
	}
	
	public void setDiaPreferido(int i, int x){
		this.diasPreferidos[i] = x;
	}
	
	public int [] getDiasBloqueados(){
		return this.diasBloqueados;
	}
	
	public int getDiaBloqueado(int i){
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
			diasBloqueados[0] = 32;
			diasBloqueados[1] = 32;
			diasBloqueados[2] = 32;
			diasBloqueados[3] = 32;
			diasBloqueados[4] = 32;
			diasBloqueados[5] = 32;
			diasBloqueados[6] = 32;
			diasBloqueados[7] = 32;
		}
		else if(x == 1){
			diasBloqueados[0] = 1;
			diasBloqueados[1] = 5;
			diasBloqueados[2] = 9;
			diasBloqueados[3] = 13;
			diasBloqueados[4] = 17;
			diasBloqueados[5] = 21;
			diasBloqueados[6] = 25;
			diasBloqueados[7] = 29;
		}
		else if(x == 2){
			diasBloqueados[0] = 2;
			diasBloqueados[1] = 6;
			diasBloqueados[2] = 10;
			diasBloqueados[3] = 14;
			diasBloqueados[4] = 18;
			diasBloqueados[5] = 22;
			diasBloqueados[6] = 26;
			diasBloqueados[7] = 30;
		}
		else if(x == 3){
			diasBloqueados[0] = 3;
			diasBloqueados[1] = 7;
			diasBloqueados[2] = 11;
			diasBloqueados[3] = 15;
			diasBloqueados[4] = 19;
			diasBloqueados[5] = 23;
			diasBloqueados[6] = 27;
			diasBloqueados[7] = 31;
		}
		else if(x == 4){
			diasBloqueados[0] = 4;
			diasBloqueados[1] = 8;
			diasBloqueados[2] = 12;
			diasBloqueados[3] = 16;
			diasBloqueados[4] = 20;
			diasBloqueados[5] = 24;
			diasBloqueados[6] = 28;
			diasBloqueados[7] = 32;
		
			
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
