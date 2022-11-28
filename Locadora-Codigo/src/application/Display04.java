package application;

import java.sql.SQLException;
import java.util.List;

import Entity.Cliente;
import app.pt2.Client;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Display04 {
	VBox vb;
	Label lbtitle;
	Button btmenu;
	Stage a;
	Display01 dp1;
	//--------------------------------------------------
	private TextField tfCodigo;
	private TextField tfNome;
	private TextField tfCpf;
	private TextField tfRg;
	private TextField tfEmail;
	private TextField tfTelefone;
	//---------------------------
	private TableView tbvc;
	private List plan;
	
	public Display04(Stage a) {
		this.a = a;
		this.plan = plan;
	}
	public VBox getVb() {
		dp1 = new Display01(a);
		vb = new VBox();
		vb.setStyle("-fx-background-color:WHITE");
		//--BP
		BorderPane bp = new BorderPane();
		bp.setStyle("-fx-background-color:#588c7e;-fx-Border-color:588c7e;-fx-Border-width:10"
				+ ";-fx-Border-Radius:20;-fx-Background-Radius:30");
		//--BP - BUTTON
		btmenu = new Button("VOLTAR");
		btmenu.setStyle("-fx-font-size:10;-fx-text-fill:BLACK;-fx-background-color:#96ceb4;"
				+ "-fx-Border-color:#96ceb4;"
				+ "-fx-Border-width:10");
		btmenu.setMaxWidth(1000);
		btmenu.setMaxHeight(20);
		//--BP - LABEL
		lbtitle = new Label("CLIENTE");
		lbtitle.setStyle("-fx-font-size:40;-fx-text-fill:WHITE;");
		//--BP---------------------------------------------------------------------------------------------------
		bp.setTop(btmenu);
		bp.setCenter(lbtitle);
		bp.setAlignment(btmenu, Pos.TOP_CENTER);
		bp.setAlignment(lbtitle, Pos.CENTER);
		/////////////////////////////////////////////////////////////////////////////////
		
		//--VB2
				VBox vb2 = new VBox();
				vb2.setStyle("-fx-background-color:#588c7e;-fx-Border-color:588c7e;-fx-Border-width:10"
						+ ";-fx-Border-Radius:20;-fx-Background-Radius:30");
				//--VB2 - FLOWLAYOUT FW
				FlowPane fw = new FlowPane();
				
				
				//LEFT
				//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL-------------------------------------------------------------------
				GridPane gpl = new GridPane();
				//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL - LABEL
				Label lb1 = new Label("CODIGO");
				lb1.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				Label lb2 = new Label("NOME");
				lb2.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				Label lb3 = new Label("CPF");
				lb3.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				Label lb4 = new Label("RG");
				lb4.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				Label lb5 = new Label("EMAIL");
				lb5.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				Label lb6 = new Label("TELEFONE");
				lb6.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				Label lb7 = new Label("PLANO");
				lb7.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL - TEXTFIELDS
				tfCodigo = new TextField();
				tfNome = new TextField();
				tfCpf = new TextField();
				tfRg = new TextField();
				tfEmail = new TextField();
				tfTelefone = new TextField();
				
				tfCodigo.setPrefWidth(230);
				tfNome.setPrefWidth(230);
				tfCpf.setPrefWidth(230);
				tfRg.setPrefWidth(230);
				tfEmail.setPrefWidth(230);
				tfTelefone.setPrefWidth(230);
				//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL------------------------------------------------------------------
				gpl.addRow(0, lb1,tfCodigo);
				gpl.addRow(1, lb2,tfNome);
				gpl.addRow(2, lb3,tfCpf);
				gpl.addRow(3, lb4,tfRg);
				gpl.addRow(4, lb5,tfEmail);
				gpl.addRow(5, lb6,tfTelefone);
				gpl.setVgap(20);
				
				//RIGHT
				//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR-----------------------------------------------------------------
				//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPR - OBJECTS
				tbvc = new TableView();
				tbvc.setPrefHeight(215);
				////////////------------
				TableColumn<Cliente,String> colcliente = new TableColumn("Cliente");
				
				TableColumn<Cliente,Integer> colcod = new TableColumn("Codigo");
				colcod.setCellValueFactory(new PropertyValueFactory("codigo"));
				
				TableColumn<Cliente,String> colnome = new TableColumn("Nome");
				colnome.setCellValueFactory(new PropertyValueFactory("nome"));
				
				TableColumn<Cliente,String> colcpf = new TableColumn("CPF");
				colcpf.setCellValueFactory(new PropertyValueFactory("cpf"));
				
				TableColumn<Cliente,String> colrg = new TableColumn("RG");
				colrg.setCellValueFactory(new PropertyValueFactory("rg"));
				
				TableColumn<Cliente,String> colplano = new TableColumn("Plano");
				colplano.setCellValueFactory(new PropertyValueFactory("plano"));
				
				TableColumn<Cliente,String> colemail = new TableColumn("Email");
				colemail.setCellValueFactory(new PropertyValueFactory("email"));
				
				TableColumn<Cliente,String> coltelefone = new TableColumn("Telefone");
				coltelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
				
				colcliente.getColumns().addAll(colcod,colnome,colcpf,colrg,colplano,
						colemail,coltelefone);
				
				tbvc.getColumns().add(colcliente);
				////////////------------
				//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR------------------------------------------------------------------
				
				//--VB2 - FLOWLAYOUT FW----------------------------------------------------------------------------------
				//--------------------fw.getChildren().addAll(gpl,ta);
				fw.getChildren().addAll(gpl,tbvc);
				fw.setAlignment(Pos.CENTER);
				fw.setHgap(100);
				//--VB2 - FLOWLAYOUT ------------------------------------------------------------------------------------
				
				//--VB2 - FLOWLAYOUT3 FW3---------------------------------------------------------------------------------
				FlowPane fw2 = new FlowPane();
				//--VB2 - FLOWLAYOUT3 - BUTTON
				Button bt1Inserir = new Button("Inserir");
				Button bt2Atualizar = new Button("Atualizar");
				Button bt3Excluir = new Button("Excluir");
				Button bt4Buscar = new Button("Buscar");
				Button bt5Listar = new Button("Listar");
				
				bt1Inserir.setPrefWidth(130);
				bt2Atualizar.setPrefWidth(130);
				bt3Excluir.setPrefWidth(130);
				bt4Buscar.setPrefWidth(130);
				bt5Listar.setPrefWidth(130);
				
				
				//--VB2 - FLOWLAYOUT3 - BUTTON
				fw2.getChildren().addAll(bt1Inserir,bt4Buscar,bt3Excluir,bt2Atualizar,bt5Listar);
				fw2.setAlignment(Pos.CENTER);
				fw2.setHgap(20);
				//--VB2 - FLOWLAYOUT3 FW3---------------------------------------------------------------------------------
				//--VB2 - FLOWLAYOUT4 FW4---------------------------------------------------------------------------------

				vb2.getChildren().addAll(fw,fw2);
				vb2.setMargin(fw, new Insets(20,0,0,0));
				vb2.setMargin(fw2, new Insets(20,0,40,0));
				//STYES
		
		
		/////////////////////////////////////////////////////////////////////////////////
				
				Client cl = new Client(tfCodigo,tfNome,tfCpf,tfRg,tfEmail,
						tfTelefone,tbvc);
				
				bt1Inserir.addEventHandler(MouseEvent.MOUSE_CLICKED, cl.getEvh());
				
				bt3Excluir.addEventHandler(MouseEvent.MOUSE_CLICKED, cl.getEvh());
				
				bt4Buscar.addEventHandler(MouseEvent.MOUSE_CLICKED, cl.getEvh());
				
				bt2Atualizar.addEventHandler(MouseEvent.MOUSE_CLICKED, cl.getEvh());
				
				bt5Listar.addEventHandler(MouseEvent.MOUSE_CLICKED, cl.getEvh());
				
		btmenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent ar) {
				// TODO Auto-generated method stub
				if(ar.getSource().toString().contains("VOLTAR")) {
					change(a,dp1.getVb());
				}
			}
			});
		vb.getChildren().addAll(bp,vb2);
		vb.setMargin(vb2, new Insets(20,0,0,0));
		return vb;
	}
	public void change(Stage a,VBox vb2) {
		Scene sc = new Scene(vb2,1000,500);
		a.setScene(sc);
	}
}
