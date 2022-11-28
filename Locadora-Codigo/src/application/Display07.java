package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.ClPlan;
import Entity.Plano;
import app.pt2.ClientePlano;
import app.pt2.PlanoContro2;
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

public class Display07 {
	VBox vb;
	Label lbtitle;
	Button btmenu;
	Stage a;
	Display01 dp1;
	//--------------------------------------
	
	private List plan;
	
	private TextField tfCodigo;
	private ComboBox cbPlano;
	private DatePicker dpDatai;
	private DatePicker dpDataf;
	private TextField tfValor;
	//private TextArea ta;
	private TableView tbva;
	
	//private boolean vale;
	
	private LocalDate ld;
	
	public void setPlan(List plan) {
		this.plan = plan;
	}
	public Display07(Stage a) {
		this.a = a;
		//this.vale =  false;
	}
	public VBox getVb() {
		try {
			PlanoContro2 pc2 = new PlanoContro2();
			plan = pc2.getPlano();
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
		lbtitle = new Label("ASSINATURA");
		lbtitle.setStyle("-fx-font-size:40;-fx-text-fill:WHITE;");
		//--BP---------------------------------------------------------------------------------------------------
		bp.setTop(btmenu);
		bp.setCenter(lbtitle);
		bp.setAlignment(btmenu, Pos.TOP_CENTER);
		bp.setAlignment(lbtitle, Pos.CENTER);
		/////////////////////////////////////////////////////////////////////////////////
		//VB2----------------------------------------------------------------------------
		VBox vb2 = new VBox();
		vb2.setStyle("-fx-background-color:#588c7e;-fx-Border-color:588c7e;-fx-Border-width:10"
				+ ";-fx-Border-Radius:20;-fx-Background-Radius:30");
		//FLOWPANE - TEXTAREA-------------------
		//FlowPane fw1 = new FlowPane();
		//----------ta = new TextArea();
		
		tbva = new TableView();
		TableColumn<ClPlan, Integer> colclcod = new TableColumn("Codigo Cliente");
		colclcod.setCellValueFactory(new PropertyValueFactory("codigo"));
		
		TableColumn<ClPlan, String> colclnome = new TableColumn("Cliente Nome");
		colclnome.setCellValueFactory(new PropertyValueFactory("clnome"));
		
		TableColumn<ClPlan, String> colplano = new TableColumn("Plano");
		colplano.setCellValueFactory(new PropertyValueFactory("planoNome"));
		
		TableColumn<ClPlan, LocalDate> coldatai = new TableColumn("Data Inicio");
		coldatai.setCellValueFactory(new PropertyValueFactory("dataI"));
		
		TableColumn<ClPlan, LocalDate> coldataf = new TableColumn("Data Fim");
		coldataf.setCellValueFactory(new PropertyValueFactory("dataF"));
		
		TableColumn<ClPlan, Double> colppreco = new TableColumn("Preco");
		colppreco.setCellValueFactory(new PropertyValueFactory("ppreco"));
		
		TableColumn<ClPlan, String> colstatus = new TableColumn("Status");
		colstatus.setCellValueFactory(new PropertyValueFactory("Status"));
		
		tbva.getColumns().addAll(colclcod,colclnome,colplano,coldatai
				,coldataf,colppreco,colstatus);
		tbva.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		tbva.setPrefHeight(200);
		tbva.setPrefWidth(700);
		
		//fw1.getChildren().add(ta);
		//fw1.setMargin(ta, new Insets(20,0,20,0));
		//fw1.setAlignment(Pos.TOP_CENTER);
		//FLOWPANE INPUTS
		FlowPane fw2 = new FlowPane();
		//GRIDPANE
		GridPane gpl = new GridPane();
		Label lb1 = new Label("CODIGO - CLIENTE");
		lb1.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb2 = new Label("PLANO");
		lb2.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb3 = new Label("DATA - INICIO");
		lb3.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb4 = new Label("DATA - FIM");
		lb4.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		Label lb5 = new Label("VALOR");
		lb5.setStyle("-fx-font-size:10;-fx-text-fill:WHITE;");
		
		tfCodigo = new TextField();
		cbPlano = new ComboBox(FXCollections.observableArrayList(plan));
		cbPlano.getSelectionModel().select(0);
		dpDatai = new DatePicker();
		dpDataf = new DatePicker();
		tfValor = new TextField();
		
		gpl.addRow(0, lb1,tfCodigo);
		gpl.addRow(1, lb2,cbPlano);
		gpl.addRow(2, lb3,dpDatai);
		gpl.addRow(3, lb4,dpDataf);
		gpl.addRow(4, lb5,tfValor);
		gpl.setVgap(20);
		//GRIDPANE
		//------------GridPane gpr = new GridPane();
		FlowPane fpb = new FlowPane();
		Button btAssinar = new Button("Assinar");
		Button btAtualizar = new Button("Atualizar");
		Button btCancelar = new Button("Cancelar");
		Button btBuscar = new Button("Buscar");
		Button btListar = new Button("Listar");
		
		btAssinar.setPrefWidth(130);
		btAtualizar.setPrefWidth(130);
		btCancelar.setPrefWidth(130);
		btBuscar.setPrefWidth(130);
		btListar.setPrefWidth(130);
		
		
		fpb.getChildren().addAll(btAssinar, btAtualizar, btCancelar, btBuscar, btListar);
		fpb.setHgap(20);
		fpb.setVgap(20);
		
		fpb.setAlignment(Pos.CENTER);
		
		/*
		gpr.addRow(0, btAssinar);
		gpr.addRow(1, btAtualizar);
		gpr.addRow(2, btCancelar);
		gpr.addRow(3, btBuscar);
		gpr.addRow(4, btListar);
		gpr.setVgap(20);
		*/
		
		//FLOWPANE INPUTS
		//------------fw2.getChildren().addAll(gpl,ta,gpr);
		//------------------fw2.getChildren().addAll(gpl,tbva,gpr);
		fw2.getChildren().addAll(gpl,tbva);
		fw2.setAlignment(Pos.CENTER);
		fw2.setHgap(10);
		//-----------------fw2.setMargin(gpr, new Insets(20,0,20,0));
		fw2.setMargin(gpl, new Insets(20,0,20,0));
		//------------fw2.setMargin(ta, new Insets(20,0,20,0));
		
		fw2.setMargin(tbva, new Insets(20,0,20,0));
		//VB2-------------------------------------------------------------------------END
		vb2.getChildren().addAll(fw2,fpb);
		vb2.setMargin(fpb, new Insets(0,0,20,0));
		/////////////////////////////////////////////////////////////////////////////////
		
		ClientePlano cp = new ClientePlano(tfCodigo,cbPlano,dpDatai,dpDataf,tfValor,tbva);//ta);
		
		btAssinar.addEventHandler(MouseEvent.MOUSE_CLICKED,cp.getEvh());
		btAtualizar.addEventHandler(MouseEvent.MOUSE_CLICKED,cp.getEvh());
		btCancelar.addEventHandler(MouseEvent.MOUSE_CLICKED,cp.getEvh());
		btBuscar.addEventHandler(MouseEvent.MOUSE_CLICKED,cp.getEvh());
		btListar.addEventHandler(MouseEvent.MOUSE_CLICKED,cp.getEvh());
		
		cbPlano.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				//PessoaAtor2 psa2;
				PlanoContro2 pc2;
				try {
					pc2 = new PlanoContro2();
					plan = pc2.getPlano();
					//cbPlano = new ComboBox(FXCollections.observableArrayList(plan));
					cbPlano.setItems(FXCollections.observableArrayList(plan));
					cbPlano.getSelectionModel().select(0);
					System.out.println("Seleced");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		dpDataf.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				PlanoContro2 pc2;
				
				try {
					if(dpDatai.getValue() != null) {
						pc2 = new PlanoContro2();
						Plano p = new Plano();
						p.setNome(cbPlano.getValue().toString());
						p = pc2.buscaP(p);
						//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						ld = LocalDate.parse(dpDatai.getValue().toString());
						System.out.println(p.getPeriodo());
						String period = p.getPeriodo();
						if(p.getPeriodo().contains("semanal")) {
							System.out.println("-------------------=>"+p.getPeriodo());
							ld = ld.plusWeeks(1);
							
						}
						if(p.getPeriodo().contains("mensal")) {
							System.out.println("-------------------=>"+p.getPeriodo());
							ld = ld.plusMonths(1);
							
						}
						if(p.getPeriodo().contains("anual")) {
							System.out.println("-------------------=>"+p.getPeriodo());
							ld = ld.plusYears(1);
							
						}
						dpDataf.setValue(ld);
						
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
		
		EventHandler<MouseEvent> evhm = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				PlanoContro2 pc2;
				
				try {
					if(tfValor.getText().isEmpty() == false) {
						pc2 = new PlanoContro2();
						Plano p = new Plano();
						p.setNome(cbPlano.getValue().toString());
						p = pc2.buscaP(p);
						Double tfval = Double.parseDouble(tfValor.getText().toString());
						if(tfval < p.getPreco()) {
							//JOptionPane.showMessageDialog(null, "VALOR PAGO INFERIOR AO PLANO");
							tfValor.setStyle("-fx-background-color:red");
							btAssinar.removeEventHandler(MouseEvent.MOUSE_CLICKED, cp.getEvh());
							btAtualizar.removeEventHandler(MouseEvent.MOUSE_CLICKED, cp.getEvh());
						}
						else {
							tfValor.setStyle(null);
							btAssinar.addEventHandler(MouseEvent.MOUSE_CLICKED,cp.getEvh());
							btAtualizar.addEventHandler(MouseEvent.MOUSE_CLICKED,cp.getEvh());
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
			
		};
		
		btAssinar.addEventHandler(MouseEvent.MOUSE_ENTERED, evhm);
		btAtualizar.addEventHandler(MouseEvent.MOUSE_ENTERED, evhm);
		
		
		
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
