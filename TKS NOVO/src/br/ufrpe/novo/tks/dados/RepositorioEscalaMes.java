package br.ufrpe.novo.tks.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import br.ufrpe.novo.tks.negocios.beans.EscalaMes;
import br.ufrpe.novo.tks.negocios.beans.Funcionario;
import br.ufrpe.novo.tks.negocios.beans.Pessoa;
import br.ufrpe.novo.tks.negocios.beans.Selecionado;

public class RepositorioEscalaMes implements IRepositorioEscalaMes, Serializable{
	
	private static final long serialVersionUID = -3798012738335471181L;
	private static RepositorioEscalaMes instance;
	private ArrayList<EscalaMes> escala;
	
	//
	
	
	EscalaMes nova = new EscalaMes();
	Pessoa pessoa01 = new Funcionario("G. SANTANA", "2º SARGENTO", 'M', "1", true, "123");
	Pessoa pessoa02 = new Funcionario("BARBOSA LIMA", "SOLDADO", 'M', "2", true, "1415");
	Pessoa pessoa03 = new Funcionario("WILMA", "SOLDADO", 'F', "3", false, "123");
	Pessoa pessoa04 = new Funcionario("GERVÁSIO", "SOLDADO", 'M', "4", true, "123");
	Pessoa pessoa05 = new Funcionario("PAULO ALMEIDA", "SOLDADO", 'M', "5", true, "123");//
	Pessoa pessoa06 = new Funcionario("ADRIANO FRANCISCO", "SOLDADO", 'M', "6", true, "1415");//
	Pessoa pessoa07 = new Funcionario("JOSEILDO", "3º SARGENTO", 'M', "7", true, "123");
	Pessoa pessoa08 = new Funcionario("CLEVESON", "SOLDADO", 'M', "8", true, "123");//
	Pessoa pessoa09 = new Funcionario("HERÁCLIO", "2º SARGENTO", 'M', "9", true, "1415");// ------ 
	Pessoa pessoa10 = new Funcionario("DANIELE", "2º SARGENTO", 'F', "10", false, "123");//
	Pessoa pessoa11 = new Funcionario("RISOALDO", "CABO", 'M', "11", true, "1415");//
	Pessoa pessoa12 = new Funcionario("RICARDO", "CABO", 'M', "12", false, "123");//
	Pessoa pessoa13 = new Funcionario("EINSTEIN", "SOLDADO", 'M', "13", true, "1415");//
	Pessoa pessoa14 = new Funcionario("ÉRIK", "SOLDADO", 'M', "14", true, "123");// ---
	Pessoa pessoa15 = new Funcionario("MARIA", "SOLDADO", 'F', "15", true, "123");
	Pessoa pessoa16 = new Funcionario("DÉBORA COSTA", "SOLDADO", 'F', "16", true, "123");
	Pessoa pessoa17 = new Funcionario("S. MONETEIRO", "SOLDADO", 'M', "17", true, "1415");//
	Pessoa pessoa18 = new Funcionario("SUELLEN", "SOLDADO", 'F', "18", true, "123");//
	Pessoa pessoa19 = new Funcionario("EDSON JOSÉ", "SOLDADO", 'M', "19", true, "1415");//
	Pessoa pessoa20 = new Funcionario("MAURÍLIO", "SOLDADO", 'M', "20", true, "123");
	Pessoa pessoa21 = new Funcionario("RICARDO", "SOLDADO", 'M', "21", true, "123");
	Pessoa pessoa22 = new Funcionario("CONCEIÇÃO MONTEIRO", "SOLDADO", 'M', "22", true, "123");
	Pessoa pessoa23 = new Funcionario("J. COSTA", "SOLDADO", 'M', "23", true, "123");//
	Pessoa pessoa24 = new Funcionario("PACÍFICO", "SOLDADO", 'M', "24", true, "1415");// --  
	Pessoa pessoa25 = new Funcionario("CLAUDENÍCIO", "SOLDADO", 'M', "25", false, "123");//
	Pessoa pessoa26 = new Funcionario("IGNÁCIO", "SOLDADO", 'M', "26", true, "123");
	Pessoa pessoa27 = new Funcionario("EVERTON", "SOLDADO", 'M', "27", true, "123");
	Pessoa pessoa28 = new Funcionario("MACIEL", "CABO", 'M', "28", false, "1415");// 
	Pessoa pessoa29 = new Funcionario("J. MARINHO", "2º SARGENTO", 'M', "29", true, "123");//
	Pessoa pessoa30 = new Funcionario("R. SOUZA", "SOLDADO", 'M', "30", true, "123");

