package br.ufrpe.novo.tks.gui.model;

import br.ufrpe.novo.tks.dados.IRepositorioPessoa;
import br.ufrpe.novo.tks.dados.RepositorioEscalaMes;
import br.ufrpe.novo.tks.dados.RepositorioPessoa;
import br.ufrpe.novo.tks.exceptions.LoginIncorretoException;
import br.ufrpe.novo.tks.exceptions.SenhaIncorretaException;
import br.ufrpe.novo.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.novo.tks.gui.MainAppTKS;
import br.ufrpe.novo.tks.negocios.CadastroPessoa;
import br.ufrpe.novo.tks.negocios.beans.Administrador;
import br.ufrpe.novo.tks.negocios.beans.Funcionario;
import br.ufrpe.novo.tks.negocios.beans.Pessoa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private TextField campoMatricula;
	@FXML
	private PasswordField campoSenha;
	@FXML
	private Button btEntrar;
	@FXML
	private Label lbErro;
	

	private MainAppTKS mainApp;
	private Pessoa logado;

	IRepositorioPessoa repositorioPessoa = RepositorioPessoa.getInstance();
	RepositorioEscalaMes repEsc = RepositorioEscalaMes.getInstance();
	CadastroPessoa cadastroPessoa = new CadastroPessoa(repEsc, repositorioPessoa);

	public LoginController() {

	}

	@FXML
	private void initialize() {
		

	}

	public void setMainApp(MainAppTKS mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleLogin() {
		if (campoMatricula.getText().equals("") || campoSenha.getText().equals("")) {
			lbErro.setText("Matrícula e/ou senha não preenchido");
		} else {
			try {
				cadastroPessoa.login(campoMatricula.getText(), campoSenha.getText());
				logado = cadastroPessoa.procurar(campoMatricula.getText());
				if(logado instanceof Funcionario){
					mainApp.showFuncionario((Funcionario) logado);
				}else if(logado instanceof Administrador){
					mainApp.showAdministrador((Administrador) logado);
				}	
			} catch (LoginIncorretoException e) {
				lbErro.setText(e.getMessage());
			} catch (SenhaIncorretaException e) {
				lbErro.setText(e.getMessage());
			} catch (UsuarioNaoEncontradoException e) {
				lbErro.setText(e.getMessage());
			}
		}
	}
}
