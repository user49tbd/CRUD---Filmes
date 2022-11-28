package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Effect.Clienteeffec;
import Entity.Serie;
import Entity.Temporada;
import app.pt2.FilmContro2;
import app.pt2.TempEp;
import app.pt2.TempEp2;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
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

public class Display03 {
	private TableView tbv;
	private TableView tbv2;
	VBox vb;
	Label lbtitle;
	Button btmenu;
	Stage a;
	Display01 dp1;
	//----------------------
	private RadioButton rbt;
	private RadioButton rbe;
	
	private TextField tfNome;
	private ComboBox cbTemp;
	private TextField tfQuant;
	
	private ComboBox cbNumEp;
	private TextField tfTitulo;
	private TextField tfTime;
	
	private List<Integer> plan;
	
	private List<Integer> epl;
	Clienteeffec ce;
	
	private String change ="1"; 
	
	public Display03(Stage a) {
		this.a = a;
		
	}
	public VBox getVb() {
		plan = new ArrayList();
		
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
		lbtitle = new Label("TEMPORADA - EPISODIO");
		lbtitle.setStyle("-fx-font-size:40;-fx-text-fill:WHITE;");
		//--BP---------------------------------------------------------------------------------------------------
		bp.setTop(btmenu);
		bp.setCenter(lbtitle);
		bp.setAlignment(btmenu, Pos.TOP_CENTER);
		bp.setAlignment(lbtitle, Pos.CENTER);
		
		////////////////////////////////////////////////////////////////////////////////////////////
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
				rbt = new RadioButton("Temporada");
				rbt.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				Label lb1 = new Label("CODIGO DA OBRA");
				lb1.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				Label lb2 = new Label("TEMPORADA");
				lb2.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				Label lb3 = new Label("QUANTIDADE EP");
				lb3.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL - TEXTFIELDS
				tfNome = new TextField();
				cbTemp = new ComboBox();
				tfQuant = new TextField();
				
				tfNome.setPrefWidth(230);
				cbTemp.setPrefWidth(230);
				tfQuant.setPrefWidth(230);
				
				//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPL------------------------------------------------------------------
				gpl.addRow(0,rbt);
				gpl.addRow(1, lb1,tfNome);
				gpl.addRow(2, lb2,cbTemp);
				gpl.addRow(3, lb3,tfQuant);
				gpl.setVgap(10);
				
				//RIGHT
				//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR-----------------------------------------------------------------
				GridPane gpr = new GridPane();
				//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPR - LABEL
				rbe = new RadioButton("Episodio");
				rbe.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				
				Label lb4 = new Label("NUMERO EP");
				lb4.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				Label lb5 = new Label("TITULO");
				lb5.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				Label lb6 = new Label("DURACAO");
				lb6.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
				
				//--VB2 - FLOWLAYOUT - GRIDPANELEFT GPR - OBJECTS
				cbNumEp = new ComboBox();
				tfTitulo = new TextField();
				//TextArea taDesc = new TextArea();
				tfTime = new TextField();
				
				cbNumEp.setPrefWidth(230);
				tfTitulo.setPrefWidth(230);
				tfTime.setPrefWidth(230);
				
				//--VB2 - FLOWLAYOUT - GRIDPANERIGHT GPR------------------------------------------------------------------
				gpr.addRow(0, rbe);
				gpr.addRow(1, lb4,cbNumEp);
				gpr.addRow(2, lb5,tfTitulo);
				gpr.addRow(3, lb6,tfTime);
				cbNumEp.setDisable(true);
				tfTitulo.setDisable(true);
				tfTime.setDisable(true);
				gpr.setVgap(10);
				//--VB2 - FLOWLAYOUT FW----------------------------------------------------------------------------------
				fw.getChildren().addAll(gpl,gpr);
				fw.setAlignment(Pos.CENTER);
				fw.setHgap(200);
				//--VB2 - FLOWLAYOUT ------------------------------------------------------------------------------------
				
				//--VB2 - FLOWLAYOUT3 FW3---------------------------------------------------------------------------------
				FlowPane fw2 = new FlowPane();
				//--VB2 - FLOWLAYOUT3 - BUTTON
				Button btInserir = new Button("Inserir");
				Button btAtualizar = new Button("Atualizar");
				Button btExcluir = new Button("Excluir");
				Button btBuscar = new Button("Buscar");
				Button btListar = new Button("Listar");
				
				btInserir.setPrefWidth(130);
				btAtualizar.setPrefWidth(130);
				btExcluir.setPrefWidth(130);
				btBuscar.setPrefWidth(130);
				btListar.setPrefWidth(130);
				
				
				//--VB2 - FLOWLAYOUT3 - BUTTON
				fw2.getChildren().addAll(btInserir,btBuscar,btExcluir,btAtualizar,btListar);
				fw2.setAlignment(Pos.CENTER);
				fw2.setHgap(20);
				//--VB2 - FLOWLAYOUT3 FW3---------------------------------------------------------------------------------
				ToggleGroup tg = new ToggleGroup();
				
				rbe.setToggleGroup(tg);
				rbt.setToggleGroup(tg);
				rbt.setSelected(true);
				//--VB2 - FLOWLAYOUT4 FW4---------------------------------------------------------------------------------
				FlowPane fw4 = new FlowPane();
				
				//ta = new TextArea();
				//ta.setPrefWidth(950);
				//ta.setPrefHeight(195);
				//fw4.getChildren().add(ta);
				
				
				tbv = new TableView();
				tbv2 = new TableView();
				if(change == "1") {
					tbv.setPrefWidth(950);
					tbv.setPrefHeight(195);
					fw4.getChildren().add(tbv);
				}
				else {
					tbv.setPrefWidth(950);
					tbv.setPrefHeight(80);
					
					tbv2.setPrefWidth(950);
					tbv2.setPrefHeight(115);
					fw4.getChildren().addAll(tbv,tbv2);
				}
				
				
				
				
				fw4.setAlignment(Pos.CENTER);
				
				vb2.getChildren().addAll(fw,fw2,fw4);
				vb2.setMargin(fw, new Insets(10,0,0,0));
				vb2.setMargin(fw2, new Insets(10,0,0,0));
				vb2.setMargin(fw4, new Insets(10,0,10,0));
				//STYES
		////////////////////////////////////////////////////////////////////////////////////////////
				ce = new Clienteeffec();
				ce.disab(cbNumEp, tfTitulo, tfTime, rbt, rbe);
				TempEp tep = new TempEp(rbt,rbe,tfNome,cbTemp,tfQuant,
						cbNumEp,tfTitulo,tfTime,tbv,tbv2,a);//ta);
				btInserir.addEventHandler(MouseEvent.MOUSE_CLICKED, tep.getEvh());
				btAtualizar.addEventHandler(MouseEvent.MOUSE_CLICKED, tep.getEvh());
				btExcluir.addEventHandler(MouseEvent.MOUSE_CLICKED, tep.getEvh());
				btBuscar.addEventHandler(MouseEvent.MOUSE_CLICKED, tep.getEvh());
				btListar.addEventHandler(MouseEvent.MOUSE_CLICKED, tep.getEvh());
				cbTemp.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<Event>() {
					@Override
					public void handle(Event ar) {
						// TODO Auto-generated method stub
						vetini();
					}
				});
					ce = new Clienteeffec();
					ce.disab(cbNumEp, tfTitulo, tfTime, rbt, rbe);
					
