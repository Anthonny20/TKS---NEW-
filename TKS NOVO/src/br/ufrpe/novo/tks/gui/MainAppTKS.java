package br.ufrpe.novo.tks.gui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.ufrpe.novo.tks.gui.model.AdministradorController;
import br.ufrpe.novo.tks.gui.model.FuncionarioController;
import br.ufrpe.novo.tks.gui.model.LoginController;
import br.ufrpe.novo.tks.negocios.Servidor;
import br.ufrpe.novo.tks.negocios.beans.Administrador;
import br.ufrpe.novo.tks.negocios.beans.EscalaMes;
import br.ufrpe.novo.tks.negocios.beans.Funcionario;
import br.ufrpe.novo.tks.negocios.beans.Pessoa;
import br.ufrpe.novo.tks.negocios.beans.Selecionado;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainAppTKS extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private Pessoa logado = null;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TKS");
		
		initRootLayout();
		if(logado == null){
			showLogin();
		}
		
	}
	
	 public void initRootLayout() {
	        try {

	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainAppTKS.class.getResource("view/RootLayout.fxml"));
	            rootLayout = (BorderPane) loader.load();

	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void showLogin() {
	        try {

	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainAppTKS.class.getResource("view/Login.fxml"));
	            AnchorPane Login = (AnchorPane) loader.load();

	            rootLayout.setCenter(Login);
	            LoginController controller = loader.getController();
	            controller.setMainApp(this);
	            
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void showFuncionario(Funcionario func) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainAppTKS.class.getResource("view/Funcionario.fxml"));
				AnchorPane funcionario = (AnchorPane) loader.load();

				rootLayout.setCenter(funcionario);
				FuncionarioController controller = loader.getController();
				controller.setMainApp(this);

				controller.setPessoa(func);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void showAdministrador(Administrador func) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainAppTKS.class.getResource("view/Administrador.fxml"));
				AnchorPane administrador = (AnchorPane) loader.load();

				rootLayout.setCenter(administrador);
				AdministradorController controller = loader.getController();
				controller.setMainApp(this);
				
				controller.setPessoa(func);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public ObservableList<Selecionado> getSelecionadoData(){
			LocalDate hoje = LocalDate.now();
			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("MM/YYYY");
			EscalaMes mesAtual =  Servidor.getInstance().procurarEscala(hoje.format(formatador));
			ObservableList<Selecionado> atual = FXCollections.observableArrayList();
			atual.addAll(mesAtual.getVoluntarios());
			return atual;
		
		}
		
		public ObservableList<Pessoa> getPessoaList(){
			ObservableList<Pessoa> atual = FXCollections.observableArrayList();
			atual.addAll(Servidor.getInstance().getPessoas());
			return atual;
		}
		
		public Stage getPrimaryStage() {
			
			return primaryStage;
	    }

		public static void main(String[] args) {
			launch(args);
		}
}
