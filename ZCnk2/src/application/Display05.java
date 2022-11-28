package application;

import java.util.List;

import app.pt2.Atorc;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Display05 {
	VBox vb;
	Label lbtitle;
	Button btmenu;
	Stage a;
	Display01 dp1;
	//-----------------------------------------------
	private TextField tfCodigo;
	private TextField tfNome;
	private ComboBox cbNacao;
	private TextField tfNomeArt;
	private TextField tfObra;
	
	//---------------------------
	private RadioButton rb1s;
	private RadioButton rb2f;
	
	private TableView tbvafs;
	private TableView tbvafs2;
	
	private String change="1";
	
	public Display05(Stage a) {
		this.a = a;
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
		lbtitle = new Label("ATOR");
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
		Label lb3 = new Label("NACIONALIDADE");
		lb3.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb4 = new Label("NOME ART");
		lb4.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb5 = new Label("OBRA");
		lb5.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL - TEXTFIELDS
		tfCodigo = new TextField();
		tfNome = new TextField();
		String [] nacao = {"FRANCES","INGLES","ESTADUNIDENSE","ALEMAO","BRASILEIRO","ITALIANO",
				"MEXICANO","CHINES","JAPONES","TURCO"};
		cbNacao = new ComboBox(FXCollections.observableArrayList(nacao));
		cbNacao.getSelectionModel().select(0);
		tfNomeArt = new TextField();
		tfObra = new TextField();
		
		tfCodigo.setPrefWidth(230);
		tfNome.setPrefWidth(230);
		cbNacao.setPrefWidth(230);
		tfNomeArt.setPrefWidth(230);
		tfObra.setPrefWidth(230);
		
		//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL------------------------------------------------------------------
		gpl.addRow(0, lb1,tfCodigo);
		gpl.addRow(1, lb2,tfNome);
		gpl.addRow(2, lb3,cbNacao);
		gpl.addRow(3, lb4,tfNomeArt);
		gpl.addRow(4, lb5,tfObra);
		gpl.setVgap(20);
		
		//RIGHT
		//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR-------------------------------------------------------------------
		GridPane gpr = new GridPane();
		//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR - OBJECTS
		rb2f = new RadioButton("Filme");
		rb2f.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		
		rb1s = new RadioButton("SERIE");
		rb1s.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		ToggleGroup tgfs = new ToggleGroup();
		rb1s.setSelected(true);
		rb1s.setToggleGroup(tgfs);
		rb2f.setToggleGroup(tgfs);
		
		//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR------------------------------------------------------------------
		FlowPane fwgpr = new FlowPane();
		fwgpr.getChildren().addAll(rb2f,rb1s);
		fwgpr.setAlignment(Pos.TOP_CENTER);
		fwgpr.setHgap(40);
		
		tbvafs = new TableView();
		tbvafs2 = new TableView();
		if(change == "1") {
			tbvafs.setPrefHeight(210);
			gpr.addRow(0, tbvafs);
			gpr.addRow(1, fwgpr);
		}
		else {
			tbvafs.setPrefHeight(90);
			tbvafs2.setPrefHeight(120);
			gpr.addRow(0, tbvafs);
			gpr.addRow(1, tbvafs2);
			gpr.addRow(2, fwgpr);
		}
		gpr.setVgap(20);
		
		//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR------------------------------------------------------------------
		
		//--VB2 - FLOWLAYOUT FW----------------------------------------------------------------------------------
		fw.getChildren().addAll(gpl,gpr);
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
		Button btinsereFA = new Button("Insere Obra");
		Button btremoveFA = new Button("Remove Obra");
		
		
		
		bt1Inserir.setPrefWidth(150);
		bt2Atualizar.setPrefWidth(150);
		bt3Excluir.setPrefWidth(150);
		bt4Buscar.setPrefWidth(150);
		bt5Listar.setPrefWidth(150);
		btinsereFA.setPrefWidth(150);
		btremoveFA.setPrefWidth(150);
		
		fw2.setMargin(btinsereFA, new Insets(10,0,0,0));
		fw2.setMargin(btremoveFA, new Insets(10,0,0,0));
		
		//--VB2 - FLOWLAYOUT3 - BUTTON
		fw2.getChildren().addAll(bt1Inserir,bt4Buscar,bt3Excluir,bt2Atualizar,bt5Listar,btinsereFA,
				btremoveFA);
		fw2.setAlignment(Pos.CENTER);
		fw2.setHgap(30);
		//--VB2 - FLOWLAYOUT3 FW3---------------------------------------------------------------------------------
		//--VB2 - FLOWLAYOUT4 FW4---------------------------------------------------------------------------------

		vb2.getChildren().addAll(fw,fw2);
		vb2.setMargin(fw, new Insets(20,0,0,0));
		vb2.setMargin(fw2, new Insets(20,0,20,0));
		//STYES

		/////////////////////////////////////////////////////////////////////////////////
		                                                                              //
		Atorc ac = new Atorc(tfCodigo,tfNome,cbNacao,tfNomeArt,tfObra,rb1s,rb2f,tbvafs,change,a);//ta);
		
		bt1Inserir.addEventHandler(MouseEvent.MOUSE_CLICKED, ac.getEvh());
		
		bt2Atualizar.addEventHandler(MouseEvent.MOUSE_CLICKED, ac.getEvh());
		
		bt3Excluir.addEventHandler(MouseEvent.MOUSE_CLICKED, ac.getEvh());
		
		bt4Buscar.addEventHandler(MouseEvent.MOUSE_CLICKED, ac.getEvh());
		bt5Listar.addEventHandler(MouseEvent.MOUSE_CLICKED, ac.getEvh());
		
		btinsereFA.addEventHandler(MouseEvent.MOUSE_CLICKED, ac.getEvh());
		
		btremoveFA.addEventHandler(MouseEvent.MOUSE_CLICKED, ac.getEvh());
		
		
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
		System.out.println("uuuuuuuuuuu");
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	
	public TableView getTbvafs() {
		return tbvafs;
	}
	public TableView getTbvafs2() {
		return tbvafs2;
	}
	
	
	public RadioButton getRb1s() {
		return rb1s;
	}
	public RadioButton getRb2f() {
		return rb2f;
	}
	public void setRb1s(boolean s) {
		rb1s.setSelected(s);
	}
	public void setRb2f(boolean f) {
		rb2f.setSelected(f);
	}
	
	public Stage getA() {
		return a;
	}
	public TextField getTfCodigo() {
		return tfCodigo;
	}
	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}
	public TextField getTfNome() {
		return tfNome;
	}
	public void setTfNome(TextField tfNome) {
		this.tfNome = tfNome;
	}
	public TextField getTfNomeArt() {
		return tfNomeArt;
	}
	public void setTfNomeArt(TextField tfNomeArt) {
		this.tfNomeArt = tfNomeArt;
	}
	public TextField getTfObra() {
		return tfObra;
	}
	public void setTfObra(TextField tfObra) {
		this.tfObra = tfObra;
	}
	public ComboBox getCbNacao() {
		return cbNacao;
	}
	public void setCbNacao(ComboBox cbNacao) {
		this.cbNacao = cbNacao;
	}
	
	}
