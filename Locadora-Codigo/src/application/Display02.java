package application;

import Entity.Filme;
import Entity.Serie;
import app.pt2.FilmContro;
import javafx.collections.MapChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
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

public class Display02 {
	VBox vb;
	Label lbtitle;
	Button btmenu;
	Stage a;
	Display01 dp1;
	
	//--------------------------
	private TextField tfCodigo;
	private TextField tfTitulo;
	private TextField tfGenero;
	private TextField tfNumtemp;
	private DatePicker dpData;
	private Slider slAvaliacao;
	private Scene sc;
	//private TextArea ta;
	private TableView tbvfs;
	
	public Display02(Stage a) {
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
		lbtitle = new Label("FILME - SERIE");
		lbtitle.setStyle("-fx-font-size:40;-fx-text-fill:WHITE;");
		//--BP---------------------------------------------------------------------------------------------------
		bp.setTop(btmenu);
		bp.setCenter(lbtitle);
		bp.setAlignment(btmenu, Pos.TOP_CENTER);
		bp.setAlignment(lbtitle, Pos.CENTER);
		
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
		Label lb1 = new Label("PRODUTORA");
		lb1.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb2 = new Label("CODIGO");
		lb2.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb3 = new Label("TITULO");
		lb3.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL - TEXTFIELDS
		//tfProdutora = new TextField();
		tfCodigo = new TextField();
		tfTitulo = new TextField();
		
		//tfProdutora.setPrefWidth(230);
		tfCodigo.setPrefWidth(230);
		tfTitulo.setPrefWidth(230);
		
		//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL------------------------------------------------------------------
		//gpl.addRow(0, lb1,tfProdutora);
		gpl.addRow(0, lb2,tfCodigo);
		gpl.addRow(1, lb3,tfTitulo);
		gpl.setVgap(20);
		
		
		
		//RIGHT
		//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR-----------------------------------------------------------------
		GridPane gpr = new GridPane();
		//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPR - LABEL
		
		Label lb4 = new Label("GENERO");
		lb4.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb5 = new Label("DATA");
		lb5.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb6 = new Label("AVALIACAO");
		lb6.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		
		//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPR - OBJECTS
		tfGenero = new TextField();
		//DATAPICKER - SLIDER
		dpData = new DatePicker();
		slAvaliacao = new Slider(0,10,5);
		slAvaliacao.setShowTickMarks(true);
		//slAvaliacao.setShowTickLabels(true);
		slAvaliacao.setMajorTickUnit(1);
		
		tfGenero.setPrefWidth(230);
		dpData.setPrefWidth(230);
		slAvaliacao.setPrefWidth(230);
				
		//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR------------------------------------------------------------------
		
		gpr.addRow(0, lb4,tfGenero);
		gpr.addRow(1, lb5,dpData);
		gpr.addRow(2, lb6,slAvaliacao);
		
		gpr.setVgap(20);
		//--VB2 - FLOWLAYOUT FW----------------------------------------------------------------------------------
		fw.getChildren().addAll(gpl,gpr);
		fw.setAlignment(Pos.CENTER);
		fw.setHgap(200);
		//--VB2 - FLOWLAYOUT ------------------------------------------------------------------------------------
		
		//--VB2 - FLOWLAYOUT2 FW2
		//--VB2 - FLOWLAYOUT2 FW2 - RADIO-BUTTON
		FlowPane fw2 = new FlowPane();
		//--------------------------------------------------------
				RadioButton rb1serie = new RadioButton("Serie");
				RadioButton rb2filme = new RadioButton("Filme");
				ToggleGroup tg = new ToggleGroup();
				rb1serie.setToggleGroup(tg);
				rb1serie.setStyle("-fx-text-fill:WHITE");
				rb2filme.setToggleGroup(tg);
				rb2filme.setStyle("-fx-text-fill:WHITE");
				//rb2filme.setSelected( true);
				Label lb7 = new Label("NUMERO DE TEMPORADAS");
				lb7.setStyle("-fx-text-fill:WHITE");
				tfNumtemp = new TextField();
				tfNumtemp.setPrefWidth(230);
		//--------------------------------------------------------
		//--VB2 - FLOWLAYOUT2 FW3---------------------------------------------------------------------------------
				fw2.getChildren().addAll(rb2filme,rb1serie,lb7,tfNumtemp);
				fw2.setAlignment(Pos.CENTER);
				fw2.setHgap(20);
				
		//--VB2 - FLOWLAYOUT3 FW3---------------------------------------------------------------------------------
		FlowPane fw3 = new FlowPane();
		//--VB2 - FLOWLAYOUT3 - BUTTON
		Button bt1insere = new Button("INSERIR");
		Button bt2busca = new Button("BUSCAR");
		Button bt3exclui = new Button("EXCLUIR");
		Button bt4atualiza = new Button("ATUALIZAR");
		Button bt5lista = new Button("LISTAR");
		
		bt1insere.setPrefWidth(130);
		bt2busca.setPrefWidth(130);
		bt3exclui.setPrefWidth(130);
		bt4atualiza.setPrefWidth(130);
		bt5lista.setPrefWidth(130);
		
		
		//--VB2 - FLOWLAYOUT3 - BUTTON
		fw3.getChildren().addAll(bt1insere,bt2busca,bt3exclui,bt4atualiza,bt5lista);
		fw3.setAlignment(Pos.CENTER);
		fw3.setHgap(20);
		//--VB2 - FLOWLAYOUT3 FW3---------------------------------------------------------------------------------
		
		//--VB2 - FLOWLAYOUT4 FW4---------------------------------------------------------------------------------
		FlowPane fw4 = new FlowPane();
		
		tbvfs = new TableView<>();
		tbvfs.setPrefWidth(950);
		tbvfs.setPrefHeight(95);
		fw4.getChildren().add(tbvfs);
		
		fw4.setAlignment(Pos.CENTER);
		
		vb2.getChildren().addAll(fw,fw2,fw3,fw4);
		vb2.setMargin(fw, new Insets(20,0,0,0));
		vb2.setMargin(fw2, new Insets(20,0,0,0));
		vb2.setMargin(fw3, new Insets(20,0,0,0));
		vb2.setMargin(fw4, new Insets(20,0,20,0));
		//STYES
				//--------------------------------------------------------
				tfNumtemp.setEditable(false);
				tfNumtemp.setStyle("-fx-Background-color:GREEN");
				
				//FUNCTION
				//------------------------------
				FilmContro fmc = new FilmContro(tfCodigo, tfTitulo, tfGenero, tfNumtemp, dpData, slAvaliacao, 
						rb1serie, rb2filme, bt1insere, bt2busca, bt3exclui, bt4atualiza, bt5lista,tbvfs);//,ta);
				
				rb1serie.addEventHandler(MouseEvent.MOUSE_CLICKED, fmc.getEvh());
				rb2filme.addEventHandler(MouseEvent.MOUSE_CLICKED, fmc.getEvh());
				
				bt1insere.addEventHandler(MouseEvent.MOUSE_CLICKED, fmc.getEvh());
				bt2busca.addEventHandler(MouseEvent.MOUSE_CLICKED, fmc.getEvh());
				bt3exclui.addEventHandler(MouseEvent.MOUSE_CLICKED, fmc.getEvh());
				bt4atualiza.addEventHandler(MouseEvent.MOUSE_CLICKED, fmc.getEvh());
				bt5lista.addEventHandler(MouseEvent.MOUSE_CLICKED, fmc.getEvh());
				
				rb1serie.selectedProperty().addListener(fmc.getEve());
				rb2filme.selectedProperty().addListener(fmc.getEve());
				rb2filme.setSelected(true);
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
