package app.pt2;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.Ator;
import Entity.Filme;
import Entity.Serie;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class FilmContro {
	private TextField tfcodigo;
	private TextField tftitulo;
	private TextField tfgenero;
	private TextField tfnumtemp;
	
	private DatePicker dpdata;
	private Slider slavaliacao;
	
	private RadioButton rb1;
	private RadioButton rb2;
	
	private Button btinsere;
	private Button btbusca;
	private Button btexclui;
	private Button btatualiza;
	private Button btlista;
	private FilmContro2 fmc2;
	//private TextArea ta;
	private TableView tbvfs;
	
	
	private FilmContro1_2 fc12;
	
	public FilmContro(TextField tfcodigo, TextField tftitulo, TextField tfgenero, TextField tfnumtemp,
			DatePicker dpdata, Slider slavaliacao, RadioButton rb1, RadioButton rb2, Button btinsere, Button btbusca,
			Button btexclui, Button btatualiza, Button btlista,TableView tbvfs){
		super();
		this.tfcodigo = tfcodigo;
		this.tftitulo = tftitulo;
		this.tfgenero = tfgenero;
		this.tfnumtemp = tfnumtemp;
		this.dpdata = dpdata;
		this.slavaliacao = slavaliacao;
		this.rb1 = rb1;
		this.rb2 = rb2;
		this.btinsere = btinsere;
		this.btbusca = btbusca;
		this.btexclui = btexclui;
		this.btatualiza = btatualiza;
		this.btlista = btlista;
		//this.ta = ta;
		this.tbvfs = tbvfs;
		
	}
	EventHandler<MouseEvent> evh = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent ar) {
			// TODO Auto-generated method stub
			try {
				fmc2 = new FilmContro2();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(ar.getSource());
			Filme f = new Filme();
			Serie s = new Serie();
			if((ar.getSource().toString().contains("INSERIR") 
					|| ar.getSource().toString().contains("ATUALIZAR"))&&
				(tfcodigo.getText().isEmpty() || tftitulo.getText().isEmpty() ||
						tfgenero.getText().isEmpty() || dpdata.getValue() == null
						)) {
					JOptionPane.showMessageDialog(null, "Insira os valores nos campos");
					
				}
			else {
				if ((ar.getSource().toString().contains("BUSCAR") || 
						ar.getSource().toString().contains("EXCLUIR")) && 
						(tfcodigo.getText().isEmpty())
						) {
					JOptionPane.showMessageDialog(null, "Insira o CODIGO");
				}
				else {
					
					fc12 = new FilmContro1_2(tfcodigo,tftitulo,tfgenero,tfnumtemp,
							dpdata, slavaliacao,rb1,rb2,btinsere,btbusca,
							btexclui,btatualiza, btlista,fmc2,tbvfs);
					if (ar.getSource().toString().contains("LISTAR")){
						//---------------------------------------------------------
						fc12.FilmeSerieListar(rb1);
					}
					//OP - SERIES
					//-------------------------------------------------------------------------------------------
					if (rb1.isSelected()) {
						if(tfnumtemp.getText().isEmpty() && (ar.getSource().toString().contains("INSERIR")
								|| ar.getSource().toString().contains("ATUALIZAR"))) {
							JOptionPane.showMessageDialog(null, "numero de temporadas nao pode ser nulo");
						}
						else {
							//INSERIR SERIE
							if (ar.getSource().toString().contains("INSERIR")) {
								fc12.InsereSerie();
								}
							//ATUALIZA SERIE
							if (ar.getSource().toString().contains("ATUALIZA")) {
								fc12.AtualizaSerie();
							}
						}
						//BUSCA SERIES
						if (ar.getSource().toString().contains("BUSCAR")) {
							fc12.BuscaSeries();
						}
						//EXCLUI SERIE
						if (ar.getSource().toString().contains("EXCLUIR")) {
							fc12.ExcluiSerie();
						}
					}else {
						//OP - SERIES
						//---------------------------------------------------------------------------------------
						//INSERIR FILME
						if (ar.getSource().toString().contains("INSERIR")) {
							fc12.InsereFilme();;
					}
						//ATUALIZA FILME
						if (ar.getSource().toString().contains("ATUALIZA")) {
							fc12.AtualizaFilme();
						}
						//BUSCA FILME 
						if (ar.getSource().toString().contains("BUSCAR")) {
							fc12.BuscaFilme();
						}
						//EXCLUI FILME
						if (ar.getSource().toString().contains("EXCLUIR")) {
							fc12.ExcluiFilme();
						}
						
					}
				}
			}
		}
	};
	ChangeListener<Boolean> eve = new ChangeListener<>() {

		@Override
		public void changed(ObservableValue<? extends Boolean> obs, Boolean oldV, Boolean newV) {
			// TODO Auto-generated method stub
			if(newV == true && rb1.isSelected()) {
				tfnumtemp.setEditable(true);
				tfnumtemp.setStyle("-fx-Background-color:Red");
				tbvfs.getColumns().clear();
				tbvfs.getItems().clear();
				TableColumn<String, Integer> colcodigo = new TableColumn<>("Codigo");
				colcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
				
				TableColumn<Serie, String> coltitulo = new TableColumn<>("Titulo");
				coltitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
				TableColumn<Serie, LocalDate> coldata = new TableColumn<>("Data");
				coldata.setCellValueFactory(new PropertyValueFactory<>("data"));
				TableColumn<Serie, Double> colavaliacao = new TableColumn<>("Avaliacao");
				colavaliacao.setCellValueFactory(new PropertyValueFactory<>("avaliacao"));
				TableColumn<Serie, String> colGenero = new TableColumn<>("Genero");
				colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
				TableColumn<Serie, Integer> colTemporada = new TableColumn<>("Numero de Temporadas");
				colTemporada.setCellValueFactory(new PropertyValueFactory<>("numeroTemporadas"));
				
				tbvfs.getColumns().addAll(colcodigo,coltitulo,coldata,colavaliacao,
						colGenero,colTemporada);
				tbvfs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			}
			else {
				tfnumtemp.setEditable(false);
				tfnumtemp.setStyle("-fx-Background-color:GREEN");
				tbvfs.getColumns().clear();
				tbvfs.getItems().clear();
				TableColumn<Filme, Integer> colcodigo = new TableColumn<>("Codigo");
				colcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
				
				TableColumn<Filme, String> coltitulo = new TableColumn<>("Titulo");
				coltitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
				TableColumn<Filme, LocalDate> coldata = new TableColumn<>("Data");
				coldata.setCellValueFactory(new PropertyValueFactory<>("data"));
				TableColumn<Filme, Double> colavaliacao = new TableColumn<>("Avaliacao");
				colavaliacao.setCellValueFactory(new PropertyValueFactory<>("avaliacao"));
				TableColumn<Filme, String> colGenero = new TableColumn<>("Genero");
				colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
				
				tbvfs.getColumns().addAll(colcodigo,coltitulo,coldata,colavaliacao,
						colGenero);
				tbvfs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			}
		}

		
		
	};
	
	public EventHandler<MouseEvent> getEvh() {
		return evh;
	}
	

	public ChangeListener<Boolean> getEve() {
		return eve;
	}
	
	
}
