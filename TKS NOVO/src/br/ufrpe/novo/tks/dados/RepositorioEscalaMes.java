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
	
	public void startRep2(){
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

		Selecionado temp1 = new Selecionado((Funcionario)pessoa01);
		Selecionado temp2 = new Selecionado((Funcionario)pessoa02);
		Selecionado temp3 = new Selecionado((Funcionario)pessoa03);
		Selecionado temp4 = new Selecionado((Funcionario)pessoa04);
		Selecionado temp5 = new Selecionado((Funcionario)pessoa05);
		Selecionado temp6 = new Selecionado((Funcionario)pessoa06);
		Selecionado temp7 = new Selecionado((Funcionario)pessoa07);
		Selecionado temp8 = new Selecionado((Funcionario)pessoa08);
		Selecionado temp9 = new Selecionado((Funcionario)pessoa09);
		Selecionado temp10 = new Selecionado((Funcionario)pessoa10);
		Selecionado temp11 = new Selecionado((Funcionario)pessoa11);
		Selecionado temp12 = new Selecionado((Funcionario)pessoa12);
		Selecionado temp13 = new Selecionado((Funcionario)pessoa13);
		Selecionado temp14 = new Selecionado((Funcionario)pessoa14);
		Selecionado temp15 = new Selecionado((Funcionario)pessoa15);
		Selecionado temp16 = new Selecionado((Funcionario)pessoa16);
		Selecionado temp17 = new Selecionado((Funcionario)pessoa17);
		Selecionado temp18 = new Selecionado((Funcionario)pessoa18);
		Selecionado temp19 = new Selecionado((Funcionario)pessoa19);
		Selecionado temp20 = new Selecionado((Funcionario)pessoa20);
		Selecionado temp21 = new Selecionado((Funcionario)pessoa21);
		Selecionado temp22 = new Selecionado((Funcionario)pessoa22);
		Selecionado temp23 = new Selecionado((Funcionario)pessoa23);
		Selecionado temp24 = new Selecionado((Funcionario)pessoa24);
		Selecionado temp25 = new Selecionado((Funcionario)pessoa25);
		Selecionado temp26 = new Selecionado((Funcionario)pessoa26);
		Selecionado temp27 = new Selecionado((Funcionario)pessoa27);
		Selecionado temp28 = new Selecionado((Funcionario)pessoa28);
		Selecionado temp29 = new Selecionado((Funcionario)pessoa29);
		Selecionado temp30 = new Selecionado((Funcionario)pessoa30);
		ArrayList<Selecionado> voluntarios = new ArrayList<>();
		
		temp1.addDiasSorteados("" + 1);
		temp1.addDiasSorteados("" + 2);
		temp2.addDiasSorteados("" + 3);
		temp2.addDiasSorteados("" + 4);
		temp3.addDiasSorteados("" + 5);
		temp3.addDiasSorteados("" + 6);
		temp4.addDiasSorteados("" + 7);
		temp4.addDiasSorteados("" + 8);
		temp5.addDiasSorteados("" + 9);
		temp5.addDiasSorteados("" + 10);
		temp6.addDiasSorteados("" + 11);
		temp6.addDiasSorteados("" + 12);
		temp7.addDiasSorteados("" + 7);
		temp7.addDiasSorteados("" + 8);
		temp8.addDiasSorteados("" + 9);
		temp8.addDiasSorteados("" + 10);
		temp9.addDiasSorteados("" + 11);
		temp9.addDiasSorteados("" + 12);
		voluntarios.add(temp1) ;
		voluntarios.add(temp2);
		voluntarios.add(temp3);
		voluntarios.add(temp4);
		voluntarios.add(temp5);
		voluntarios.add(temp6);
		voluntarios.add(temp7);
		voluntarios.add(temp8);
		voluntarios.add(temp9);
		nova.setVoluntarios(voluntarios);
		instance.escala.add(nova);
	}
	
	//
	private RepositorioEscalaMes(){
		this.escala = new ArrayList<EscalaMes>();
		this.startRep2();


	}
	
	public static RepositorioEscalaMes getInstance(){
		if(instance == null){
			instance = RepositorioEscalaMes.carregarbd();
		
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
