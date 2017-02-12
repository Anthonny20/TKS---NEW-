package br.ufrpe.novo.tks.gui.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import br.ufrpe.novo.tks.dados.IRepositorioPessoa;
import br.ufrpe.novo.tks.dados.RepositorioEscalaMes;
import br.ufrpe.novo.tks.dados.RepositorioPessoa;
import br.ufrpe.novo.tks.exceptions.EscalaNaoEncontradaException;
import br.ufrpe.novo.tks.exceptions.UsuarioJaCadastradoException;
import br.ufrpe.novo.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.novo.tks.gui.MainAppTKS;
import br.ufrpe.novo.tks.negocios.CadastroPessoa;
import br.ufrpe.novo.tks.negocios.Servidor;
import br.ufrpe.novo.tks.negocios.beans.EscalaMes;
import br.ufrpe.novo.tks.negocios.beans.EscalaNormal;
import br.ufrpe.novo.tks.negocios.beans.Funcionario;
import br.ufrpe.novo.tks.negocios.beans.Pessoa;
import br.ufrpe.novo.tks.negocios.beans.Selecionado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FuncionarioController {
	
	private MainAppTKS mainApp;
	private Funcionario logado;
	private ObservableList<Selecionado> selecionadoData = FXCollections.observableArrayList();
	private ObservableList<String> isVoluntario;
	private ObservableList<String> escalaCadastro;
	
	IRepositorioPessoa repositorioPessoa = RepositorioPessoa.getInstance();
	RepositorioEscalaMes repEsc = RepositorioEscalaMes.getInstance();
	CadastroPessoa cadastroPessoa = new CadastroPessoa(repEsc, repositorioPessoa);
	
	@FXML
	private Label lbD01; 
	@FXML
	private Label lbD02;
	@FXML
	private Label lbD03;
	@FXML
	private Label lbD04;
	@FXML
	private Label lbD05;
	@FXML
	private Label lbD06;
	@FXML
	private Label lbD07;
	@FXML
	private Label lbD08;
	@FXML
	private Label lbD09;
	@FXML
	private Label lbD10;
	
	// CARACTERISTICAS REFERENTES A ABA DADOS CADASTRO

	@FXML
	private ChoiceBox<String> cbVoluntarioCadastro;
	@FXML
	private ChoiceBox<String> cbEscalaCadastro;
	@FXML
	private TextField tfQTDServicosCadastro;
	@FXML
	private TextField tfDiasPreferidosCadastro;
	@FXML
	private Button btSalvarCadastro;
	@FXML
	private Label lbAvisoErro;
	
	
	// CARACTERISTICAS REFERENTES A ABA BUSCAR MESES ANTERIORES
	@FXML
	private Button btConsultar;
	@FXML
	private TextField tfMesAno;
	@FXML
	private TableView<Selecionado> selecionadoTable2;
	@FXML
    private TableColumn<Selecionado, String> tcCargo2;
	@FXML
    private TableColumn<Selecionado, String> tcMatricula2;
	@FXML
    private TableColumn<Selecionado, String> tcNome2;
	@FXML
    private TableColumn<Selecionado, String> tcFuncao2;
	@FXML
    private TableColumn<Selecionado, String> tcDiasSorteados2;
	@FXML
    private TableColumn<Selecionado, String> tcTotalDias2;
	
	// CARACTERISTICAS REFERENTES A ABA ESCALA DO MES.
	@FXML
	private TableView<Selecionado> selecionadoTable;
	@FXML
    private TableColumn<Selecionado, String> tcNome;
    @FXML
    private TableColumn<Selecionado, String> tcMatricula;
    @FXML
    private TableColumn<Selecionado, String> tcCargo;
    @FXML
    private TableColumn<Selecionado, String> tcFuncao; 
    @FXML
    private TableColumn<Selecionado, String> tcDiasSorteados;
    @FXML
    private TableColumn<Selecionado, String> tcTotalDias;
    
    // BOTAO SAIR NA PARTE PRINCIPAL DO CONTROLLER
	@FXML
	private Button btSair;
	
	
	
	// CARACTERISTICAS REFERENTES A LABELS NA PARTE PRINCIPAL DO CONTROLLER.
	@FXML
	private Label lbCargo;
	@FXML
	private Label lbMatricula;
	@FXML
	private Label lbNome;
	
	
	// CONSTRUTOR
	public FuncionarioController(){
		
	}
	
	@FXML
	private void initialize() {
		this.isVoluntario = FXCollections.observableArrayList( "SIM", "NÃO");
		this.escalaCadastro = FXCollections.observableArrayList( "ADMINISTRATIVO", "1-5-9-13-17-21-25-29", "2-6-10-14-18-22-26-30", "3-7-11-15-19-23-27-31", "4-8-12-16-20-24-28");
		
		tcNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
		tcMatricula.setCellValueFactory(cellData -> cellData.getValue().getMatriculaProperty());
		tcCargo.setCellValueFactory(cellData -> cellData.getValue().getCargoProperty());
		tcFuncao.setCellValueFactory(cellData -> cellData.getValue().getFuncaoProperty());
		tcDiasSorteados.setCellValueFactory(cellData -> cellData.getValue().getDiasSorteadosProperty());
		tcTotalDias.setCellValueFactory(cellData -> cellData.getValue().getTotalDiasProperty());
	}
	
	public void setMainApp(MainAppTKS mainApp) {
		this.mainApp = mainApp;
		try {
			this.selecionadoData = mainApp.getSelecionadoData();
		} catch (EscalaNaoEncontradaException e) {
			e.printStackTrace();
		}
		finally{
			if(this.selecionadoData != null){
				selecionadoTable.setItems(this.selecionadoData);
			}
		}
		
	}
	
	
	public void setPessoa(Pessoa func){
		
		this.cbVoluntarioCadastro.setItems(isVoluntario);
		this.cbEscalaCadastro.setItems(escalaCadastro);
		this.logado = (Funcionario) func;
		if(logado.getEscalaNormal() != null){
		
			if(logado.getEscalaNormal().isVoluntario()){
				cbVoluntarioCadastro.setValue("SIM");
			}
			else{
				cbVoluntarioCadastro.setValue("NÃO");
			}
			if(logado.getEscalaNormal().getEscala() == 0){
				cbEscalaCadastro.setValue("ADMINISTRATIVO");
			}
			else if(logado.getEscalaNormal().getEscala() == 1){
				cbEscalaCadastro.setValue("1-5-9-13-17-21-25-29");
			}
			else if(logado.getEscalaNormal().getEscala() == 2){
				cbEscalaCadastro.setValue("2-6-10-14-18-22-26-30");
			}
			else if(logado.getEscalaNormal().getEscala() == 3){
				cbEscalaCadastro.setValue("3-7-11-15-19-23-27-31");
			}
			else if(logado.getEscalaNormal().getEscala() == 4){
				cbEscalaCadastro.setValue("4-8-12-16-20-24-28");
			}
		}
		
		
		this.lbCargo.setText( logado.getCargo());
		this.lbMatricula.setText(logado.getMatricula());
		this.lbNome.setText(logado.getNome());
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("MM/YYYY");
		EscalaMes escTemp = new EscalaMes();
		try {
			escTemp = Servidor.getInstance().procurarEscala(hoje.format(formatador));
		} catch (EscalaNaoEncontradaException e) {
			e.printStackTrace();
		}
		finally{
			if(escTemp != null){
				ArrayList<Selecionado> novo = escTemp.getVoluntarios();
				if(escTemp != null){
					for(int i = 0; i < novo.size(); i++){
						if(escTemp.getVoluntario(i).getMatricula().equals(logado.getMatricula())){
							int x = escTemp.getVoluntario(i).getDiasSorteados().size();
							this.lbFuncionarioDSPreencher(escTemp, x, i);
							}
					}
				}
			}
		}
		
		//this.data.addAll(novo);
		//this.SelecionadoTable.setItems(data);
	}
	
	
	// FUNÇAO PARA PREENCHER OS DIAS SORTEADOS DO MES CORRENTE DO USUARIO NO FUNCIONARIO.FXML
	// PARAM. ESCALAMES OBJETO ESCALAMES DO MES CORRENTE PARA CONSULTAR OS DIAS DO VOLUNTARIO
	// PARAM. INT X INDICANDO O TAMANHO DO ARRAY DE DIAS SORTEADOS DO VOLUNTARIO PESQUISADO
	// PARAM. INT I INDICANDO A POSIÇAO DO VOLUNTARIO DENTRO DA ESCALAMES ARRAY.
	private void lbFuncionarioDSPreencher(EscalaMes escTemp, int x, int i){
		switch(x){
		case 1: {
			lbD01.setText(escTemp.getVoluntario(i).getDiaSorteado(0));
			lbD02.setText("");
			lbD03.setText("");
			lbD04.setText("");
			lbD05.setText("");
			lbD06.setText("");
			lbD07.setText("");
			lbD08.setText("");
			lbD09.setText("");
			lbD10.setText("");
			break;
		}
		case 2: {
			lbD01.setText(escTemp.getVoluntario(i).getDiaSorteado(0));
			lbD02.setText(escTemp.getVoluntario(i).getDiaSorteado(1));
			lbD03.setText("");
			lbD04.setText("");
			lbD05.setText("");
			lbD06.setText("");
			lbD07.setText("");
			lbD08.setText("");
			lbD09.setText("");
			lbD10.setText("");
			break;
		}
		case 3: {
			lbD01.setText(escTemp.getVoluntario(i).getDiaSorteado(0));
			lbD02.setText(escTemp.getVoluntario(i).getDiaSorteado(1));
			lbD03.setText(escTemp.getVoluntario(i).getDiaSorteado(2));
			lbD04.setText("");
			lbD05.setText("");
			lbD06.setText("");
			lbD07.setText("");
			lbD08.setText("");
			lbD09.setText("");
			lbD10.setText("");
			break;
		}
		case 4: {
			lbD01.setText(escTemp.getVoluntario(i).getDiaSorteado(0));
			lbD02.setText(escTemp.getVoluntario(i).getDiaSorteado(1));
			lbD03.setText(escTemp.getVoluntario(i).getDiaSorteado(2));
			lbD04.setText(escTemp.getVoluntario(i).getDiaSorteado(3));
			lbD05.setText("");
			lbD06.setText("");
			lbD07.setText("");
			lbD08.setText("");
			lbD09.setText("");
			lbD10.setText("");
			break;
		}
		case 5: {
			lbD01.setText(escTemp.getVoluntario(i).getDiaSorteado(0));
			lbD02.setText(escTemp.getVoluntario(i).getDiaSorteado(1));
			lbD03.setText(escTemp.getVoluntario(i).getDiaSorteado(2));
			lbD04.setText(escTemp.getVoluntario(i).getDiaSorteado(3));
			lbD05.setText(escTemp.getVoluntario(i).getDiaSorteado(4));
			lbD06.setText("");
			lbD07.setText("");
			lbD08.setText("");
			lbD09.setText("");
			lbD10.setText("");
			break;
		}
		case 6: {
			lbD01.setText(escTemp.getVoluntario(i).getDiaSorteado(0));
			lbD02.setText(escTemp.getVoluntario(i).getDiaSorteado(1));
			lbD03.setText(escTemp.getVoluntario(i).getDiaSorteado(2));
			lbD04.setText(escTemp.getVoluntario(i).getDiaSorteado(3));
			lbD05.setText(escTemp.getVoluntario(i).getDiaSorteado(4));
			lbD06.setText(escTemp.getVoluntario(i).getDiaSorteado(5));
			lbD07.setText("");
			lbD08.setText("");
			lbD09.setText("");
			lbD10.setText("");
			break;
		}
		case 7: {
			lbD01.setText(escTemp.getVoluntario(i).getDiaSorteado(0));
			lbD02.setText(escTemp.getVoluntario(i).getDiaSorteado(1));
			lbD03.setText(escTemp.getVoluntario(i).getDiaSorteado(2));
			lbD04.setText(escTemp.getVoluntario(i).getDiaSorteado(3));
			lbD05.setText(escTemp.getVoluntario(i).getDiaSorteado(4));
			lbD06.setText(escTemp.getVoluntario(i).getDiaSorteado(5));
			lbD07.setText(escTemp.getVoluntario(i).getDiaSorteado(6));
			lbD08.setText("");
			lbD09.setText("");
			lbD10.setText("");
			break;
		}
		case 8: {
			lbD01.setText(escTemp.getVoluntario(i).getDiaSorteado(0));
			lbD02.setText(escTemp.getVoluntario(i).getDiaSorteado(1));
			lbD03.setText(escTemp.getVoluntario(i).getDiaSorteado(2));
			lbD04.setText(escTemp.getVoluntario(i).getDiaSorteado(3));
			lbD05.setText(escTemp.getVoluntario(i).getDiaSorteado(4));
			lbD06.setText(escTemp.getVoluntario(i).getDiaSorteado(5));
			lbD07.setText(escTemp.getVoluntario(i).getDiaSorteado(6));
			lbD08.setText(escTemp.getVoluntario(i).getDiaSorteado(7));
			lbD09.setText("");
			lbD10.setText("");
			break;
		}
		case 9: {
			lbD01.setText(escTemp.getVoluntario(i).getDiaSorteado(0));
			lbD02.setText(escTemp.getVoluntario(i).getDiaSorteado(1));
			lbD03.setText(escTemp.getVoluntario(i).getDiaSorteado(2));
			lbD04.setText(escTemp.getVoluntario(i).getDiaSorteado(3));
			lbD05.setText(escTemp.getVoluntario(i).getDiaSorteado(4));
			lbD06.setText(escTemp.getVoluntario(i).getDiaSorteado(5));
			lbD07.setText(escTemp.getVoluntario(i).getDiaSorteado(6));
			lbD08.setText(escTemp.getVoluntario(i).getDiaSorteado(7));
			lbD09.setText(escTemp.getVoluntario(i).getDiaSorteado(8));
			lbD10.setText("");
			break;
		}
		case 10: {
			lbD01.setText(escTemp.getVoluntario(i).getDiaSorteado(0));
			lbD02.setText(escTemp.getVoluntario(i).getDiaSorteado(1));
			lbD03.setText(escTemp.getVoluntario(i).getDiaSorteado(2));
			lbD04.setText(escTemp.getVoluntario(i).getDiaSorteado(3));
			lbD05.setText(escTemp.getVoluntario(i).getDiaSorteado(4));
			lbD06.setText(escTemp.getVoluntario(i).getDiaSorteado(5));
			lbD07.setText(escTemp.getVoluntario(i).getDiaSorteado(6));
			lbD08.setText(escTemp.getVoluntario(i).getDiaSorteado(7));
			lbD09.setText(escTemp.getVoluntario(i).getDiaSorteado(8));
			lbD10.setText(escTemp.getVoluntario(i).getDiaSorteado(9));
			break;
		}
		
		}
	}
	
	public ObservableList<Selecionado> getSelecionadoData(){
		return selecionadoData;
	}
	
	@FXML
	private void handleSalvar() {
		if( tfQTDServicosCadastro.getText().equals("") ){
			lbAvisoErro.setText("PREENCHA OS CAMPOS!!!");
		}
		String [] dias  = tfDiasPreferidosCadastro.getText().split(Pattern.quote(","));
		
		EscalaNormal temp = new EscalaNormal( this.EscalaNormalX(cbEscalaCadastro));
		for(int i = 0; i < dias.length; i++){

				temp.getDiasPreferidos().add(dias[i]);			
		}
		
		for(int i = 0; i < temp.getDiasPreferidos().size(); i++){
			for(int j = 0; j < temp.getDiasBloqueados().length; j++){
				if(temp.getDiaPreferido(i).equals(temp.getDiaBloqueado(j)) ){
					System.out.println(temp.getDiaPreferido(i));
					System.out.println(temp.getDiaBloqueado(j) + "Blok");
					temp.getDiasPreferidos().remove(i);
				}
			}
		}
		
		temp.setDiasQTD(Integer.parseInt(tfQTDServicosCadastro.getText()));
		
		if(this.cbVoluntarioCadastro.getValue().equals("SIM")){
			temp.setVoluntario(true);
		}
		else{
			temp.setVoluntario(false);
		}
		this.logado.setEscalaNormalFunc(temp);
		mainApp.showFuncionario(logado);
		this.initialize();
		Pessoa temporal = this.logado;
		try {
			Servidor.getInstance().remover(logado.getMatricula());
		} catch (UsuarioNaoEncontradoException e1) {
			e1.printStackTrace();
		}
		try {
			Servidor.getInstance().cadastrar(temporal);
			lbAvisoErro.setText("SALVO COM SUCESSO!!!");
		} catch (UsuarioJaCadastradoException e) {
			e.printStackTrace();
		}
	}
	
	private int EscalaNormalX(ChoiceBox<String> cbEscalaCadastro){
		int x = 0;
		if(cbEscalaCadastro.getValue().equals("ADMINISTRATIVO")){
			x = 0;
		}
		else if(cbEscalaCadastro.getValue().equals("1-5-9-13-17-21-25-29")){
			x = 1;
		}
		else if(cbEscalaCadastro.getValue().equals("2-6-10-14-18-22-26-30")){
			x = 2;
		}
		else if(cbEscalaCadastro.getValue().equals("3-7-11-15-19-23-27-31")){
			x = 3;
		}
		else if(cbEscalaCadastro.getValue().equals("4-8-12-16-20-24-28")){
			x = 4;
		}
		return x;
		
	}
	
	
	@FXML
	private void handleConsultar(){
		ObservableList<Selecionado> temporal = FXCollections.observableArrayList();
		EscalaMes temp = new EscalaMes();
		try {
			temp = Servidor.getInstance().procurarEscala(tfMesAno.getText());
		} catch (EscalaNaoEncontradaException e) {
			e.printStackTrace();
		}
		
		finally{
			if(temp != null){
				temporal.addAll(temp.getVoluntarios());
				tcNome2.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
				tcMatricula2.setCellValueFactory(cellData -> cellData.getValue().getMatriculaProperty());
				tcCargo2.setCellValueFactory(cellData -> cellData.getValue().getCargoProperty());
				tcFuncao2.setCellValueFactory(cellData -> cellData.getValue().getFuncaoProperty());
				tcDiasSorteados2.setCellValueFactory(cellData -> cellData.getValue().getDiasSorteadosProperty());
				tcTotalDias2.setCellValueFactory(cellData -> cellData.getValue().getTotalDiasProperty());
				selecionadoTable2.setItems(temporal);
			}

		}
		}
			
	
	@FXML
	private void handleLogout(){
		mainApp.showLogin();
	}

}
