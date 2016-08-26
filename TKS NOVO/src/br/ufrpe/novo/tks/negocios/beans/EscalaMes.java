package br.ufrpe.novo.tks.negocios.beans;


import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class EscalaMes implements Serializable{
	
	
	private static final long serialVersionUID = -1097567439259145088L;
	private LocalDate mesAno;
	private Selecionado [] voluntarios;
	
	public EscalaMes(){
		this.mesAno = LocalDate.now();
	}
	
	public String getMesAno(){
		DateTimeFormatter retorno = DateTimeFormatter.ofPattern("MM/YYYY");
		return this.mesAno.format(retorno);
	}
	
	public Selecionado [] getVoluntarios(){
		return this.voluntarios;
	}
	
	public void setVoluntarios(Selecionado [] voluntarios){
		this.voluntarios = voluntarios;
	}
	
	public Selecionado getVoluntario(int i){
		return this.voluntarios[i];
	}
	
	public String toString(){
		
		return Arrays.toString(this.voluntarios);
		
	}

}
