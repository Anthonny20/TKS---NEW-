package br.ufrpe.novo.tks.gui.model;

import java.util.ArrayList;
import java.util.Arrays;

import br.ufrpe.novo.tks.dados.IRepositorioPessoa;
import br.ufrpe.novo.tks.dados.RepositorioEscalaMes;
import br.ufrpe.novo.tks.dados.RepositorioPessoa;
import br.ufrpe.novo.tks.exceptions.UsuarioJaCadastradoException;
import br.ufrpe.novo.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.novo.tks.gui.MainAppTKS;
import br.ufrpe.novo.tks.negocios.CadastroPessoa;
import br.ufrpe.novo.tks.negocios.Servidor;
import br.ufrpe.novo.tks.negocios.beans.Administrador;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdministradorController {

	private MainAppTKS mainApp;
	private Administrador logado;
	private ObservableList<String> admFunc;
	private ObservableList<String> motoFunc;
	private ObservableList<String> sexoList;
	private ObservableList<String> cargoList;
	private ObservableList<String> escalaList;
	private ObservableList<Pessoa> pessoaListCV = FXCollections.observableArrayList();
	private ObservableList<Funcionario> funcList = FXCollections.observableArrayList();
	
	IRepositorioPessoa repositorioPessoa = RepositorioPessoa.getInstance();
	RepositorioEscalaMes repEsc = RepositorioEscalaMes.getInstance();
	CadastroPessoa cadastroPessoa = new CadastroPessoa(repEsc, repositorioPessoa);
	
	
	//PARTE COMUM A TODAS AS ABAS DESTE CONTROLLER
	@FXML
	private Button btSair;
	@FXML
	private Label lbMatricula;
	@FXML
	private Label lbAdmNome;
	
	// PARTE DE CADASTRAMENTO / CADASTRAR PESSOAS
	
	@FXML
	private ChoiceBox<String> cbAdmFuncCP;
	@FXML
	private ChoiceBox<String> cbFuncaoCP;
	@FXML
	private ChoiceBox<String> cbSexoCP;
	@FXML
	private ChoiceBox<String> cbCargoCP;
	@FXML
	private TextField tfMatriculaCP;
	@FXML
	private TextField tfNomeCP;
	@FXML
	private TextField tfSenhaCP;
	@FXML
	private Button btCadastrarCP;
	@FXML
	private Label lbErroCP;
	@FXML
	private Label lbSucessoCP;
	
	//PARTE DE CADASTRAMENTO / REMOVER PESSOAS
	
	@FXML
	private TextField tfMatriculaRP;
	@FXML
	private Label lbRemoverRP;
	@FXML
	private Button btRemoverRP;
	
	//PARTE DE CADASTRAMENTO / ATUALIZAR PESSOAS
	@FXML
	private TextField tfMatriculaAP;
	@FXML
	private Button btPesquisarAP;
	@FXML
	private ChoiceBox<String> cbClasseAP;
	@FXML
	private TextField tfNomeAP;
	@FXML
	private ChoiceBox<String> cbSexoAP;
	@FXML
	private ChoiceBox<String> cbMotoAP;
	@FXML
	private TextField tfSenhaAP;
	@FXML
	private ChoiceBox<String> cbCargoAP;
	@FXML
	private Button btAtualizarAP;
	@FXML
	private ChoiceBox<String> cbEscalaAP;
	@FXML
	private Label lbErroAP;
	@FXML
	private Label lbSucessoAP;
	
	//PARTE DE CONFIGURAR VOLUNTARIOS / 
	
	@FXML
	private TableView tvFuncCV;
	@FXML
	private TableColumn tcCargoCV;
	@FXML
	private TableColumn tcMatriculaCV;
	@FXML
	private TableColumn tcNomeCV;
	@FXML
	private TableColumn tcEscalaCV;
	
	//PARTE BUSCAR MESES ANTERIORES / 
	
	@FXML
	private TextField tfMesAnoBMA;
	@FXML
	private Button btConsultarBMA;
	@FXML
	private Button btDeletarEscalaBMA;
	@FXML
	private PasswordField pfSenhaDeletarBMA;
	@FXML
	private Label lbErroDeletarBMA;
	@FXML
	private Label lbDataInexistenteBMA;
	@FXML
	private TableView<Selecionado> tvEscolhidoBMA;
	@FXML
	private TableColumn<Selecionado, String> tcCargoBMA;
	@FXML
	private TableColumn<Selecionado, String> tcMatriculaBMA;
	@FXML
	private TableColumn<Selecionado, String> tcNomeBMA;
	@FXML
	private TableColumn<Selecionado, String> tcFuncaoBMA;
	@FXML
	private TableColumn<Selecionado, String> tcDiasBMA;
	@FXML
	private TableColumn<Selecionado, String> tcTotalBMA;
	
	//
	
	public AdministradorController(){
		
	}
	
	@FXML
	public void initialize(){
		admFunc = FXCollections.observableArrayList( "ADMINISTRADOR", "FUNCIONARIO");
		motoFunc = FXCollections.observableArrayList( "MOTORISTA", "COMANDANTE");
		sexoList = FXCollections.observableArrayList( "MASCULINO", "FEMININO");
		cargoList = FXCollections.observableArrayList( "SOLDADO", "CABO", "3º SARGENTO", "2º SARGENTO",
				"1º SARGENTO", "SUB-TENENTE", "2º TENENTE", "1º TENENTE", "CAPITÃO", "MAJOR", "TENENTE CORONEL", "CORONEL");
		escalaList = FXCollections.observableArrayList( "ADMINISTRATIVO", "1-5-9-13-17-21-25-29", "2-6-10-14-18-22-26-30", "3-7-11-15-19-23-27-31", "4-8-12-16-20-24-28");

	}
	
	public void setMainApp(MainAppTKS mainApp) {
		this.mainApp = mainApp;
		this.pessoaListCV = mainApp.getPessoaList();
	}

	@FXML
	public void setPessoa(Administrador func) {
		
		this.logado = func;
		this.lbMatricula.setText(logado.getMatricula());
		this.lbAdmNome.setText(logado.getNome());
		this.cbAdmFuncCP.setItems(admFunc);
		this.cbFuncaoCP.setItems(motoFunc);
		this.cbSexoCP.setItems(sexoList);
		this.cbCargoCP.setItems(cargoList);
		this.lbErroCP.setText("");
		this.lbSucessoCP.setText("");
		this.lbRemoverRP.setText("");
		this.cbClasseAP.setItems(admFunc);
		this.cbMotoAP.setItems(motoFunc);
		this.cbCargoAP.setItems(cargoList);
		this.cbSexoAP.setItems(sexoList);
		this.cbEscalaAP.setItems(escalaList);
		this.lbSucessoAP.setText("");
		this.lbErroAP.setText("");
		this.lbErroDeletarBMA.setText("");
		this.lbDataInexistenteBMA.setText("");
//		this.funcList.addAll(this.createFuncList());
//		System.out.println(funcList.toString());
	}
	
	@FXML
	private void handleCadastrarCP(){
		if(this.tfMatriculaCP.getText().equals("") || this.tfNomeCP.getText().equals("") || this.tfSenhaCP.getText().equals("")){
			lbErroCP.setText("CAMPO NÃO PREENCHIDO!!!");
		}
		else{
			this.lbErroCP.setText("");
			this.lbSucessoCP.setText("");
		if(cbAdmFuncCP.getValue().equals("ADMINISTRADOR")){
			Pessoa temp = new Administrador(tfNomeCP.getText(), cbSexoCP.getValue().charAt(0), tfMatriculaCP.getText(), tfSenhaCP.getText());
			try {
				Servidor.getInstance().cadastrar(temp);
				lbSucessoCP.setText("CADASTRADO COM SUCESSO!!!");
			} catch (UsuarioJaCadastradoException e) {
				lbErroCP.setText(e.getMessage());
				e.printStackTrace();
			}
			
		}
		else if(cbAdmFuncCP.getValue().equals("FUNCIONARIO")){
			boolean moto = false;
			if(cbFuncaoCP.getValue().equals("MOTORISTA")){
				moto = true;
			}
			EscalaNormal nova = new EscalaNormal(0, 4);
			Pessoa temp = new Funcionario(tfNomeCP.getText(), cbCargoCP.getValue(), cbSexoCP.getValue().charAt(0),
					tfMatriculaCP.getText(), moto, tfSenhaCP.getText());
			((Funcionario)temp).setEscalaNormalFunc(nova);
			try {
				Servidor.getInstance().cadastrar(temp);
				lbSucessoCP.setText("CADASTRADO COM SUCESSO!!!");
			} catch (UsuarioJaCadastradoException e) {
				lbErroCP.setText(e.getMessage());
				e.printStackTrace();
			}
			
		}
		}
		
	}
	
	@FXML
	private void handlePesquisarAP(){
		try {
			Pessoa logadoTemp = Servidor.getInstance().procurar(this.tfMatriculaAP.getText());
			if(logadoTemp instanceof Administrador){
				this.cbClasseAP.setValue("ADMINISTRADOR");
				if(logadoTemp.getSexo() == 'M'){
					this.cbSexoAP.setValue("MASCULINO");
				}
				else{
					this.cbSexoAP.setValue("FEMININO");
				}
				this.cbEscalaAP.setItems(null);
				this.cbMotoAP.setItems(null);
				this.cbCargoAP.setItems(null);
			}
			if(logadoTemp instanceof Funcionario){
				this.cbEscalaAP.setItems(escalaList);
				this.cbMotoAP.setItems(motoFunc);
				this.cbCargoAP.setItems(cargoList);
				this.cbClasseAP.setValue("FUNCIONARIO");
				if(logadoTemp.getSexo() == 'M'){
					this.cbSexoAP.setValue("MASCULINO");
				}
				else{
					this.cbSexoAP.setValue("FEMININO");
				}
				
				if(((Funcionario)logadoTemp).getEscalaNormal().getEscala() == 0){
					cbEscalaAP.setValue("ADMINISTRATIVO");
				}
				else if(((Funcionario)logadoTemp).getEscalaNormal().getEscala() == 1){
					cbEscalaAP.setValue("1-5-9-13-17-21-25-29");
				}
				else if(((Funcionario)logadoTemp).getEscalaNormal().getEscala() == 2){
					cbEscalaAP.setValue("2-6-10-14-18-22-26-30");
				}
				else if(((Funcionario)logadoTemp).getEscalaNormal().getEscala() == 3){
					cbEscalaAP.setValue("3-7-11-15-19-23-27-31");
				}
				else if(((Funcionario)logadoTemp).getEscalaNormal().getEscala() == 4){
					cbEscalaAP.setValue("4-8-12-16-20-24-28");
				}
				if(((Funcionario) logadoTemp).isMotorista()){
					this.cbMotoAP.setValue("MOTORISTA");
				}
				else{
					this.cbMotoAP.setValue("COMANDANTE");
				}
				
				this.cbCargoAP.setValue(((Funcionario) logadoTemp).getCargo());
			}
		} catch (UsuarioNaoEncontradoException e) {
			lbErroAP.setText(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleAtualizarAP(){
		this.lbErroAP.setText("");
		if(this.cbClasseAP.getValue().equals("ADMINISTRADOR")){
			Pessoa temporal = new Administrador(tfNomeAP.getText(), cbSexoAP.getValue().charAt(0), tfMatriculaAP.getText(), tfSenhaAP.getText());
			try {
				Servidor.getInstance().remover(temporal.getMatricula());
			} catch (UsuarioNaoEncontradoException e) {
				this.lbErroAP.setText(e.getMessage());
				e.printStackTrace();
			}
			try {
				Servidor.getInstance().cadastrar(temporal);
				this.lbSucessoAP.setText("ATUALIZADO COM SUCESSO!!!");
			} catch (UsuarioJaCadastradoException e) {
				this.lbErroAP.setText(e.getMessage());
				e.printStackTrace();
			}
		}
		else if(this.cbClasseAP.getValue().equals("FUNCIONARIO")){
			boolean moto = false;
			int escala = 0;
			
			if(this.cbEscalaAP.getValue().equals("ADMINISTRATIVO")){
				escala = 0;
			}
			else if(this.cbEscalaAP.getValue().equals("1-5-9-13-17-21-25-29")){
				escala = 1;
			}
			else if(this.cbEscalaAP.getValue().equals("2-6-10-14-18-22-26-30")){
				escala = 2;
			}
			else if(this.cbEscalaAP.getValue().equals("3-7-11-15-19-23-27-31")){
				escala = 3;
			}
			else if(this.cbEscalaAP.getValue().equals("4-8-12-16-20-24-28")){
				escala = 4;
			}
			
			EscalaNormal nova = new EscalaNormal(escala, 4);
			if(this.cbMotoAP.getValue().equals("MOTORISTA")){
				moto = true;
			}
			Pessoa temporal = new Funcionario(tfNomeAP.getText(), cbCargoAP.getValue(), cbSexoAP.getValue().charAt(0), tfMatriculaAP.getText(), moto, tfSenhaAP.getText() );
			((Funcionario) temporal).setEscalaNormalFunc(nova);
			try {
				Servidor.getInstance().remover(tfMatriculaAP.getText());
			} catch (UsuarioNaoEncontradoException e) {
				lbErroAP.setText(e.getMessage());
				e.printStackTrace();
			}
			
			try {
				Servidor.getInstance().cadastrar(temporal);
				lbSucessoAP.setText("ATUALIZADO COM SUCESSO!!!");
			} catch (UsuarioJaCadastradoException e) {
				lbErroAP.setText(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
	}
	
	private ObservableList<Funcionario> createFuncList(){
		ObservableList<Funcionario> resultado = FXCollections.observableArrayList();
		
		//ja fiz de tudo e não roda
		Pessoa [] lista = new Pessoa [this.funcList.size()];
		for(int i = 0; i < lista.length; i++){
			Pessoa temp = pessoaListCV.get(i);
			lista[i] = temp;
		}
		
		
		ArrayList<Pessoa> nova = new ArrayList<Pessoa>();
		for(int i = 0; i < lista.length; i++){
			nova.add(lista[i]);
		}
		for(Pessoa p : nova){
			if(p instanceof Administrador){
				nova.remove(p);
			}
		}
		ArrayList<Funcionario> temp = new ArrayList<Funcionario>();
		for(Pessoa p : nova){
			if(p instanceof Funcionario){
				temp.add((Funcionario)p);
			}
		}
		resultado.addAll(temp);
		return resultado;
	}
 	
	@FXML
	private void handleRemover(){
		
		try {
			Servidor.getInstance().remover(tfMatriculaRP.getText());
			this.lbRemoverRP.setText("MATRICULA: " + tfMatriculaRP.getText() + " REMOVIDA COM SUCESSO!!!");
		} catch (UsuarioNaoEncontradoException e) {
			lbRemoverRP.setText(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleConsultarBMA(){
		
		this.lbDataInexistenteBMA.setText("");
		this.tvEscolhidoBMA.setItems(null);
		
		ObservableList<Selecionado> temporal = FXCollections.observableArrayList();
		if((Servidor.getInstance().procurarEscala(this.tfMesAnoBMA.getText())) == null){
			this.lbDataInexistenteBMA.setText("DATA INEXISTENTE");
		}
		else{
			
			EscalaMes temp = Servidor.getInstance().procurarEscala(tfMesAnoBMA.getText());
			temporal.addAll(temp.getVoluntarios());
			tcNomeBMA.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
			tcMatriculaBMA.setCellValueFactory(cellData -> cellData.getValue().getMatriculaProperty());
			tcCargoBMA.setCellValueFactory(cellData -> cellData.getValue().getCargoProperty());
			tcFuncaoBMA.setCellValueFactory(cellData -> cellData.getValue().getFuncaoProperty());
			tcDiasBMA.setCellValueFactory(cellData -> cellData.getValue().getDiasSorteadosProperty());
			tcTotalBMA.setCellValueFactory(cellData -> cellData.getValue().getTotalDiasProperty());
			this.tvEscolhidoBMA.setItems(temporal);
		}
		
	}
	
	@FXML
	private void handleRemoverEscalaBMA(){
		this.lbErroDeletarBMA.setText("");
		if(this.pfSenhaDeletarBMA.getText().equals("")){
			this.lbErroDeletarBMA.setText("INSIRA A SENHA!!!");
		}
		else if( !(this.pfSenhaDeletarBMA.getText().equals(this.logado.getSenha())) ){
			this.lbErroDeletarBMA.setText("SENHA INCORRETA!!!");
		}
		
		else if(this.pfSenhaDeletarBMA.getText().equals(this.logado.getSenha())){
			if(this.tfMesAnoBMA.getText().equals("")){
				this.lbErroDeletarBMA.setText("INSIRA A DATA!!!");
			}
			else if(Servidor.getInstance().procurarEscala(this.tfMesAnoBMA.getText()) == null){
				this.lbErroDeletarBMA.setText("DATA INEXISTENTE!!!");
			}
			else{
				Servidor.getInstance().removerEscala(Servidor.getInstance().procurarEscala(this.tfMesAnoBMA.getText()));
				this.lbErroDeletarBMA.setText("ESCALA REMOVIDA!!!");
			}
		}
	}
	
	
	@FXML
	private void handleSair(){
		mainApp.showLogin();
	}

}
