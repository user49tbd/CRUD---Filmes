package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Display01 {
	Mainn m1 = new Mainn();
	VBox vb = new VBox();
	Button bt1 = new Button("FILME - SERIE");
	Button bt2 = new Button("TEMPORADA - SERIE");
	Button bt3 = new Button("CLIENTE");
	Button bt4 = new Button("ATOR");
	Button bt6 = new Button("PLANO");
	Button bt7 = new Button("ASSINATURA");
	Button bt8 = new Button("CATALOGO");
	Stage a;
	Display02 dp2;
	Display03 dp3;
	Display04 dp4;
	Display05 dp5;
	Display06 dp6;
	Display07 dp7;
	Display08 dp8;
	public Display01(Stage a) {
		this.a = a;
	}
	public VBox getVb() {
		vb = new VBox();
		vb.setStyle("-fx-background-color:WHITE");
		Label lbtitle;
		//--BP
		BorderPane bp = new BorderPane();
		bp.setStyle("-fx-background-color:#588c7e;-fx-Border-color:#96ceb4;-fx-Border-width:10"
				+ ";-fx-Border-Radius:20;-fx-Background-Radius:30");
		lbtitle = new Label("C.R.U.D");
		lbtitle.setStyle("-fx-font-size:40;-fx-text-fill:WHITE;");
		//--BP---------------------------------------------------------------------------------------------------
		bp.setCenter(lbtitle);
		bp.setAlignment(lbtitle, Pos.CENTER);
		
		//-----------------------------------------------------
		GridPane gp = new GridPane();
		gp.addRow(0, bt1,bt2,bt3);
		gp.addRow(1, bt4,bt6,bt7);
		gp.setHgap(20);
		gp.setVgap(20);
		FlowPane fwp = new FlowPane();
		fwp.getChildren().addAll(gp);
		fwp.setAlignment(Pos.TOP_CENTER);
		bt1.setPrefWidth(230);
		bt1.setStyle("-fx-background-color:#588c7e;-fx-text-fill:WHITE;-fx-background-radius:20");
		bt2.setPrefWidth(230);
		bt2.setStyle("-fx-background-color:#588c7e;-fx-text-fill:WHITE;-fx-background-radius:20");
		bt3.setPrefWidth(230);
		bt3.setStyle("-fx-background-color:#588c7e;-fx-text-fill:WHITE;-fx-background-radius:20");
		bt4.setPrefWidth(230);
		bt4.setStyle("-fx-background-color:#588c7e;-fx-text-fill:WHITE;-fx-background-radius:20");
		bt6.setPrefWidth(230);
		bt6.setStyle("-fx-background-color:#588c7e;-fx-text-fill:WHITE;-fx-background-radius:20");
		bt7.setPrefWidth(230);
		bt7.setStyle("-fx-background-color:#588c7e;-fx-text-fill:WHITE;-fx-background-radius:20");
		bt8.setPrefWidth(230);
		bt8.setStyle("-fx-background-color:#588c7e;-fx-text-fill:WHITE;-fx-background-radius:20");
		
		bt1.setPrefHeight(50);
		bt2.setPrefHeight(50);
		bt3.setPrefHeight(50);
		bt4.setPrefHeight(50);
		bt6.setPrefHeight(50);
		bt7.setPrefHeight(50);
		bt8.setPrefHeight(50);
		
		vb.getChildren().addAll(bp,fwp,bt8);
		vb.setMargin(fwp, new Insets(20,0,0,0));
		vb.setMargin(bt8, new Insets(20,0,0,0));
		vb.setAlignment(Pos.CENTER);
		
		dp2 = new Display02(a);
		dp3 = new Display03(a);
		dp4 = new Display04(a);
		dp5 = new Display05(a);
		dp6 = new Display06(a);
		dp7 = new Display07(a);
		dp8 = new Display08(a);
		
		bt1.addEventHandler(MouseEvent.MOUSE_CLICKED, evh);
		bt2.addEventHandler(MouseEvent.MOUSE_CLICKED, evh);
		bt3.addEventHandler(MouseEvent.MOUSE_CLICKED, evh);
		bt4.addEventHandler(MouseEvent.MOUSE_CLICKED, evh);
		bt6.addEventHandler(MouseEvent.MOUSE_CLICKED, evh);
		bt7.addEventHandler(MouseEvent.MOUSE_CLICKED, evh);
		bt8.addEventHandler(MouseEvent.MOUSE_CLICKED, evh);
		//-----------------------------------------------
		return vb;
	}
	public void change(Stage a,VBox vb2) {
		Scene sc = new Scene(vb2,1000,500);
		a.setScene(sc);
	}
	EventHandler<MouseEvent> evh = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent ar) {
			// TODO Auto-generated method stub
			if(ar.getSource().toString().contains("FILME - SERIE")) {
				System.out.println("ok");
				change(a,dp2.getVb());
			}
			if(ar.getSource().toString().contains("TEMPORADA - SERIE")) {
				System.out.println("ok");
				change(a,dp3.getVb());
			}
			if(ar.getSource().toString().contains("PLANO")) {
				System.out.println("ok");
				change(a,dp6.getVb());
			}
			if(ar.getSource().toString().contains("CLIENTE")) {
				System.out.println("ok");
				change(a,dp4.getVb());
			}
			if(ar.getSource().toString().contains("ATOR")) {
				System.out.println("ok");
				change(a,dp5.getVb());
			}
			if(ar.getSource().toString().contains("ASSINATURA")) {
				System.out.println("ok");
				change(a,dp7.getVb());
			}
			if(ar.getSource().toString().contains("CATALOGO")) {
				System.out.println("ok");
				change(a,dp8.getVb());
			}
		}
		};

}

	
	
