package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Plano;
import app.pt2.Catalogo;
import app.pt2.FilmContro2;
import app.pt2.PlanoContro2;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Display08 {
	VBox vb;
	Label lbtitle;
	Button btmenu;
	Stage a;
	Display01 dp1;
	private PlanoContro2 pc2;
	//----------------------------
	private List plan;
	private List oblist;
	
	
	private ComboBox cbPlan;
	private ComboBox cbObra;
	private TextField tfbuscaobra;
	Label obtype;
	
	private String change = "1";
	private int ic =0;
	private TableView tbvc;
	private TableView tbvc2;
	
	private ChangeListener<String> chl;
	
	public void setPlan(List plan) {
		this.plan = plan;
	}
	
	public void setOblist(List oblist) {
		this.oblist = oblist;
	}

	public Display08(Stage a) {
		this.a = a;
	}
	public VBox getVb() {
		obtype = new Label("");
		try {
			pc2 = new PlanoContro2();
			plan = pc2.getPlano();
			oblist = new ArrayList();
			oblist.clear();
			if(plan.size() > 0) {
				FilmContro2 fc2 = new FilmContro2();
				Plano p = new Plano();
				p.setNome(plan.get(0).toString());
				p = pc2.buscaP(p);
				oblist = fc2.getObra("F",p);
				oblist.clear();
				obtype.setText(p.getTpobra());
				bsec(p.getTpobra(),obtype);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		lbtitle = new Label("CATALOGO");
		lbtitle.setStyle("-fx-font-size:40;-fx-text-fill:WHITE;");
		//--BP---------------------------------------------------------------------------------------------------
		bp.setTop(btmenu);
		bp.setCenter(lbtitle);
		bp.setAlignment(btmenu, Pos.TOP_CENTER);
		bp.setAlignment(lbtitle, Pos.CENTER);
		/////////////////////////////////////////////////////////////////////////////////
		//VB2
		VBox vb2 = new VBox();
		vb2.setStyle("-fx-background-color:#588c7e;-fx-Border-color:588c7e;-fx-Border-width:10"
				+ ";-fx-Border-Radius:20;-fx-Background-Radius:30");
		//FLOWPANE FW1
		
		FlowPane fw1 = new FlowPane();
		GridPane gpm1 = new GridPane();
		Label lb1 = new Label("Plano");
		lb1.setStyle("-fx-font-size:15;-fx-text-fill:WHITE;");
		Label lb2 = new Label("Obra");
		lb2.setStyle("-fx-font-size:15;-fx-text-fill:WHITE;");
		obtype.setStyle("-fx-font-size:15;-fx-text-fill:WHITE;");
		tfbuscaobra = new TextField();
		
		cbPlan = new ComboBox(FXCollections.observableArrayList(plan));
		cbPlan.getSelectionModel().select(0);
		cbObra = new ComboBox(FXCollections.observableArrayList(oblist));
		cbObra.getSelectionModel().select(0);
		
		cbPlan.setPrefWidth(220);
		cbObra.setPrefWidth(220);
		//FLOWPANE FW1 -----------------------------------------------------------------
		gpm1.addRow(0,lb1,cbPlan,lb2,cbObra,tfbuscaobra);
		fw1.getChildren().addAll(gpm1,obtype);
		fw1.setAlignment(Pos.CENTER);
		gpm1.setHgap(20);
		fw1.setMargin(obtype, new Insets(20,0,0,0));
		//FLOWPANE FW2
		FlowPane fw2 = new FlowPane();
		
		tbvc = new TableView();
		tbvc2 = new TableView();
		if(change == "1") {
			tbvc.setPrefWidth(750);
			tbvc.setPrefHeight(220);
			fw2.getChildren().add(tbvc);
		}
		else {
			tbvc.setPrefWidth(750);
			tbvc2.setPrefWidth(750);
			
			tbvc.setPrefHeight(50);
			tbvc2.setPrefHeight(160);
			
			fw2.getChildren().addAll(tbvc,tbvc2);
		}
		//FLOWPANE FW2 -----------------------------------------------------------------
		fw2.setAlignment(Pos.CENTER);
		fw2.setVgap(20);
		//FLOWPANE FW3
		FlowPane fw3 = new FlowPane();
		Button bt1Inserir = new Button("Inserir");
		Button bt2Excluir = new Button("Excluir");
		Button bt3Buscar = new Button("Buscar");
		Button bt4Listar = new Button("Listar");
		
		bt1Inserir.setPrefWidth(130);
		bt2Excluir.setPrefWidth(130);
		bt3Buscar.setPrefWidth(130);
		bt4Listar.setPrefWidth(130);
		//FLOWPANE FW3 -----------------------------------------------------------------
		fw3.getChildren().addAll(bt1Inserir,bt2Excluir,bt3Buscar,bt4Listar);
		fw3.setAlignment(Pos.CENTER);
		fw3.setHgap(10);
		//VB2---------------------------------------------------------------------------
		FlowPane fw4 = new FlowPane();
		GridPane gp = new GridPane();
		gp.addRow(0, fw1);
		gp.addRow(1, fw2);
		gp.addRow(2, fw3);
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(10);
		gp.setMargin(fw1, new Insets(20,0,0,0));
		gp.setMargin(fw3, new Insets(0,0,20,0));
		vb2.getChildren().addAll(gp);
		/////////////////////////////////////////////////////////////////////////////////
		
		
		Catalogo cat = new Catalogo(cbPlan, cbObra, tbvc,tfbuscaobra,a);//ta,tfbuscaobra);
		
		bt1Inserir.addEventHandler(MouseEvent.MOUSE_CLICKED,cat.getEvh());
		bt2Excluir.addEventHandler(MouseEvent.MOUSE_CLICKED,cat.getEvh());
		bt3Buscar.addEventHandler(MouseEvent.MOUSE_CLICKED,cat.getEvh());
		bt4Listar.addEventHandler(MouseEvent.MOUSE_CLICKED,cat.getEvh());
		
			chl = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue ov, String arg1, String arg2) {
				// TODO Auto-generated method stub
				PlanoContro2 pc2;
				try {
					pc2 = new PlanoContro2();
					plan = pc2.getPlano();
					cbPlan.setItems(FXCollections.observableArrayList(plan));
					Plano p = new Plano();
					p.setNome(cbPlan.getValue().toString());
					p = pc2.buscaP(p);
					if(p.getNome() != null) {
						obtype.setText(p.getTpobra());
						bsec(p.getTpobra(),obtype);
						System.out.println("Seleced");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		cbPlan.valueProperty().addListener(chl);
		System.out.println("--22>>"+ic);
		cbPlan.getSelectionModel().select(ic);
		cbObra.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				FilmContro2 fc2;
				try {
					if(cbPlan.getValue() != null) {
					fc2 = new FilmContro2();
					oblist.clear();
					Plano p = new Plano();
					p.setNome(cbPlan.getValue().toString());
					p = pc2.buscaP(p);
					if(obtype.getText().contains("/") || obtype.getText().contains("F")) {
						for(String c : fc2.getObra("F",p)) {
							if(tfbuscaobra.getText().isEmpty() == false) {
								String [] ar = c.split("/");
								for(String h : ar) {
									System.out.println("------------->"+h);
								}
								if(ar[1].contains(tfbuscaobra.getText())) {
									oblist.add(c);
								}
							}
							else {
									oblist.add(c);
							}
						}
					}
					if(obtype.getText().contains("/") || obtype.getText().contains("S")) {
						for(String c : fc2.getObra("S",p)) {
							if(tfbuscaobra.getText().isEmpty() == false) {
								String [] ar = c.split("/");
								System.out.println(ar[1]);
								if(ar[1].contains(tfbuscaobra.getText())) {
									oblist.add(c);
								}
							}
							else {
									oblist.add(c);
							}
						}
					}
					cbObra.setItems(FXCollections.observableArrayList(oblist));
					cbObra.getSelectionModel().select(0);
					System.out.println("Seleced----------");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
	public void bsec(String val,Label lb) {
		if(val.contains("F")){
			lb.setText("Filme");
		}
		if(val.contains("S")){
			lb.setText("Series");
		}
		if(val.contains("D")){
			lb.setText("Filme | Serie");
		}
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

	public TableView getTbvc() {
		return tbvc;
	}

	public TableView getTbvc2() {
		return tbvc2;
	}

	public ComboBox getCbPlan() {
		return cbPlan;
	}

	public void setCbPlan(ComboBox cbPlan) {
		this.cbPlan = cbPlan;
	}

	public ChangeListener<String> getChl() {
		return chl;
	}

	public void setChl(ChangeListener<String> chl) {
		this.chl = chl;
	}

	public int getIc() {
		return ic;
	}

	public void setIc(int ic) {
		this.ic = ic;
	}
	
	
	
	
	
}
