package br.ufrpe.novo.tks.negocios.beans;

public class Administrador extends Pessoa{
	
	private static final long serialVersionUID = -6984609885438774692L;

	public Administrador(String nome, char sexo, String matricula, String senha){
		super(nome, sexo, matricula, senha);
	}
	
	public void setMatricula(String matricula){
		super.setMatricula(matricula);
	}
	
	public String getMatricula(){
		return super.getMatricula();
	}
	
	public boolean equals(Object o){
		boolean resultado=false;
		if(o != null){
			if(o instanceof Administrador){
				super.equals(o);
			}
		}
		return resultado;
	}
}
