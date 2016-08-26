package br.ufrpe.novo.tks.dados;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import br.ufrpe.novo.tks.negocios.beans.Administrador;
import br.ufrpe.novo.tks.negocios.beans.Funcionario;
import br.ufrpe.novo.tks.negocios.beans.Pessoa;

public class RepositorioPessoa implements IRepositorioPessoa, Serializable {

	private static final long serialVersionUID = -6495110561477281438L;
	private static RepositorioPessoa instance;
	private ArrayList<Pessoa> pessoa;

	Pessoa pessoa1 = new Funcionario("José Rafael", "Cabo", 'M', "12345", true, "1415");
	Pessoa pessoa2 = new Funcionario("Éder Lucena", "Major", 'F', "103418", false, "123");
	Pessoa administrador = new Administrador("Administrador", 'M', "Admin", "Admin");
	Pessoa administrador2 = new Administrador("Sport", 'M', "1000", "123");
	
	Pessoa pessoa01 = new Funcionario("G. SANTANA", "2º SARGENTO", 'M', "1", true, "123");
	Pessoa pessoa02 = new Funcionario("BARBOSA LIMA", "SOLDADO", 'M', "2", true, "1415");//
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
	Pessoa pessoa31 = new Funcionario("A. GOMES", "SOLDADO", 'M', "31", true, "123");
	Pessoa pessoa32 = new Funcionario("J. MELO", "SOLDADO", 'M', "32", true, "123");
	Pessoa pessoa33 = new Funcionario("MÉRCIA FONSÊCA", "SOLDADO", 'F', "33", true, "123");
	Pessoa pessoa34 = new Funcionario("JAQUELINE MENDONÇA", "SOLDADO", 'F', "34", true, "123");
	Pessoa pessoa35 = new Funcionario("FLAVISON", "2º SARGENTO", 'M', "35", true, "123");
	Pessoa pessoa36 = new Funcionario("DANIELLY MICHELLY", "SOLDADO", 'F', "36", true, "1415");//
	Pessoa pessoa37 = new Funcionario("VANIELLY", "SOLDADO", 'F', "37", true, "123");// -- 
	Pessoa pessoa38 = new Funcionario("RÔMULO", "2º SARGENTO", 'M', "38", true, "123");
	Pessoa pessoa39 = new Funcionario("P. RAFAEL", "3º SARGENTO", 'M', "39", true, "123");
	Pessoa pessoa40 = new Funcionario("LINDINALDO", "SOLDADO", 'M', "40", true, "123");
	Pessoa pessoa41 = new Funcionario("EDER", "CABO", 'M', "41", false, "123");
	Pessoa pessoa42 = new Funcionario("AZEVEDO FILHO", "SOLDADO", 'M', "42", false, "123");
	Pessoa pessoa43 = new Funcionario("ARAGÃO", "CABO", 'M', "43", true, "123");
	Pessoa pessoa44 = new Funcionario("G. BARBOZA", "SOLDADO", 'M', "44", true, "123");
	Pessoa pessoa45 = new Funcionario("ALCIDES", "SOLDADO", 'M', "45", true, "123");
	Pessoa pessoa46 = new Funcionario("CARLOS MOURA", "SOLDADO", 'M', "46", true, "123");
	Pessoa pessoa47 = new Funcionario("CLAUDIO LEAL", "SOLDADO", 'M', "47", true, "123");
	Pessoa pessoa48 = new Funcionario("LEANDRO SANTANA", "SOLDADO", 'M', "48", true, "123");
	Pessoa pessoa49 = new Funcionario("J. EDUARDO", "SOLDADO", 'M', "49", true, "123");
	Pessoa pessoa50 = new Funcionario("RENATO", "SOLDADO", 'M', "50", true, "123");
	Pessoa pessoa51 = new Funcionario("CEZAR CAMPELO", "SOLDADO", 'M', "51", true, "123");
	
	
	

	public static RepositorioPessoa getInstance() {
		if (instance == null) {
			instance = RepositorioPessoa.carregarbd();
		}
		return instance;
	}

	private RepositorioPessoa() {
		this.pessoa = new ArrayList<Pessoa>();

		pessoa.add(pessoa1);
		pessoa.add(pessoa2);
		pessoa.add(administrador);
		pessoa.add(administrador2);
		pessoa.add(pessoa01);
		pessoa.add(pessoa02);
		pessoa.add(pessoa03);
		pessoa.add(pessoa04);
		pessoa.add(pessoa05);
		pessoa.add(pessoa06);
		pessoa.add(pessoa07);
		pessoa.add(pessoa08);
		pessoa.add(pessoa09);
		pessoa.add(pessoa10);
		pessoa.add(pessoa11);
		pessoa.add(pessoa12);
		pessoa.add(pessoa13);
		pessoa.add(pessoa14);
		pessoa.add(pessoa15);
		pessoa.add(pessoa16);
		pessoa.add(pessoa17);
		pessoa.add(pessoa18);
		pessoa.add(pessoa19);
		pessoa.add(pessoa20);
		pessoa.add(pessoa21);
		pessoa.add(pessoa22);
		pessoa.add(pessoa23);
		pessoa.add(pessoa24);
		pessoa.add(pessoa25);
		pessoa.add(pessoa26);
		pessoa.add(pessoa27);
		pessoa.add(pessoa28);
		pessoa.add(pessoa29);
		pessoa.add(pessoa30);
		pessoa.add(pessoa31);
		pessoa.add(pessoa32);
		pessoa.add(pessoa33);
		pessoa.add(pessoa34);
		pessoa.add(pessoa35);
		pessoa.add(pessoa36);
		pessoa.add(pessoa37);
		pessoa.add(pessoa38);
		pessoa.add(pessoa39);
		pessoa.add(pessoa40);
		pessoa.add(pessoa41);
		pessoa.add(pessoa42);
		pessoa.add(pessoa43);
		pessoa.add(pessoa44);
		pessoa.add(pessoa45);
		pessoa.add(pessoa46);
		pessoa.add(pessoa47);
		pessoa.add(pessoa48);
		pessoa.add(pessoa49);
		pessoa.add(pessoa50);
		pessoa.add(pessoa51);
	}

	@Override
	public void cadastrar(Pessoa p) {
		if (p != null) {
			this.pessoa.add(p);
		}
	}

	@Override
	public void remover(Pessoa p) {
		if (p != null) {
			this.pessoa.remove(p);
		}
	}

	@Override
	public Pessoa procurar(String matricula) {
		Pessoa pessoaP = null;
		if (this.pessoa.size() > 0) {
			for (int i = 0; i < this.pessoa.size(); i++) {
				if (matricula.equals(this.pessoa.get(i).getMatricula())) {
					pessoaP = this.pessoa.get(i);
				}
			}
		}
		return pessoaP;
	}

	@Override
	public ArrayList<Pessoa> getUsuarios() {
		return this.pessoa;
	}

	private static RepositorioPessoa carregarbd() {

		RepositorioPessoa repositorio = null;

		File bd = new File("RepositorioPessoa.tks");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {

			fis = new FileInputStream(bd);
			ois = new ObjectInputStream(fis);

			repositorio = (RepositorioPessoa) ois.readObject();
		} catch (Exception e) {
			repositorio = new RepositorioPessoa();

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

	@Override
	public void salvarbd() {
		if (!(instance == null)) {

			File bd = new File("RepositorioPessoa.tks");

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
}