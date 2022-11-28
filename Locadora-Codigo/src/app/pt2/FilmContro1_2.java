package app.pt2;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.Filme;
import Entity.Serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FilmContro1_2 {
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
	private TableView tbvfs;
	private Filme f = new Filme();
	private Serie s = new Serie();
	public FilmContro1_2(TextField tfcodigo, TextField tftitulo, TextField tfgenero, TextField tfnumtemp,
			DatePicker dpdata, Slider slavaliacao, RadioButton rb1, RadioButton rb2, Button btinsere, Button btbusca,
			Button btexclui, Button btatualiza, Button btlista, FilmContro2 fmc2, TableView tbvfs){
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
		this.fmc2 = fmc2;
		this.tbvfs = tbvfs;
	}
	public void FilmeSerieListar(RadioButton rb1) {
		if(rb1.isSelected()) {
			try {
			System.out.println("Listando series");
			List <Serie> ls = fmc2.ListarS();
			StringBuffer sb1 = new StringBuffer();
			List <String> gen = new ArrayList();
			ObservableList<Serie> serl = FXCollections.observableArrayList();
			for(Serie s1:ls) {
				gen.addAll(fmc2.buscaFilmeGenero(f, s1, rb1));
				for(String fg:gen) {
					sb1.append(fg+",");
				}
				s1.setGenero(sb1.toString());
				s1.setNumeroTemporadas(fmc2.BuscaNumTemp(s1));
				serl.add(s1);
				gen.clear();
				sb1.delete(0, sb1.length());
			}
			tbvfs.setItems(serl);
			tbvfs.setColumnResizePolicy(tbvfs.CONSTRAINED_RESIZE_POLICY);
			////////////////////////////////////
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				List <Filme> lf = fmc2.Listar();
				StringBuffer sb1 = new StringBuffer();
				List <String> gen = new ArrayList();
				ObservableList<Filme> serl = FXCollections.observableArrayList();
				for(Filme f1:lf) {
				gen.addAll(fmc2.buscaFilmeGenero(f1, s, rb1));
				for(String fg:gen) {
				sb1.append(fg+",");
				}
				f1.setGenero(sb1.toString());
				System.out.println("--->"+f1.getTitulo().toString());
				serl.add(f1);
				gen.clear();
				sb1.delete(0, sb1.length());
				}
				tbvfs.setItems(serl);
				tbvfs.setColumnResizePolicy(tbvfs.CONSTRAINED_RESIZE_POLICY);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Listando filmes");
		}
		
		
		
		
	}
	public void InsereSerie() {
		valueSetter(f,s,rb1);
		try {
			fmc2.InsereObra(f, s,rb1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
	}
	public void AtualizaSerie() {
		valueSetter(f,s,rb1);
		System.out.println("data ->  "+s.getData());
		try {
			fmc2.Atualiza(f, s, rb1);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("atualiza serie");
	}
	public void BuscaSeries() {
		s.setCodigo(Integer.parseInt(tfcodigo.getText()));
		try {
			s = fmc2.buscaSerie(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tftitulo.setText(s.getTitulo());
		dpdata.setValue(s.getData());
		slavaliacao.setValue(s.getAvaliacao());
		StringBuffer stb1 = new StringBuffer();
		String tfgen = "";
		try {
			List<String> lg = fmc2.buscaFilmeGenero(f,s,rb1);
			
			for(String tg:lg) {
				stb1.append(tg+"  ");
				tfgen+=tg+",";
			}
			s.setGenero(stb1.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tfgenero.setText(tfgen);
		try {
			System.out.println(fmc2.BuscaNumTemp(s));
			s.setNumeroTemporadas(fmc2.BuscaNumTemp(s));
			tfnumtemp.setText(String.valueOf(fmc2.BuscaNumTemp(s)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    tbvfs.getItems().clear();
		tbvfs.getItems().add(s);
		System.out.print("BUSCA SERIE");
	}
	public void ExcluiSerie() {
		try {
			s.setCodigo(Integer.parseInt(tfcodigo.getText()));
			fmc2.ExcluiFilmeSerie(f, s, rb1);
			parseZero();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Exclui serie");
	}
	// OP - FILME
	//-----------------------------------------------------------------------------------------------------------
	public void InsereFilme() {
		valueSetter(f,s,rb1);
		System.out.print("grave filmes");
		try {
			fmc2.InsereObra(f, s,rb1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
	}
	public void AtualizaFilme() {
		valueSetter(f,s,rb1);
		System.out.print("grave filmes");
		try {
			fmc2.Atualiza(f, s, rb1);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("atualiza filme");
	}
	public void BuscaFilme() {
		f.setCodigo(Integer.parseInt(tfcodigo.getText()));
		try {
			f = fmc2.buscaFilme(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
		tftitulo.setText(f.getTitulo());
		dpdata.setValue(f.getData());
		slavaliacao.setValue(f.getAvaliacao());
		StringBuffer stb1 = new StringBuffer();
		try {
			System.out.println("//|| "+rb1.isSelected());
			List<String> lg = fmc2.buscaFilmeGenero(f,s,rb1);
			String tfgen = "";
			for(String tg:lg) {
				stb1.append(tg+"  ");
				tfgen+=tg+",";
			}
			f.setGenero(tfgen);
			tfgenero.setText(tfgen);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tbvfs.getItems().clear();
		tbvfs.getItems().add(f);
		
		System.out.print("busca filme");
	}
	public void ExcluiFilme() {
		try {
			f.setCodigo(Integer.parseInt(tfcodigo.getText()));
			fmc2.ExcluiFilmeSerie(f, s, rb1);
			parseZero();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Exclui filme");
	}
	public void parseZero() {
		tbvfs.getItems().clear();
	}
	public void valueSetter(Filme f, Serie s,RadioButton rb1) {
		if(rb1.isSelected()) {
			s.setCodigo(Integer.parseInt(tfcodigo.getText()));
			s.setTitulo(tftitulo.getText().toLowerCase());
			s.setGenero(tfgenero.getText().toLowerCase());
			s.setData(dpdata.getValue());
			s.setAvaliacao(slavaliacao.getValue());
			s.setNumeroTemporadas(Integer.parseInt(tfnumtemp.getText()));
		}else {
			f.setCodigo(Integer.parseInt(tfcodigo.getText()));
			f.setTitulo(tftitulo.getText().toLowerCase());
			f.setGenero(tfgenero.getText().toLowerCase());
			f.setData(dpdata.getValue());
			//System.out.println(f.getData());
			f.setAvaliacao(slavaliacao.getValue());
		}
		
	}
}