	Selecionado temp1 = new Selecionado(pessoa01, 2);
	Selecionado temp2 = new Selecionado(pessoa02, 2);
	Selecionado temp3 = new Selecionado(pessoa03, 2);
	Selecionado temp4 = new Selecionado(pessoa04, 2);
	Selecionado temp5 = new Selecionado(pessoa05, 2);
	Selecionado temp6 = new Selecionado(pessoa06, 2);
	Selecionado temp7 = new Selecionado(pessoa07, 2);
	Selecionado temp8 = new Selecionado(pessoa08, 2);
	Selecionado temp9 = new Selecionado(pessoa09, 2);
	Selecionado temp10 = new Selecionado(pessoa10, 2);
	Selecionado temp11 = new Selecionado(pessoa11, 2);
	Selecionado temp12 = new Selecionado(pessoa12, 2);
	Selecionado temp13 = new Selecionado(pessoa13, 2);
	Selecionado temp14 = new Selecionado(pessoa14, 2);
	Selecionado temp15 = new Selecionado(pessoa15, 2);
	Selecionado temp16 = new Selecionado(pessoa16, 2);
	Selecionado temp17 = new Selecionado(pessoa17, 2);
	Selecionado temp18 = new Selecionado(pessoa18, 2);
	Selecionado temp19 = new Selecionado(pessoa19, 2);
	Selecionado temp20 = new Selecionado(pessoa20, 2);
	Selecionado temp21 = new Selecionado(pessoa21, 2);
	Selecionado temp22 = new Selecionado(pessoa22, 2);
	Selecionado temp23 = new Selecionado(pessoa23, 2);
	Selecionado temp24 = new Selecionado(pessoa24, 2);
	Selecionado temp25 = new Selecionado(pessoa25, 2);
	Selecionado temp26 = new Selecionado(pessoa26, 2);
	Selecionado temp27 = new Selecionado(pessoa27, 2);
	Selecionado temp28 = new Selecionado(pessoa28, 2);
	Selecionado temp29 = new Selecionado(pessoa29, 2);
	Selecionado temp30 = new Selecionado(pessoa30, 2);
	Selecionado [] voluntarios = new Selecionado[9];
	
	//
	private RepositorioEscalaMes(){
		this.escala = new ArrayList<EscalaMes>();
		

		temp1.setDiasSorteados(1);
		temp1.setDiasSorteados(2);
		temp2.setDiasSorteados(3);
		temp2.setDiasSorteados(4);
		temp3.setDiasSorteados(5);
		temp3.setDiasSorteados(6);
		temp4.setDiasSorteados(7);
		temp4.setDiasSorteados(8);
		temp5.setDiasSorteados(9);
		temp5.setDiasSorteados(10);
		temp6.setDiasSorteados(11);
		temp6.setDiasSorteados(12);
		temp7.setDiasSorteados(7);
		temp7.setDiasSorteados(8);
		temp8.setDiasSorteados(9);
		temp8.setDiasSorteados(10);
		temp9.setDiasSorteados(11);
		temp9.setDiasSorteados(12);
		voluntarios[0] = temp1;
		voluntarios[1] = temp2;
		voluntarios[2] = temp3;
		voluntarios[3] = temp4;
		voluntarios[4] = temp5;
		voluntarios[5] = temp6;
		voluntarios[6] = temp7;
		voluntarios[7] = temp8;
		voluntarios[8] = temp9;
		nova.setVoluntarios(voluntarios);
		this.escala.add(nova);
	}
	
	public static RepositorioEscalaMes getInstance(){
		if(instance == null){
			instance = RepositorioEscalaMes.carregarbd();;
		}
		return instance;
	}

	@Override
	public void cadastrarEscala(EscalaMes mesAno) {
		if(mesAno != null){
			escala.add(mesAno);
		}
		
	}

	@Override
	public void removerEscala(EscalaMes mesAno) {
		if(mesAno != null){
			escala.remove(mesAno);
		}
		
	}

	@Override
	public EscalaMes procurarEscala(String mesAno) {
		EscalaMes resultado = null;
		if(this.escala.size() > 0){
			for(int i = 0; i < this.escala.size(); i++){
				if(mesAno.equals(this.escala.get(i).getMesAno())){
					resultado = this.escala.get(i);
				}
			}
		}
		return resultado;
	}

	@Override
	public ArrayList<EscalaMes> getEscalas() {
		return this.escala;
	}

	@Override
	public void salvarbd() {
		if (!(instance == null)) {

			File bd = new File("RepositorioEscalaMes.tks");

			try {

				if (!bd.exists()) {
					bd.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(bd);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(instance);
				oos.flush();
				oos.close();
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}
	
	private static RepositorioEscalaMes carregarbd() {

		RepositorioEscalaMes repositorio = null;

		File bd = new File("RepositorioEscalaMes.tks");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {

			fis = new FileInputStream(bd);
			ois = new ObjectInputStream(fis);

			repositorio = (RepositorioEscalaMes) ois.readObject();
		} catch (Exception e) {
			repositorio = new RepositorioEscalaMes();

			try {
				if (!bd.exists()) {
					bd.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(bd);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(repositorio);
				oos.flush();
				oos.close();
				fos.flush();
				fos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			//e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println("Não foi possível fechar o arquivo!");
					e.printStackTrace();
				}
			}
		}

		return repositorio;
	}

}
