package br.ufrpe.novo.tks.negocios.beans;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EscalaMes implements Serializable{
	
	
	private static final long serialVersionUID = -1097567439259145088L;
	private LocalDate mesAno;
	private ArrayList<Selecionado> voluntarios;
	
	public EscalaMes(){
		this.mesAno = LocalDate.now().plusMonths(1);
	}
	
	public String getMesAno(){
		DateTimeFormatter retorno = DateTimeFormatter.ofPattern("MM/YYYY");
		return this.mesAno.format(retorno);
	}
	
	public ArrayList<Selecionado> getVoluntarios(){
		return this.voluntarios;
	}
	
	public void setVoluntarios(ArrayList<Selecionado> voluntarios){
		this.voluntarios = voluntarios;
	}
	
	public Selecionado getVoluntario(int i){
		return this.voluntarios.get(i);
	}
	
	public void setMesAno(LocalDate x){
		this.mesAno = x;
	}
	
	public String toString(){
		String resultado = "";
		for(Selecionado x : voluntarios){
			resultado += (x.toString());
		}
		
		return resultado;
		
	}

}
