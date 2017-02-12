package br.ufrpe.novo.tks.gui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import br.ufrpe.novo.tks.exceptions.EscalaNaoEncontradaException;
import br.ufrpe.novo.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.novo.tks.gui.model.AdministradorController;
import br.ufrpe.novo.tks.gui.model.FuncionarioController;
import br.ufrpe.novo.tks.gui.model.LoginController;
import br.ufrpe.novo.tks.negocios.Servidor;
import br.ufrpe.novo.tks.negocios.beans.Administrador;
import br.ufrpe.novo.tks.negocios.beans.EscalaMes;
import br.ufrpe.novo.tks.negocios.beans.EscalaNormal;
import br.ufrpe.novo.tks.negocios.beans.Funcionario;
import br.ufrpe.novo.tks.negocios.beans.Pessoa;
import br.ufrpe.novo.tks.negocios.beans.Selecionado;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainAppTKS extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private Pessoa logado = null;
	private ObservableList<Pessoa> viewCV = FXCollections.observableArrayList();
	private ObservableList<Selecionado> listaFinal = FXCollections.observableArrayList();

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
		
		public ObservableList<Selecionado> getSelecionadoData() throws EscalaNaoEncontradaException{
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
			Collections.sort(atual);
			return atual;
		}
		
		public ObservableList<Pessoa> getFuncList(){
			ObservableList<Pessoa> listaFunc = FXCollections.observableArrayList();
			listaFunc.addAll(Servidor.getInstance().getFuncionariosList());
			return listaFunc;
		}
		
		public void clearViewCV(){
			this.viewCV.clear();
		}
		
		public void clearFinal(){
			this.listaFinal.clear();
		}
		
		public void addListaTempView(String mat, int x){
				try {
					Pessoa temp = new Funcionario(Servidor.getInstance().procurar(mat).getNome(), ((Funcionario) Servidor.getInstance().procurar(mat)).getCargo(),
							Servidor.getInstance().procurar(mat).getSexo(), mat, ((Funcionario) Servidor.getInstance().procurar(mat)).isMotorista(), Servidor.getInstance().procurar(mat).getSenha());
					temp = Servidor.getInstance().procurar(temp.getMatricula());
					if(((Funcionario)temp).getEscalaNormal() != null){
						((Funcionario)temp).getEscalaNormal().setEscala(x);
					}
					else{
						EscalaNormal nova = new EscalaNormal(x);
						((Funcionario)temp).setEscalaNormalFunc(nova);
					}
					
					((Funcionario) temp).setEscala(x);
					((Funcionario)temp).getEscalaNormal().setDiasSorteados(((Funcionario)temp).getEscalaNormal().getEscala());
					for(int i = 0; i < ((Funcionario)temp).getEscalaNormal().getDiasPreferidos().size(); i++){
						for(int j = 0; j < ((Funcionario)temp).getEscalaNormal().getDiasBloqueados().length; j++){
							if(((Funcionario)temp).getEscalaNormal().getDiaPreferido(i).equals("" + ((Funcionario)temp).getEscalaNormal().getDiaBloqueado(j)) ){
								((Funcionario)temp).getEscalaNormal().getDiasPreferidos().remove(i);
							}
						}
					}
					for(int i = 0; i < viewCV.size(); i++){
						if(this.viewCV.contains(temp)){
							int j = this.viewCV.indexOf(temp);
							this.viewCV.remove(j);
						}
					}
					
					this.viewCV.add(temp);
					
				} catch (UsuarioNaoEncontradoException e) {
					
					e.printStackTrace();
				}
		}
		
		public void addListaFinal(String mat, int x){
			if(mat != null){
				try {
					Pessoa f = Servidor.getInstance().procurar(mat);
					if(f instanceof Funcionario){
						
						Selecionado temp = new Selecionado((Funcionario) f);
						temp.addDiasSorteados("0" + x);
						for(int i = 0; i < listaFinal.size(); i++){
							if(this.listaFinal.contains(temp)){
								int j = this.listaFinal.indexOf(temp);
								this.listaFinal.remove(j);
							}
						}
							
						this.listaFinal.add(temp);
						}		
					} catch (UsuarioNaoEncontradoException e) {

						e.printStackTrace();
					}
			
		}
		}
		
		//PARAM TABLEVIEW<PESSOA> SOURCE - LOCAL DE ORIGEM DO DRAG&DROP.
		//PARAM LABEL TARGET - LOCAL ONDE DEVE SER SOLTO O DRAG&DROP.
		//PARAM TABLEVIEW<PESSOA> DESTINY - TABLE VIEW PARA APRESENTAÇÃO DAS INFORMAÇOES MODIFICADAS.
		//PARAM INT X - INTEIRO PARA CONFIGURAÇÃO DE ESCALAS.
		public void dragDropCV(TableView<Pessoa> source, Label target, TableView<Pessoa> destiny, int x ){
			source.setOnDragDetected(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent event) {
			        
			        Dragboard db = source.startDragAndDrop(TransferMode.COPY);
			        ClipboardContent content = new ClipboardContent();
			        content.putString(source.getSelectionModel().getSelectedItem().getMatricula());
			        db.setContent(content);
			        event.consume();
			    }
			});
			
			target.setOnDragOver(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			        /* data is dragged over the target */
			        /* accept it only if it is not dragged from the same node 
			         * and if it has a string data */
			        if (event.getGestureSource() != target &&
			                event.getDragboard().hasString()) {
			            /* allow for both copying and moving, whatever user chooses */
			            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			        }
			        
			        event.consume();
			    }
			});
			
			target.setOnDragDropped(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			    	
			        /* data dropped */
			        /* if there is a string data on dragboard, read it and use it */
			        Dragboard db = event.getDragboard();
			        boolean success = false;
			        if (db.hasString()) {

				        addListaTempView(db.getString(), x);
				        destiny.setItems(getViewCV());
				        
			            success = true;
			        }
			        /* let the source know whether the string was successfully 
			         * transferred and used */
			        event.setDropCompleted(success);
			        
			        event.consume();
			     }
			});
			
			target.setOnDragEntered(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			        /* the drag-and-drop gesture entered the target */
			        /* show to the user that it is an actual gesture target */
			    
			             if (event.getGestureSource() != target &&
			                     event.getDragboard().hasString()) {
			                 target.setCursor(Cursor.HAND);
			                 
			             }
			                    
			             event.consume();
			        }
			    });
			
			target.setOnDragExited(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			        /* mouse moved away, remove the graphical cues */
			        target.setCursor(Cursor.DEFAULT);

			        event.consume();
			    }
			});
		}
		
		public void dragDropSorteio(TableView<Pessoa> source, TextField target ){
			source.setOnDragDetected(new EventHandler<MouseEvent>() {
			    public void handle(MouseEvent event) {
			        
			        Dragboard db = source.startDragAndDrop(TransferMode.COPY);
			        ClipboardContent content = new ClipboardContent();
			        content.putString(source.getSelectionModel().getSelectedItem().getMatricula());
			        db.setContent(content);
			        event.consume();
			    }
			});
			
			target.setOnDragOver(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			        /* data is dragged over the target */
			        /* accept it only if it is not dragged from the same node 
			         * and if it has a string data */
			        if (event.getGestureSource() != target &&
			                event.getDragboard().hasString()) {
			            /* allow for both copying and moving, whatever user chooses */
			            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			        }
			        
			        event.consume();
			    }
			});
			
			target.setOnDragDropped(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			    	
			        /* data dropped */
			        /* if there is a string data on dragboard, read it and use it */
			        Dragboard db = event.getDragboard();
			        boolean success = false;
			        if (db.hasString()) {

			        	target.setText(db.getString());
			            success = true;
			        }
			        /* let the source know whether the string was successfully 
			         * transferred and used */
			        event.setDropCompleted(success);
			        
			        event.consume();
			     }
			});
			
			target.setOnDragEntered(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			        /* the drag-and-drop gesture entered the target */
			        /* show to the user that it is an actual gesture target */
			    
			             if (event.getGestureSource() != target &&
			                     event.getDragboard().hasString()) {
			                 target.setCursor(Cursor.HAND);
			                 
			             }
			                    
			             event.consume();
			        }
			    });
			
			target.setOnDragExited(new EventHandler<DragEvent>() {
			    public void handle(DragEvent event) {
			        /* mouse moved away, remove the graphical cues */
			        target.setCursor(Cursor.DEFAULT);

			        event.consume();
			    }
			});
		}
		
		public ObservableList<Pessoa> getViewCV(){
			Collections.sort(viewCV);
			return this.viewCV;
		}
		
//		public ObservableList<Selecionado> getListaFinal(){
	//		return this.listaFinal;
		//}
		
		public Stage getPrimaryStage() {
			
			return primaryStage;
	    }

		public static void main(String[] args) {
			launch(args);
		}
}