					cbNumEp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						vecepini();
					}
					
				});
				
				
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
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public Stage getA() {
		return a;
	}
	public void setA(Stage a) {
		this.a = a;
	}
	public TableView getTbv() {
		return tbv;
	}
	public TableView getTbv2() {
		return tbv2;
	}
	public TextField getTfNome() {
		return tfNome;
	}
	public void setTfNome(TextField tfNome) {
		this.tfNome = tfNome;
	}
	public ComboBox getCbTemp() {
		return cbTemp;
	}
	public void setCbTemp(ComboBox cbTemp) {
		this.cbTemp = cbTemp;
	}
	public TextField getTfQuant() {
		return tfQuant;
	}
	public void setTfQuant(TextField tfQuant) {
		this.tfQuant = tfQuant;
	}
	public RadioButton getRbt() {
		return rbt;
	}
	public void setRbt(RadioButton rbt) {
		this.rbt = rbt;
	}
	public RadioButton getRbe() {
		return rbe;
	}
	public void setRbe(RadioButton rbe) {
		this.rbe = rbe;
	}
	
	
	public ComboBox getCbNumEp() {
		return cbNumEp;
	}
	public void setCbNumEp(ComboBox cbNumEp) {
		this.cbNumEp = cbNumEp;
	}
	public TextField getTfTitulo() {
		return tfTitulo;
	}
	public void setTfTitulo(TextField tfTitulo) {
		this.tfTitulo = tfTitulo;
	}
	public TextField getTfTime() {
		return tfTime;
	}
	public void setTfTime(TextField tfTime) {
		this.tfTime = tfTime;
	}
	public Clienteeffec getCe() {
		return ce;
	}
	public void vetini() {
		try {
			plan.clear();
			FilmContro2 fc2 = new FilmContro2();
			Serie s = new Serie();
			if(tfNome.getText().isEmpty()==false) {
			s.setCodigo(Integer.parseInt(tfNome.getText()));
			int p = fc2.BuscaNumTemp(s);
			System.out.println(fc2.BuscaNumTemp(s));
			if(p >= 1) {
			for(int i =1;i<=fc2.BuscaNumTemp(s);i++) {
				System.out.println(i);
				plan.add(i);
			}
			cbTemp.setItems(FXCollections.observableArrayList(plan));
			cbTemp.getSelectionModel().select(0);
			System.out.println("Seleced");
			}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void vecepini() {
		if(tfNome.getText().isEmpty() == false && cbTemp.getValue() != null) {
			Temporada t = new Temporada();
			List<Temporada> lt = new ArrayList();
			t.setObraCodigo(Integer.parseInt(tfNome.getText()));
			
			try {
				TempEp2 te2 = new TempEp2();
				lt = te2.BuscarT(t);
				for(Temporada t1 : lt) {
					if(t1.getTempnum() == Integer.parseInt(cbTemp.getValue().toString())) {
						if(t1.getTotEp()>=1) {
							epl = new ArrayList();
							int ep = t1.getTotEp();
							for(int i =1;i<=ep;i++) {
								epl.add(i);
								System.out.println("Episodio num "+epl.get(i-1));
							}
							cbNumEp.setItems(FXCollections.observableArrayList(epl));
						}
						break;
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	}
