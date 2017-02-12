package br.ufrpe.novo.tks.gui.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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
	private ObservableList<Pessoa> funcList = FXCollections.observableArrayList();
	private ObservableList<Selecionado> selecaoList = FXCollections.observableArrayList();
	private ObservableList<Pessoa> usuarios = FXCollections.observableArrayList();
	private int countStatus = 0;
	
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
	@FXML
	private Label lbTesteDrag;
	
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
	private TableView<Pessoa> tvFuncCV;
	@FXML
	private TableColumn<Funcionario, String> tcCargoCV;
	@FXML
	private TableColumn<Pessoa, String> tcMatriculaCV;
	@FXML
	private TableColumn<Pessoa, String> tcNomeCV;
	@FXML
	private TableColumn<Funcionario, Integer> tcEscalaCV;
	@FXML
	private TableView<Pessoa> tvTransfCV;
	@FXML
	private TableColumn<Pessoa, String> tcMatConfCV;
	@FXML
	private TableColumn<Pessoa, String> tcNomeConfCV;
	@FXML
	private TableColumn<Pessoa, Integer> tcEscalaConfCV;
	@FXML
	private TableColumn<Pessoa, String> tcPrefCV;
	@FXML
	private Button btAddPrefCV;
	@FXML
	private CheckBox checkCV = new CheckBox("VOLUNTARIOS ON");
	@FXML
	private TextField tfMatCV;
	@FXML
	private TextField tfDiasCV;
	@FXML
	private Label lb01CV;
	@FXML
	private Label lb02CV;
	@FXML
	private Label lb03CV;
	@FXML
	private Label lb04CV;
	@FXML
	private Label lbEXPCV;
	@FXML
	private Button btConfigurarTudoCV;
	
	//PARTE SORTEIO
	
	@FXML
	private TableView<Pessoa> tvSorteio;
	@FXML
	private TableColumn<Pessoa, String> tcMatSorteio;
	@FXML
	private TableColumn<Pessoa, String> tcNomeSorteio;
	@FXML
	private TableColumn<Pessoa, String> tcFuncSorteio;
	@FXML
	private TableColumn<Pessoa, String> tcEscSorteio;
	@FXML
	private TableColumn<Pessoa, String> tcPrefSorteio;
	@FXML
	private TableView<Selecionado> tvSorteioFinal;
	@FXML
	private TableColumn<Selecionado, String> tcNomeFinal;
	@FXML
	private TableColumn<Selecionado, String> tcFuncFinal;
	@FXML
	private TableColumn<Selecionado, String> tcSorteadosFinal;
	@FXML
	private TableColumn<Selecionado, String> tcQTDFinal;
	@FXML
	private TableColumn<Selecionado, String> tcPrefFinal;
	@FXML
	private TextField tf01Sorteio;
	@FXML
	private TextField tf02Sorteio;
	@FXML
	private TextField tf03Sorteio;
	@FXML
	private TextField tf04Sorteio;
	@FXML
	private TextField tf05Sorteio;
	@FXML
	private TextField tf06Sorteio;
	@FXML
	private TextField tf07Sorteio;
	@FXML
	private TextField tf08Sorteio;
	@FXML
	private TextField tf09Sorteio;
	@FXML
	private TextField tf10Sorteio;
	@FXML
	private DatePicker dpSorteio;
	@FXML
	private Button btLimparTextoSorteio;
	@FXML
	private Button btStatusSorteio;
	@FXML
	private Button btFinalizarSorteio;
	@FXML
	private Button btDeletarFinal;
	@FXML
	private Button btADDFinal;
	
	
	
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
	
	// PARTE USUÁRIOS.
	
	@FXML
	private TableView<Pessoa> tvUsuarios;
	@FXML
	private TableColumn<Pessoa, String> tcMatUsuarios;
	@FXML
	private TableColumn<Pessoa, String> tcNomeUsuarios;
	
	
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
		
		tcEscalaConfCV.setCellValueFactory(cellData -> ((Funcionario) cellData.getValue()).getEscalaProperty().asObject());
		tcNomeConfCV.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
		tcMatConfCV.setCellValueFactory(cellData -> cellData.getValue().getMatriculaProperty());
		tcCargoCV.setCellValueFactory(cellData -> ((Funcionario) cellData.getValue()).getCargoProperty());
		
		tcMatriculaCV.setCellValueFactory(cellData -> cellData.getValue().getMatriculaProperty());
		tcNomeCV.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
		tcEscalaCV.setCellValueFactory(cellData -> ((Funcionario) cellData.getValue()).getEscalaProperty().asObject());
		tcPrefCV.setCellValueFactory(cellData -> ((Funcionario) cellData.getValue()).getPreferidosFuncProperty());
		
		tcMatSorteio.setCellValueFactory(cellData -> cellData.getValue().getMatriculaProperty());
		tcNomeSorteio.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
		tcFuncSorteio.setCellValueFactory(cellData -> ((Funcionario) cellData.getValue()).motoristaProperty());
		tcEscSorteio.setCellValueFactory(cellData -> ((Funcionario) cellData.getValue()).getStringPropertyEscala());
		tcPrefSorteio.setCellValueFactory(cellData -> ((Funcionario) cellData.getValue()).getPreferidosFuncProperty());
		
		tcNomeFinal.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
		tcFuncFinal.setCellValueFactory(cellData ->  cellData.getValue().getCargoProperty());
		tcSorteadosFinal.setCellValueFactory(cellData -> cellData.getValue().getDiasSorteadosProperty());
		tcQTDFinal.setCellValueFactory(cellData -> cellData.getValue().getTotalDiasProperty());
		tcPrefFinal.setCellValueFactory(cellData -> cellData.getValue().getSelecionado().getPreferidosFuncProperty());
		
		tcMatUsuarios.setCellValueFactory(cellData -> cellData.getValue().getMatriculaProperty());
		tcNomeUsuarios.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
		
	}
	
	public void setMainApp(MainAppTKS mainApp) {
		this.mainApp = mainApp;
		this.funcList = mainApp.getFuncList();
		Collections.sort(funcList);
		this.tvFuncCV.setItems(funcList);
		Collections.sort(pessoaListCV);
		this.tvTransfCV.setItems(pessoaListCV);
		this.tvSorteioFinal.setItems(selecaoList);
		mainApp.dragDropCV(tvFuncCV, lb01CV, tvTransfCV, 1);
		mainApp.dragDropCV(tvFuncCV, lb02CV, tvTransfCV, 2);
		mainApp.dragDropCV(tvFuncCV, lb03CV, tvTransfCV, 3);
		mainApp.dragDropCV(tvFuncCV, lb04CV, tvTransfCV, 4);
		mainApp.dragDropCV(tvFuncCV, lbEXPCV, tvTransfCV, 0);
		mainApp.dragDropSorteio(tvSorteio, tf01Sorteio);
		mainApp.dragDropSorteio(tvSorteio, tf02Sorteio);
		mainApp.dragDropSorteio(tvSorteio, tf03Sorteio);
		mainApp.dragDropSorteio(tvSorteio, tf04Sorteio);
		mainApp.dragDropSorteio(tvSorteio, tf05Sorteio);
		mainApp.dragDropSorteio(tvSorteio, tf06Sorteio);
		mainApp.dragDropSorteio(tvSorteio, tf07Sorteio);
		mainApp.dragDropSorteio(tvSorteio, tf08Sorteio);
		mainApp.dragDropSorteio(tvSorteio, tf09Sorteio);
		mainApp.dragDropSorteio(tvSorteio, tf10Sorteio);

		mainApp.dragDropSorteio(tvFuncCV, tfMatCV);
		
		dpSorteio.setValue(LocalDate.now());
		this.carregarTableViewSorteio();
		usuarios = mainApp.getPessoaList();
		this.tvUsuarios.setItems(usuarios);
		
	}

	
	public void setPessoa(Administrador func) {
		
		this.logado = func;
		this.pessoaListCV = mainApp.getViewCV();
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
			EscalaNormal nova = new EscalaNormal(0);
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
		this.funcList = mainApp.getFuncList();
		Collections.sort(funcList);
		tvFuncCV.setItems(funcList);
		tvFuncCV.getColumns().get(0).setVisible(false);
		tvFuncCV.getColumns().get(0).setVisible(true);
		
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
				this.cbEscalaAP.setItems(escalaList);
				this.cbMotoAP.setItems(motoFunc);
				this.cbCargoAP.setItems(cargoList);
				tfNomeAP.setText(logadoTemp.getNome());
				
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
			Pessoa temporal = null;
			try {
				temporal = Servidor.getInstance().procurar(tfMatriculaAP.getText());
			} catch (UsuarioNaoEncontradoException e1) {
				this.lbErroAP.setText(e1.getMessage());
				e1.printStackTrace();
			}
			finally{
				if(temporal != null){
					int index = -1;
					List<Pessoa> listaR = Servidor.getInstance().getPessoas();
					for(int i = 0; i < listaR.size(); i++){
						if(temporal.getMatricula().equals(listaR.get(i).getMatricula())){
							index = i;
						}
					}
					if(index > 0){
						Servidor.getInstance().remover(index);
						temporal = new Administrador(tfNomeAP.getText(), cbSexoAP.getValue().charAt(0), tfMatriculaAP.getText(), tfSenhaAP.getText());
						try {
							Servidor.getInstance().cadastrar(temporal);
							lbSucessoAP.setText("ATUALIZADO COM SUCESSO!!!");
						} catch (UsuarioJaCadastradoException e) {
							this.lbErroAP.setText(e.getMessage());
							e.printStackTrace();
						}
						}
				}
				else{
					try {
						temporal = new Administrador(tfNomeAP.getText(), cbSexoAP.getValue().charAt(0), tfMatriculaAP.getText(), tfSenhaAP.getText());
						Servidor.getInstance().cadastrar(temporal);
						lbSucessoAP.setText("ATUALIZADO COM SUCESSO!!!");
					} catch (UsuarioJaCadastradoException e) {
						this.lbErroAP.setText(e.getMessage());
						e.printStackTrace();
					}
				}
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
			
			EscalaNormal nova = new EscalaNormal(escala);
			if(this.cbMotoAP.getValue().equals("MOTORISTA")){
				moto = true;
			}
			
			Pessoa temporal = null;
			try {
				temporal = Servidor.getInstance().procurar(tfMatriculaAP.getText());
			} catch (UsuarioNaoEncontradoException e1) {
				this.lbErroAP.setText(e1.getMessage());
				e1.printStackTrace();
			}
			finally{
				if(temporal != null){
					int index = -1;
					List<Pessoa> listaR = Servidor.getInstance().getPessoas();
					for(int i = 0; i < listaR.size(); i++){
						if(temporal.getMatricula().equals(listaR.get(i).getMatricula())){
							index = i;
						}
					}
					if(index > 0){
						Servidor.getInstance().remover(index);
						temporal = new Funcionario(tfNomeAP.getText(), cbCargoAP.getValue(), cbSexoAP.getValue().charAt(0), tfMatriculaAP.getText(), moto, tfSenhaAP.getText() );
						((Funcionario) temporal).setEscalaNormalFunc(nova);
						
						try {
							Servidor.getInstance().cadastrar(temporal);
							lbSucessoAP.setText("ATUALIZADO COM SUCESSO!!!");
						} catch (UsuarioJaCadastradoException e) {
							this.lbErroAP.setText(e.getMessage());
							e.printStackTrace();
						}
						}
				}
				else{
					try {
						temporal = new Funcionario(tfNomeAP.getText(), cbCargoAP.getValue(), cbSexoAP.getValue().charAt(0), tfMatriculaAP.getText(), moto, tfSenhaAP.getText() );
						((Funcionario) temporal).setEscalaNormalFunc(nova);						
						Servidor.getInstance().cadastrar(temporal);
						lbSucessoAP.setText("ATUALIZADO COM SUCESSO!!!");
					} catch (UsuarioJaCadastradoException e) {
						this.lbErroAP.setText(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
		this.funcList = mainApp.getFuncList();
		Collections.sort(funcList);
		tvFuncCV.setItems(funcList);
		tvFuncCV.refresh();
		
	}
	
	public void handleAddPrefCV(){
		Pessoa temp = new Funcionario("","", 'M',"", false, "");
		
		if(tfMatCV.getText().length() != 0){
			try {
				temp = Servidor.getInstance().procurar(tfMatCV.getText());
			} catch (UsuarioNaoEncontradoException e) {
				e.printStackTrace();
			}
			if(((Funcionario)temp).getEscalaNormal() == null){
				EscalaNormal nova = new EscalaNormal(0);
				nova.setDiasSorteados(0);
				((Funcionario)temp).setEscalaNormalFunc(nova);
			}
			
			((Funcionario)temp).getEscalaNormal().setDiasSorteados(((Funcionario)temp).getEscalaNormal().getEscala());
			((Funcionario)temp).getEscalaNormal().setVoluntario(this.checkCV.isSelected());
			((Funcionario)temp).getEscalaNormal().getDiasPreferidos().clear();
			
			String [] dias  = tfDiasCV.getText().split(Pattern.quote(","));
					
			for(int i = 0; i < dias.length; i++){
					((Funcionario)temp).getEscalaNormal().getDiasPreferidos().add(dias[i]);
				
			}
						
			for(int i = 0; i < ((Funcionario)temp).getEscalaNormal().getDiasPreferidos().size(); i++){
				for(int j = 0; j < ((Funcionario)temp).getEscalaNormal().getDiasBloqueados().length; j++){
					if(((Funcionario)temp).getEscalaNormal().getDiaPreferido(i).equals("" + ((Funcionario)temp).getEscalaNormal().getDiaBloqueado(j)) ){
						((Funcionario)temp).getEscalaNormal().getDiasPreferidos().remove(i);
					}
				}
			}
		}
		
		try {
			Servidor.getInstance().remover(temp.getMatricula());
		} catch (UsuarioNaoEncontradoException e) {
			e.printStackTrace();
		}
		
		try {
			Servidor.getInstance().cadastrar(temp);
		} catch (UsuarioJaCadastradoException e) {
			e.printStackTrace();
		}
		this.funcList = mainApp.getFuncList();
		Collections.sort(funcList);
		this.tvFuncCV.setItems(funcList);
		mainApp.addListaTempView(temp.getMatricula(), ((Funcionario)temp).getEscalaNormal().getEscala() );
		tvTransfCV.setItems(mainApp.getViewCV());
		tfDiasCV.setText("");
		tfMatCV.setText("");
		
	}
	
	public void handleConfigurarTudo(){
		ObservableList<Pessoa> atualizar = mainApp.getViewCV();
		if(atualizar != null){
			for(Pessoa x : atualizar){
				try {
					Servidor.getInstance().remover(x.getMatricula());
					((Funcionario)x).getEscalaNormal().setVoluntario(this.checkCV.isSelected());
				} catch (UsuarioNaoEncontradoException e) {
					
					e.printStackTrace();
				}
				
				try {
					Servidor.getInstance().cadastrar(x);
				} catch (UsuarioJaCadastradoException e) {
				
					e.printStackTrace();
				}
			}
		}
		mainApp.clearViewCV();
		this.funcList = mainApp.getFuncList();
		Collections.sort(funcList);
		tvFuncCV.setItems(funcList);
		tvFuncCV.refresh();
		this.carregarTableViewSorteio();
	}
	
	private void removerIguais(String mat1, String mat2, TextField tfSorteio){
		if(mat1 != null && mat2 != null && tfSorteio != null){
			if(mat1.equals(mat2)){
				tfSorteio.setText("");
			}
		}
	}
	

	
	//CRIAR FUNCAO PARA ATUALIZAR OS BOTOES ADD E DELETAR DE SORTEIO ONDE ATUALIZE A TABELA
	//TVSORTEIO CASO O ATRIBUTO FIQUE DE TAMANHO IGUAL AO DIAS QTD DA TABELA CONCORRENTE
	
	private void removerIguaisTotal(){
		removerIguais(tf01Sorteio.getText(), tf02Sorteio.getText(), tf02Sorteio);
		removerIguais(tf01Sorteio.getText(), tf03Sorteio.getText(), tf03Sorteio);
		removerIguais(tf01Sorteio.getText(), tf04Sorteio.getText(), tf04Sorteio);
		removerIguais(tf01Sorteio.getText(), tf05Sorteio.getText(), tf05Sorteio);
		removerIguais(tf01Sorteio.getText(), tf06Sorteio.getText(), tf06Sorteio);
		removerIguais(tf01Sorteio.getText(), tf07Sorteio.getText(), tf07Sorteio);
		removerIguais(tf01Sorteio.getText(), tf08Sorteio.getText(), tf08Sorteio);
		removerIguais(tf01Sorteio.getText(), tf09Sorteio.getText(), tf09Sorteio);
		removerIguais(tf01Sorteio.getText(), tf10Sorteio.getText(), tf10Sorteio);
		removerIguais(tf02Sorteio.getText(), tf03Sorteio.getText(), tf03Sorteio);
		removerIguais(tf02Sorteio.getText(), tf04Sorteio.getText(), tf04Sorteio);
		removerIguais(tf02Sorteio.getText(), tf05Sorteio.getText(), tf05Sorteio);
		removerIguais(tf02Sorteio.getText(), tf06Sorteio.getText(), tf06Sorteio);
		removerIguais(tf02Sorteio.getText(), tf07Sorteio.getText(), tf07Sorteio);
		removerIguais(tf02Sorteio.getText(), tf08Sorteio.getText(), tf08Sorteio);
		removerIguais(tf02Sorteio.getText(), tf09Sorteio.getText(), tf09Sorteio);
		removerIguais(tf02Sorteio.getText(), tf10Sorteio.getText(), tf10Sorteio);
		removerIguais(tf03Sorteio.getText(), tf04Sorteio.getText(), tf04Sorteio);
		removerIguais(tf03Sorteio.getText(), tf05Sorteio.getText(), tf05Sorteio);
		removerIguais(tf03Sorteio.getText(), tf06Sorteio.getText(), tf06Sorteio);
		removerIguais(tf03Sorteio.getText(), tf07Sorteio.getText(), tf07Sorteio);
		removerIguais(tf03Sorteio.getText(), tf08Sorteio.getText(), tf08Sorteio);
		removerIguais(tf03Sorteio.getText(), tf09Sorteio.getText(), tf09Sorteio);
		removerIguais(tf03Sorteio.getText(), tf10Sorteio.getText(), tf10Sorteio);
		removerIguais(tf04Sorteio.getText(), tf05Sorteio.getText(), tf05Sorteio);
		removerIguais(tf04Sorteio.getText(), tf06Sorteio.getText(), tf06Sorteio);
		removerIguais(tf04Sorteio.getText(), tf07Sorteio.getText(), tf07Sorteio);
		removerIguais(tf04Sorteio.getText(), tf08Sorteio.getText(), tf08Sorteio);
		removerIguais(tf04Sorteio.getText(), tf09Sorteio.getText(), tf09Sorteio);
		removerIguais(tf04Sorteio.getText(), tf10Sorteio.getText(), tf10Sorteio);
		removerIguais(tf05Sorteio.getText(), tf06Sorteio.getText(), tf06Sorteio);
		removerIguais(tf05Sorteio.getText(), tf07Sorteio.getText(), tf07Sorteio);
		removerIguais(tf05Sorteio.getText(), tf08Sorteio.getText(), tf08Sorteio);
		removerIguais(tf05Sorteio.getText(), tf09Sorteio.getText(), tf09Sorteio);
		removerIguais(tf05Sorteio.getText(), tf10Sorteio.getText(), tf10Sorteio);
		removerIguais(tf06Sorteio.getText(), tf07Sorteio.getText(), tf07Sorteio);
		removerIguais(tf06Sorteio.getText(), tf08Sorteio.getText(), tf08Sorteio);
		removerIguais(tf06Sorteio.getText(), tf09Sorteio.getText(), tf09Sorteio);
		removerIguais(tf06Sorteio.getText(), tf10Sorteio.getText(), tf10Sorteio);
		removerIguais(tf07Sorteio.getText(), tf08Sorteio.getText(), tf08Sorteio);
		removerIguais(tf07Sorteio.getText(), tf09Sorteio.getText(), tf09Sorteio);
		removerIguais(tf07Sorteio.getText(), tf10Sorteio.getText(), tf10Sorteio);
		removerIguais(tf08Sorteio.getText(), tf09Sorteio.getText(), tf09Sorteio);
		removerIguais(tf08Sorteio.getText(), tf10Sorteio.getText(), tf10Sorteio);
		removerIguais(tf09Sorteio.getText(), tf10Sorteio.getText(), tf10Sorteio);
		
	}
	
	private boolean validarSorteio(ArrayList<String> lista){
		boolean resultado = false;
		ArrayList<Funcionario> listF = new ArrayList<Funcionario>();
		Pessoa temp = new Pessoa("", 'M', "", "");
		int countMot = 0;
		int countFem = 0;
		if(selecaoList.size() != 0){
			for(Selecionado zero : selecaoList){
				for(String st : zero.getDiasSorteados()){
					if(st.equals(dpSorteio.getValue().toString().substring(dpSorteio.getValue().toString().length() - 2))){
						listF.add(zero.getFunc());
					}
				}
			}
		}
		
		for(Funcionario aa : listF){
			if(aa.isMotorista()){
				countMot++;
			}
			if(aa.getSexo() == 'F'){
				countFem++;
			}
		}
		
		for(String x : lista){
			try {
				temp = Servidor.getInstance().procurar(x);
			} catch (UsuarioNaoEncontradoException e) {
				
				e.printStackTrace();
			}
			if(temp instanceof Funcionario){
				if(((Funcionario)temp).isMotorista()){
					countMot++;
				}
				if(temp.getSexo() == 'F'){
					countFem++;
				}
			}
		}
		if(countMot > 1 && countFem <= ((lista.size() + listF.size()) / 2) && (lista.size() + listF.size()) > 3){
			resultado = true;
		}
		return resultado;
	}
	
	public void carregarTableViewSorteio(){
		
		List<Pessoa> listaCompleta = new ArrayList<Pessoa>();
		ObservableList<Pessoa> resultado = FXCollections.observableArrayList();
		ArrayList<Pessoa> esc1 = new ArrayList<Pessoa>();
		ArrayList<Pessoa> esc2 = new ArrayList<Pessoa>();
		ArrayList<Pessoa> esc3 = new ArrayList<Pessoa>();
		ArrayList<Pessoa> esc4 = new ArrayList<Pessoa>();
		ArrayList<Pessoa> esc0 = new ArrayList<Pessoa>();
		
		listaCompleta = Servidor.getInstance().getFuncionariosList();
		for(Pessoa temp : listaCompleta){
			if(temp instanceof Funcionario){
				
				if(((Funcionario) temp).getEscalaNormal() == null){
					EscalaNormal nova = new EscalaNormal(100);
					((Funcionario)temp).setEscalaNormalFunc(nova);
				}
				
				if(((Funcionario)temp).getEscalaNormal().isVoluntario()){
					
					if(((Funcionario)temp).getEscalaNormal().getEscala() == 1){
						esc1.add(temp);
		
					}
					if(((Funcionario)temp).getEscalaNormal().getEscala() == 2 ){
						esc2.add(temp);
		
					}
					if(((Funcionario)temp).getEscalaNormal().getEscala() == 3 ){
						esc3.add(temp);
						
					}
					if(((Funcionario)temp).getEscalaNormal().getEscala() == 4 ){
						esc4.add(temp);
						
					}
					if(((Funcionario)temp).getEscalaNormal().getEscala() == 0){
						esc0.add(temp);
						
					}
					if( ((Funcionario)temp).getEscalaNormal().getEscala() > 4){
						esc0.add(temp);
					}
				}
				
			}
		}
		
		String dia = dpSorteio.getValue().toString();
		
		if(dia.substring(dia.length()-2).equals("01") || dia.substring(dia.length()-2).equals("05") ||
				dia.substring(dia.length()-2).equals("09") || dia.substring(dia.length()-2).equals("13") ||
				dia.substring(dia.length()-2).equals("17") || dia.substring(dia.length()-2).equals("21") || 
				dia.substring(dia.length()-2).equals("25") || dia.substring(dia.length()-2).equals("29")){
			resultado.addAll(esc2);
			resultado.addAll(esc3);
			resultado.addAll(esc4);
			resultado.addAll(esc0);
			
		}
		else if((dia.substring(dia.length()-2).equals("02") || dia.substring(dia.length()-2).equals("06") ||
				dia.substring(dia.length()-2).equals("10") || dia.substring(dia.length()-2).equals("14") ||
				dia.substring(dia.length()-2).equals("18") || dia.substring(dia.length()-2).equals("22") || 
				dia.substring(dia.length()-2).equals("26") || dia.substring(dia.length()-2).equals("30"))){
			resultado.addAll(esc1);
			resultado.addAll(esc3);
			resultado.addAll(esc4);
			resultado.addAll(esc0);
		}
		else if((dia.substring(dia.length()-2).equals("03") || dia.substring(dia.length()-2).equals("07") ||
				dia.substring(dia.length()-2).equals("11") || dia.substring(dia.length()-2).equals("15") ||
				dia.substring(dia.length()-2).equals("19") || dia.substring(dia.length()-2).equals("23") || 
				dia.substring(dia.length()-2).equals("27") || dia.substring(dia.length()-2).equals("31"))){
			resultado.addAll(esc1);
			resultado.addAll(esc2);
			resultado.addAll(esc4);
			resultado.addAll(esc0);
		}
		else{
			resultado.addAll(esc1);
			resultado.addAll(esc2);
			resultado.addAll(esc3);
			resultado.addAll(esc0);
		}
		
		boolean tf1 = false;
		boolean tf2 = false;
		boolean tf3 = false;
		boolean tf4 = false;
		boolean tf5 = false;
		boolean tf6 = false;
		boolean tf7 = false;
		boolean tf8 = false;
		boolean tf9 = false;
		boolean tf10 = false;
		
		for(Pessoa doubt : resultado){
			
			
			if(tf01Sorteio.getText().length() != 0){
				if(doubt.getMatricula().equals(tf01Sorteio.getText())){
					tf1 = true;
				}
				
			}
			if(tf02Sorteio.getText().length() != 0){
				if(doubt.getMatricula().equals(tf02Sorteio.getText())){
					tf2 = true;
				}
				
			}
			if(tf03Sorteio.getText().length() != 0){
				if(doubt.getMatricula().equals(tf03Sorteio.getText())){
					tf3 = true;
				}
				
			}
			if(tf04Sorteio.getText().length() != 0){
				if(doubt.getMatricula().equals(tf04Sorteio.getText())){
					tf4 = true;
				}
				
			}
			if(tf05Sorteio.getText().length() != 0){
				if(doubt.getMatricula().equals(tf05Sorteio.getText())){
					tf5 = true;
				}
				
			}
			if(tf06Sorteio.getText().length() != 0){
				if(doubt.getMatricula().equals(tf06Sorteio.getText())){
					tf6 = true;
				}
				
			}
			if(tf07Sorteio.getText().length() != 0){
				if(doubt.getMatricula().equals(tf07Sorteio.getText())){
					tf7 = true;
				}
				
			}
			if(tf08Sorteio.getText().length() != 0){
				if(doubt.getMatricula().equals(tf08Sorteio.getText())){
					tf8 = true;
				}
				
			}
			if(tf09Sorteio.getText().length() != 0){
				if(doubt.getMatricula().equals(tf09Sorteio.getText())){
					tf9 = true;
				}
				
			}
			if(tf10Sorteio.getText().length() != 0){
				if(doubt.getMatricula().equals(tf10Sorteio.getText())){
					tf10 = true;
				}

			}
		}
		
		if(!tf1){
			tf01Sorteio.setText("");
		}
		if(!tf2){
			tf02Sorteio.setText("");
		}
		if(!tf3){
			tf03Sorteio.setText("");
		}
		if(!tf4){
			tf04Sorteio.setText("");
		}
		if(!tf5){
			tf05Sorteio.setText("");
		}
		if(!tf6){
			tf06Sorteio.setText("");
		}
		if(!tf7){
			tf07Sorteio.setText("");
		}
		if(!tf8){
			tf08Sorteio.setText("");
		}
		if(!tf9){
			tf09Sorteio.setText("");
		}
		if(!tf10){
			tf10Sorteio.setText("");
		}
		
		//LIMITADOR DE QTD DE DIAS PARA FUNCIONARIOS 
		for(Pessoa dd : listaCompleta){
			for(Selecionado ee : selecaoList){
				if( dd.getMatricula().equals(ee.getFunc().getMatricula())){
					if( ((Funcionario)dd).getEscalaNormal().getDiasQTD() <= ee.getDiasSorteados().size()){
						for(int j = 0; j < resultado.size(); j++){
							if(resultado.get(j).getMatricula().equals(dd.getMatricula())){
								this.removerIguais(resultado.get(j).getMatricula(), tf01Sorteio.getText(), tf01Sorteio);
								this.removerIguais(resultado.get(j).getMatricula(), tf02Sorteio.getText(), tf02Sorteio);
								this.removerIguais(resultado.get(j).getMatricula(), tf03Sorteio.getText(), tf03Sorteio);
								this.removerIguais(resultado.get(j).getMatricula(), tf04Sorteio.getText(), tf04Sorteio);
								this.removerIguais(resultado.get(j).getMatricula(), tf05Sorteio.getText(), tf05Sorteio);
								this.removerIguais(resultado.get(j).getMatricula(), tf06Sorteio.getText(), tf06Sorteio);
								this.removerIguais(resultado.get(j).getMatricula(), tf07Sorteio.getText(), tf07Sorteio);
								this.removerIguais(resultado.get(j).getMatricula(), tf08Sorteio.getText(), tf08Sorteio);
								this.removerIguais(resultado.get(j).getMatricula(), tf09Sorteio.getText(), tf09Sorteio);
								this.removerIguais(resultado.get(j).getMatricula(), tf10Sorteio.getText(), tf10Sorteio);
								resultado.remove(j);
							}
						}
					}
				}
			}
		}
		
		Collections.sort(resultado);
		if(tcFuncSorteio.getText().length() != 0){
		this.tvSorteio.setItems(resultado);
		}
	}
	
	public void LimparTextoSorteio(){
		this.tf01Sorteio.setText("");
		this.tf02Sorteio.setText("");
		this.tf03Sorteio.setText("");
		this.tf04Sorteio.setText("");
		this.tf05Sorteio.setText("");
		this.tf06Sorteio.setText("");
		this.tf07Sorteio.setText("");
		this.tf08Sorteio.setText("");
		this.tf09Sorteio.setText("");
		this.tf10Sorteio.setText("");
	}
	
	public void deletarDiaSorteio(){
		String dia = dpSorteio.getValue().toString();
		ArrayList<Selecionado> listaTemp = new ArrayList<Selecionado>();
		for(Selecionado s : selecaoList){
			if(s.getDiasSorteados().contains(dia.substring(dia.length()-2))){
				s.getDiasSorteados().remove(dia.substring(dia.length()-2));
				if(s.getDiasSorteados().size() != 0){
					listaTemp.add(s);
				}
				
			}
			else{
				if(s.getDiasSorteados().size() != 0){
					listaTemp.add(s);
				}
			}
			
		}
		selecaoList.clear();
		selecaoList.addAll(listaTemp);
		
		if(tcFuncSorteio.getText().length() == 0){
			countStatus--;
			this.handleStatusSorteio();
			tvSorteio.refresh();
		}
		this.carregarTableViewSorteio();
		tvSorteioFinal.getColumns().get(0).setVisible(false);
		tvSorteioFinal.getColumns().get(0).setVisible(true);
	}
	
	public void handleAddFinal(){
		ArrayList<String> pickList = new ArrayList<String>();

		removerIguaisTotal();
				
		if(tf01Sorteio.getText().length() != 0){
			pickList.add(tf01Sorteio.getText());
		}
		if(tf02Sorteio.getText().length() != 0){
			pickList.add(tf02Sorteio.getText());
		}
		if(tf03Sorteio.getText().length() != 0){
			pickList.add(tf03Sorteio.getText());
		}
		if(tf04Sorteio.getText().length() != 0){
			pickList.add(tf04Sorteio.getText());
		}
		if(tf05Sorteio.getText().length() != 0){
			pickList.add(tf05Sorteio.getText());
		}
		if(tf06Sorteio.getText().length() != 0){
			pickList.add(tf06Sorteio.getText());
		}
		if(tf07Sorteio.getText().length() != 0){
			pickList.add(tf07Sorteio.getText());
		}
		if(tf08Sorteio.getText().length() != 0){
			pickList.add(tf08Sorteio.getText());
		}
		if(tf09Sorteio.getText().length() != 0){
			pickList.add(tf09Sorteio.getText());
		}
		if(tf10Sorteio.getText().length() != 0){
			pickList.add(tf10Sorteio.getText());
		}
		
		if(validarSorteio(pickList)){
			
			for(int i = 0; i < pickList.size(); i++){
				String x = pickList.get(i);
				Pessoa temp = new Pessoa("", 'M', "", "");
				try {
					temp = Servidor.getInstance().procurar(x);
				} catch (UsuarioNaoEncontradoException e) {
					e.printStackTrace();
				}
				
				if(temp instanceof Funcionario){
					Funcionario f = new Funcionario(temp.getNome(), ((Funcionario)temp).getCargo(), temp.getSexo(), 
							temp.getMatricula(), ((Funcionario)temp).isMotorista(), temp.getSenha());
					
					Selecionado novo = new Selecionado(f);
					String dia = dpSorteio.getValue().toString();
					
					if(!selecaoList.contains(novo)){
						novo.addDiasSorteados(dia.substring(dia.length()-2));
						Collections.sort(novo.getDiasSorteados());
						selecaoList.add(novo);
					}
					else{
						for(int j = 0; j < selecaoList.size(); j++){
							boolean b = false;
							Selecionado temporal = selecaoList.get(j);
							if(temporal.equals(novo)){
								novo = temporal;
								for(String s : novo.getDiasSorteados()){
									if(dia.substring(dia.length()-2).equals(s)){
										b = true;
									}
								}
								if(!b){
									selecaoList.remove(j);
									novo.addDiasSorteados(dia.substring(dia.length()-2));
									Collections.sort(novo.getDiasSorteados());
									selecaoList.add(novo);
								}
								
							}
						}
						
						}
					
					}
				
					
				}
		}
		Collections.sort(selecaoList);
		if(tcFuncSorteio.getText().length() == 0){
			countStatus--;
			this.handleStatusSorteio();
			tvSorteio.refresh();
		}
		
		tvSorteioFinal.getColumns().get(0).setVisible(false);
		tvSorteioFinal.getColumns().get(0).setVisible(true);
		
	}
	
	public void handleFinalizarSorteio(){
		
		int c01 = 0; int c02 = 0; int c03 = 0; int c04 = 0; int c05 = 0; int c06 = 0; int c07 = 0; int c08 = 0;
		int c09 = 0; int c10 = 0; int c11 = 0; int c12 = 0; int c13 = 0; int c14 = 0; int c15 = 0; int c16 = 0;
		int c17 = 0; int c18 = 0; int c19 = 0; int c20 = 0; int c21 = 0; int c22 = 0; int c23 = 0; int c24 = 0;
		int c25 = 0; int c26 = 0; int c27 = 0; int c28 = 0; int c29 = 0; int c30 = 0; int c31 = 0;
			
			for(Selecionado s : selecaoList){
				for(String y : s.getDiasSorteados()){
					if(y.equals("01")){
						c01++;
					}
					if(y.equals("02")){
						c02++;
					}
					if(y.equals("03")){
						c03++;
					}
					if(y.equals("04")){
						c04++;
					}
					if(y.equals("05")){
						c05++;
					}
					if(y.equals("06")){
						c06++;
					}
					if(y.equals("07")){
						c07++;
					}
					if(y.equals("08")){
						c08++;
					}
					if(y.equals("09")){
						c09++;
					}
					if(y.equals("10")){
						c10++;
					}
					if(y.equals("11")){
						c11++;
					}
					if(y.equals("12")){
						c12++;
					}
					if(y.equals("13")){
						c13++;
					}
					if(y.equals("14")){
						c14++;
					}
					if(y.equals("15")){
						c15++;
					}
					if(y.equals("16")){
						c16++;
					}
					if(y.equals("17")){
						c17++;
					}
					if(y.equals("18")){
						c18++;
					}
					if(y.equals("19")){
						c19++;
					}
					if(y.equals("20")){
						c20++;
					}
					if(y.equals("21")){
						c21++;
					}
					if(y.equals("22")){
						c22++;
					}
					if(y.equals("23")){
						c23++;
					}
					if(y.equals("24")){
						c24++;
					}
					if(y.equals("25")){
						c25++;
					}
					if(y.equals("26")){
						c26++;
					}
					if(y.equals("27")){
						c27++;
					}
					if(y.equals("28")){
						c28++;
					}
					if(y.equals("29")){
						c29++;
					}
					if(y.equals("30")){
						c30++;
					}
					if(y.equals("31")){
						c31++;
					}
				
				}
			}

			String x = LocalDate.now().plusMonths(1).toString().substring(5, LocalDate.now().toString().length() - 3);
			if(x.equals("01") || x.equals("03") || x.equals("05") || x.equals("07") || x.equals("08")
					|| x.equals("10") || x.equals("12")){
				
				if(c01 < 4 || c02 < 4 || c03 < 4 || c04 < 4 || c05 < 4 || c06 < 4 || c07 < 4 || c08 < 4
						|| c09 < 4 || c10 < 4 || c11 < 4 || c12 < 4 || c13 < 4 || c14 < 4 || c15 < 4
						|| c16 < 4 || c17 < 4 || c18 < 4 || c19 < 4 || c20 < 4 || c21 < 4 || c22 < 4
						|| c23 < 4 || c24 < 4 || c25 < 4 || c26 < 4 || c27 < 4 || c28 < 4 || c29 < 4
						|| c30 < 4 || c31 < 4){
					
					this.handleStatusSorteio();
				}
				else{
					EscalaMes nova = new EscalaMes();
					ArrayList<Selecionado> listaFinal = new ArrayList<Selecionado>(); 
					for(Selecionado um : selecaoList){
						listaFinal.add(um);
					}
					Collections.sort(listaFinal);
					nova.setVoluntarios(listaFinal);
					EscalaMes teste = null;
					try {
						teste = Servidor.getInstance().procurarEscala(nova.getMesAno());
					} catch (EscalaNaoEncontradaException e) {
						e.printStackTrace();
					}
					finally{
						if(teste != null){
							Servidor.getInstance().removerEscala(teste);
							Servidor.getInstance().cadastrarEscala(nova);
							this.tf01Sorteio.setText("SUCESSO!!!");
							this.tf02Sorteio.setText("SUCESSO!!!");
							this.tf03Sorteio.setText("SUCESSO!!!");
							this.tf04Sorteio.setText("SUCESSO!!!");
							this.tf05Sorteio.setText("SUCESSO!!!");
							this.tf06Sorteio.setText("SUCESSO!!!");
							this.tf07Sorteio.setText("SUCESSO!!!");
							this.tf08Sorteio.setText("SUCESSO!!!");
							this.tf09Sorteio.setText("SUCESSO!!!");
							this.tf10Sorteio.setText("SUCESSO!!!");
						}
						else{
							Servidor.getInstance().cadastrarEscala(nova);
							this.tf01Sorteio.setText("SUCESSO!!!");
							this.tf02Sorteio.setText("SUCESSO!!!");
							this.tf03Sorteio.setText("SUCESSO!!!");
							this.tf04Sorteio.setText("SUCESSO!!!");
							this.tf05Sorteio.setText("SUCESSO!!!");
							this.tf06Sorteio.setText("SUCESSO!!!");
							this.tf07Sorteio.setText("SUCESSO!!!");
							this.tf08Sorteio.setText("SUCESSO!!!");
							this.tf09Sorteio.setText("SUCESSO!!!");
							this.tf10Sorteio.setText("SUCESSO!!!");
						}
						
						
					}
					
				}
			}
			
			else if(x.equals("04") || x.equals("06") || x.equals("09") || x.equals("11")){
				
				if(c01 < 4 || c02 < 4 || c03 < 4 || c04 < 4 || c05 < 4 || c06 < 4 || c07 < 4 || c08 < 4
						|| c09 < 4 || c10 < 4 || c11 < 4 || c12 < 4 || c13 < 4 || c14 < 4 || c15 < 4
						|| c16 < 4 || c17 < 4 || c18 < 4 || c19 < 4 || c20 < 4 || c21 < 4 || c22 < 4
						|| c23 < 4 || c24 < 4 || c25 < 4 || c26 < 4 || c27 < 4 || c28 < 4 || c29 < 4
						|| c30 < 4 || c31 != 0){
					
					this.handleStatusSorteio();
				}
				else{
					EscalaMes nova = new EscalaMes();
					ArrayList<Selecionado> listaFinal = new ArrayList<Selecionado>(); 
					for(Selecionado um : selecaoList){
						listaFinal.add(um);
					}
					Collections.sort(listaFinal);
					nova.setVoluntarios(listaFinal);
					EscalaMes teste = null;
					try {
						teste = Servidor.getInstance().procurarEscala(nova.getMesAno());
					} catch (EscalaNaoEncontradaException e) {
						e.printStackTrace();
					}
					finally{
						if(teste != null){
							Servidor.getInstance().removerEscala(teste);
							Servidor.getInstance().cadastrarEscala(nova);
							this.tf01Sorteio.setText("SUCESSO!!!");
							this.tf02Sorteio.setText("SUCESSO!!!");
							this.tf03Sorteio.setText("SUCESSO!!!");
							this.tf04Sorteio.setText("SUCESSO!!!");
							this.tf05Sorteio.setText("SUCESSO!!!");
							this.tf06Sorteio.setText("SUCESSO!!!");
							this.tf07Sorteio.setText("SUCESSO!!!");
							this.tf08Sorteio.setText("SUCESSO!!!");
							this.tf09Sorteio.setText("SUCESSO!!!");
							this.tf10Sorteio.setText("SUCESSO!!!");
						}
						else{
							Servidor.getInstance().cadastrarEscala(nova);
							this.tf01Sorteio.setText("SUCESSO!!!");
							this.tf02Sorteio.setText("SUCESSO!!!");
							this.tf03Sorteio.setText("SUCESSO!!!");
							this.tf04Sorteio.setText("SUCESSO!!!");
							this.tf05Sorteio.setText("SUCESSO!!!");
							this.tf06Sorteio.setText("SUCESSO!!!");
							this.tf07Sorteio.setText("SUCESSO!!!");
							this.tf08Sorteio.setText("SUCESSO!!!");
							this.tf09Sorteio.setText("SUCESSO!!!");
							this.tf10Sorteio.setText("SUCESSO!!!");
						}
						
						
					}
					
				}
			}
			else{
				
				if(c01 < 4 || c02 < 4 || c03 < 4 || c04 < 4 || c05 < 4 || c06 < 4 || c07 < 4 || c08 < 4
						|| c09 < 4 || c10 < 4 || c11 < 4 || c12 < 4 || c13 < 4 || c14 < 4 || c15 < 4
						|| c16 < 4 || c17 < 4 || c18 < 4 || c19 < 4 || c20 < 4 || c21 < 4 || c22 < 4
						|| c23 < 4 || c24 < 4 || c25 < 4 || c26 < 4 || c27 < 4 || c28 < 4 || c30 != 0 || c31 != 0){
					
					this.handleStatusSorteio();
				
				}
				else{
					
					EscalaMes nova = new EscalaMes();
					ArrayList<Selecionado> listaFinal = new ArrayList<Selecionado>(); 
					for(Selecionado um : selecaoList){
						listaFinal.add(um);
					}
					Collections.sort(listaFinal);
					nova.setVoluntarios(listaFinal);
					EscalaMes teste = null;
					try {
						teste = Servidor.getInstance().procurarEscala(nova.getMesAno());
					} catch (EscalaNaoEncontradaException e) {
						e.printStackTrace();
					}
					
					finally{
					
						if(teste != null){
							Servidor.getInstance().removerEscala(teste);
							Servidor.getInstance().cadastrarEscala(nova);
							this.tf01Sorteio.setText("SUCESSO!!!");
							this.tf02Sorteio.setText("SUCESSO!!!");
							this.tf03Sorteio.setText("SUCESSO!!!");
							this.tf04Sorteio.setText("SUCESSO!!!");
							this.tf05Sorteio.setText("SUCESSO!!!");
							this.tf06Sorteio.setText("SUCESSO!!!");
							this.tf07Sorteio.setText("SUCESSO!!!");
							this.tf08Sorteio.setText("SUCESSO!!!");
							this.tf09Sorteio.setText("SUCESSO!!!");
							this.tf10Sorteio.setText("SUCESSO!!!");
						}
						else{
							Servidor.getInstance().cadastrarEscala(nova);
							this.tf01Sorteio.setText("SUCESSO!!!");
							this.tf02Sorteio.setText("SUCESSO!!!");
							this.tf03Sorteio.setText("SUCESSO!!!");
							this.tf04Sorteio.setText("SUCESSO!!!");
							this.tf05Sorteio.setText("SUCESSO!!!");
							this.tf06Sorteio.setText("SUCESSO!!!");
							this.tf07Sorteio.setText("SUCESSO!!!");
							this.tf08Sorteio.setText("SUCESSO!!!");
							this.tf09Sorteio.setText("SUCESSO!!!");
							this.tf10Sorteio.setText("SUCESSO!!!");
						}
					}
				}
			}
		
	}
	
	public void handleStatusSorteio(){
		ArrayList<Pessoa> statistica = new ArrayList<Pessoa>();
		int contador = 0;
		
		for(int i = 1; i <= 31; i++){
			String c = "";
			if(i < 10){
				c = "0" + i;
			}
			else{
				c = "" + i;
			}
			for(Selecionado x : selecaoList){
				for(String s : x.getDiasSorteados()){
					if(c.equals(s)){
					contador++;	
					}
				}
			}
			String str = "";
			if(contador > 3){
				Pessoa temp = new Pessoa(c, 'M', (str + contador) , "OK");
				statistica.add(temp);
				contador = 0;
			}
			else{
				Pessoa temp = new Pessoa(c, 'M', (str + contador) , "");
				statistica.add(temp);
				contador = 0;
			}
			
		}
		
		ObservableList<Pessoa> statisticamente = FXCollections.observableArrayList();
		statisticamente.addAll(statistica);
		countStatus++;
		if((countStatus %2) != 0){
			tcMatSorteio.setText("DIAS");
			tcNomeSorteio.setText("QTD ESCALADOS");
			tcPrefSorteio.setText("STATUS");
			tcFuncSorteio.setText("");
			tcFuncSorteio.setVisible(false);
			tcEscSorteio.setVisible(false);
			tvSorteio.refresh();
			
			tcMatSorteio.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
			tcNomeSorteio.setCellValueFactory(cellData -> cellData.getValue().getMatriculaProperty());
			tcPrefSorteio.setCellValueFactory(cellData -> cellData.getValue().getSenhaProperty());
			tvSorteio.setItems(statisticamente);
			
		}
		else{
			
			tcMatSorteio.setText("MATRICULA");
			tcNomeSorteio.setText("NOME");
			tcPrefSorteio.setText("DIAS PREFERIDOS");
			tcFuncSorteio.setText("FUNCÃO");
			tcFuncSorteio.setVisible(true);
			tcEscSorteio.setVisible(true);
			tvSorteio.refresh();
			this.carregarTableViewSorteio();
			tcMatSorteio.setCellValueFactory(cellData -> cellData.getValue().getMatriculaProperty());
			tcNomeSorteio.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
			tcFuncSorteio.setCellValueFactory(cellData -> ((Funcionario) cellData.getValue()).motoristaProperty());
			tcEscSorteio.setCellValueFactory(cellData -> ((Funcionario) cellData.getValue()).getStringPropertyEscala());
			tcPrefSorteio.setCellValueFactory(cellData -> ((Funcionario) cellData.getValue()).getPreferidosFuncProperty());
			
		}
		
	}
	
	
	/*
	
	private ObservableList<Funcionario> createFuncList(){
		ObservableList<Funcionario> resultado = FXCollections.observableArrayList();
		
		
		pessoaListCV.addAll(Servidor.getInstance().getFuncionariosList());
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
 	*/
	@FXML
	private void handleRemover(){
		
		try {
			Servidor.getInstance().remover(tfMatriculaRP.getText());
			this.lbRemoverRP.setText("MATRICULA: " + tfMatriculaRP.getText() + " REMOVIDA COM SUCESSO!!!");
		} catch (UsuarioNaoEncontradoException e) {
			lbRemoverRP.setText(e.getMessage());
			e.printStackTrace();
		}
		
		this.funcList = mainApp.getFuncList();
		Collections.sort(funcList);
		tvFuncCV.setItems(funcList);
		tvFuncCV.refresh();
	}
	
	@FXML
	private void handleConsultarBMA(){
		
		this.lbDataInexistenteBMA.setText("");
		this.tvEscolhidoBMA.setItems(null);
		if(this.tfMesAnoBMA.getText().length() > 6){
			ObservableList<Selecionado> temporal = FXCollections.observableArrayList();
			try {
				if((Servidor.getInstance().procurarEscala(this.tfMesAnoBMA.getText())) == null){
					this.lbDataInexistenteBMA.setText("DATA INEXISTENTE");
				}
				else{
					EscalaMes temp = new EscalaMes();
					ArrayList<Selecionado> voluntarios = new ArrayList<Selecionado>();
					temp.setVoluntarios(voluntarios);
					
					temporal.addAll(temp.getVoluntarios());
					tcNomeBMA.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
					tcMatriculaBMA.setCellValueFactory(cellData -> cellData.getValue().getMatriculaProperty());
					tcCargoBMA.setCellValueFactory(cellData -> cellData.getValue().getCargoProperty());
					tcFuncaoBMA.setCellValueFactory(cellData -> cellData.getValue().getFuncaoProperty());
					tcDiasBMA.setCellValueFactory(cellData -> cellData.getValue().getDiasSorteadosProperty());
					tcTotalBMA.setCellValueFactory(cellData -> cellData.getValue().getTotalDiasProperty());
					this.tvEscolhidoBMA.setItems(temporal);
				}
				
			} catch (EscalaNaoEncontradaException e) {
				this.lbDataInexistenteBMA.setText("DATA INEXISTENTE");
				e.printStackTrace();
			}
		}
		else{
			this.lbDataInexistenteBMA.setText("ERRO NA DATA");
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
			} else
				try {
					if(Servidor.getInstance().procurarEscala(this.tfMesAnoBMA.getText()) == null){
						
					}
					else{
						Servidor.getInstance().removerEscala(Servidor.getInstance().procurarEscala(this.tfMesAnoBMA.getText()));
						this.lbErroDeletarBMA.setText("ESCALA REMOVIDA!!!");
						this.tvEscolhidoBMA.setItems(null);
					}
				} catch (EscalaNaoEncontradaException e) {
					this.lbErroDeletarBMA.setText("DATA INEXISTENTE!!!");
					e.printStackTrace();
				}
		}
	}
	
	
	@FXML
	private void handleSair(){
		mainApp.showLogin();
	}

}
