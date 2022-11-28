package application;

import Entity.Cliente;
import Entity.Plano;
import app.pt2.PlanoContro;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Display06 {
	VBox vb;
	Label lbtitle;
	Button btmenu;
	Stage a;
	Display01 dp1;
	//-----------------------------------------------------------
	private TextField tfNomeP;
	private ComboBox cbPeriodo;
	private TextField tfObraMed;
	private TextField tfQuant;
	private ComboBox cbQuali;
	private TextField tfPreco;
	private TableView tbvp;
	private RadioButton rbf;
	private RadioButton rbs;
	
	public Display06(Stage a) {
		this.a = a;
	}
	public VBox getVb() {
		
		String [] period = {"SEMANAL","MENSAL","ANUAL"};
		String [] quali = {"1080p","720p","480p","360p","240p","144p"};
		//-----------------------------------
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
		lbtitle = new Label("PLANO");
		lbtitle.setStyle("-fx-font-size:40;-fx-text-fill:WHITE;");
		//--BP---------------------------------------------------------------------------------------------------
		bp.setTop(btmenu);
		bp.setCenter(lbtitle);
		bp.setAlignment(btmenu, Pos.TOP_CENTER);
		bp.setAlignment(lbtitle, Pos.CENTER);
		////////////////////////////////////////////////////////////////////////////
		
		
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
		Label lb1 = new Label("NOME");
		lb1.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb2 = new Label("PERIODO");
		lb2.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb3 = new Label("MEDIA");
		lb3.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb4 = new Label("QUANTIDADE");
		lb4.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb5 = new Label("QUALIDADE");
		lb5.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb6 = new Label("PRECO");
		lb6.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL - TEXTFIELDS
		tfNomeP = new TextField();
		cbPeriodo =  new ComboBox(FXCollections.observableArrayList(period));
		cbPeriodo.getSelectionModel().select(0);
		tfObraMed = new TextField();
		tfQuant = new TextField(); 
		cbQuali =  new ComboBox(FXCollections.observableArrayList(quali));
		cbQuali.getSelectionModel().select(0);
		tfPreco = new TextField();
		
		
		
		tfNomeP.setPrefWidth(230);
		cbPeriodo.setPrefWidth(230);
		tfObraMed.setPrefWidth(230);
		tfQuant.setPrefWidth(230);
		cbQuali.setPrefWidth(230);
		tfPreco.setPrefWidth(230);
		
		//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL------------------------------------------------------------------
		gpl.addRow(0, lb1,tfNomeP);
		gpl.addRow(1, lb2,cbPeriodo);
		gpl.addRow(2, lb3,tfObraMed);
		gpl.addRow(3, lb4,tfQuant);
		gpl.addRow(4, lb5,cbQuali);
		gpl.addRow(5, lb6,tfPreco);
		gpl.setVgap(20);
		
		//RIGHT
		//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR-----------------------------------------------------------------
		GridPane gpr = new GridPane();
		//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPR - OBJECTS
		rbf = new RadioButton("Filme");
		rbf.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		
		rbs = new RadioButton("Episodio");
		rbs.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		
		tbvp = new TableView();
		tbvp.setPrefHeight(215);
			////////////------------
			TableColumn<Plano,String> colPlano = new TableColumn("Plano");
			
			TableColumn<Plano,String> colNome = new TableColumn("Nome");
			colNome.setCellValueFactory(new PropertyValueFactory("nome"));
			
			TableColumn<Plano,String> colPeriodo = new TableColumn("Periodo");
			colPeriodo.setCellValueFactory(new PropertyValueFactory("periodo"));
			
			TableColumn<Plano,Integer> colMedia = new TableColumn("Media");
			colMedia.setCellValueFactory(new PropertyValueFactory("obraMedia"));
			
			TableColumn<Plano,Integer> colQuant = new TableColumn("Quantidade");
			colQuant.setCellValueFactory(new PropertyValueFactory("quant"));
			
			TableColumn<Plano,String> colQuali = new TableColumn("Qualidade");
			colQuali.setCellValueFactory(new PropertyValueFactory("quali"));
			
			TableColumn<Plano,Double> colPreco = new TableColumn("Preco");
			colPreco.setCellValueFactory(new PropertyValueFactory("preco"));
			
			
			TableColumn<Plano,String> colTpobra = new TableColumn("Tipo_Obra");
			colTpobra.setCellValueFactory(new PropertyValueFactory("tpobra"));
			
			colPlano.getColumns().addAll(colNome,colPeriodo,colMedia,colQuant,colQuali,colPreco,
					colTpobra);
			
			tbvp.getColumns().add(colPlano);
			tbvp.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			////////////------------
		
		
		
		//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR------------------------------------------------------------------
		FlowPane fwgpr = new FlowPane();
		fwgpr.getChildren().addAll(rbf,rbs);
		fwgpr.setAlignment(Pos.TOP_CENTER);
		fwgpr.setHgap(40);
		gpr.addRow(0, fwgpr);
		gpr.addRow(1, tbvp);
		gpr.setVgap(20);
		
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
		
		/////////////////////////////////////////////////////////////////////////////
		
		PlanoContro pc = new PlanoContro(tfNomeP,cbPeriodo,tfObraMed,tfQuant,cbQuali,
				tfPreco,/*ta,*/tbvp,rbf,rbs);
		
		bt1Inserir.addEventFilter(MouseEvent.MOUSE_CLICKED, pc.getRvh());
		bt2Atualizar.addEventFilter(MouseEvent.MOUSE_CLICKED, pc.getRvh());
		bt3Excluir.addEventFilter(MouseEvent.MOUSE_CLICKED, pc.getRvh());
		bt4Buscar.addEventFilter(MouseEvent.MOUSE_CLICKED, pc.getRvh());
		bt5Listar.addEventFilter(MouseEvent.MOUSE_CLICKED, pc.getRvh());
		
		
		
		
		
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
