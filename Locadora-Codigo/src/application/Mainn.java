package application;

import java.sql.Connection;
import java.sql.SQLException;

import app.pt2.FilmContro;
import app.pt2.classcontro;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Mainn extends Application{
	private TextField tfCodigo;
	private TextField tfTitulo;
	private TextField tfGenero;
	private TextField tfNumtemp;
	private DatePicker dpData;
	private Slider slAvaliacao;
	private Scene sc;
	private TextArea ta;
	
	//-----------------------------------
	private TextField tf1tb2;
	private TextField tf2tb2;
	private TextField tf3tb2;
	
	private TextField tf4tb2;
	private TextField tf5tb2;
	private TextField tf6tb2;
	private TextField tf7tb2;
	private TextField tf8tb2;
	//-----------------------------------
	private TextField tf1g;
	private TextField tf2g;
	private TextField tf3g;
	
	private TextArea tag1;
	private TextArea tag2;
	
	private TextArea ta2;
	public static void main(String[]args) {
		launch(args);
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Display01 d1 = new Display01(arg0);
		sc = new Scene(d1.getVb(),1000,500);
		arg0.setScene(sc);
		arg0.show();
		arg0.setResizable(false);
	}
	
	
}
