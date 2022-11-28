package app.pt2;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.Epsodio;
import Entity.Filme;
import Entity.Serie;
import Entity.Temporada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TempEp1_2 {
	private RadioButton rbt;
	private RadioButton rbe;
	
	private TextField tfNome;
	private ComboBox cbTemp;
	private TextField tfQuant;
	
	private ComboBox cbNumEp;
	private TextField tfTitulo;
	private TextField tfTime;
	
	private TempEp2 te2;
	private FilmContro2 fc2;
	
	public TempEp1_2(RadioButton rbt, RadioButton rbe, TextField tfNome, ComboBox cbTemp, TextField tfQuant,
			ComboBox cbNumEp, TextField tfTitulo,TextField tfTime) {
		super();
		this.rbt = rbt;
		this.rbe = rbe;
		this.tfNome = tfNome;
		this.cbTemp = cbTemp;
		this.tfQuant = tfQuant;
		this.cbNumEp = cbNumEp;
		this.tfTitulo = tfTitulo;
		this.tfTime = tfTime;
		
		try {
			te2 = new TempEp2();
			fc2 = new FilmContro2();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void InsereT(TextField tfNome, ComboBox cbTemp, TextField tfQuant) {
		Temporada t = new Temporada();
		t.setObraCodigo(Integer.parseInt(tfNome.getText()));
		t.setTipoObra("S");
		t.setTempnum(Integer.parseInt(cbTemp.getValue().toString()));
		t.setTotEp(Integer.parseInt(tfQuant.getText().toString()));
		try {
			te2.InserirT(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
	}
	public void AtualizaT(TextField tfNome, ComboBox cbTemp, TextField tfQuant) {
		Temporada t = new Temporada();
		t.setObraCodigo(Integer.parseInt(tfNome.getText()));
		t.setTipoObra("S");
		t.setTempnum(Integer.parseInt(cbTemp.getValue().toString()));
		t.setTotEp(Integer.parseInt(tfQuant.getText().toString()));
		try {
			te2.AtualizaT(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
	}
	public void ExcluiT(TextField tfNome, ComboBox cbTemp) {
		Temporada t = new Temporada();
		t.setObraCodigo(Integer.parseInt(tfNome.getText()));
		t.setTipoObra("S");
		t.setTempnum(Integer.parseInt(cbTemp.getValue().toString()));
		try {
			te2.ExcluirT(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void BuscaT(TextField tfNome,TableView tbv,TableView tbv2,TextField tfQuant,ComboBox cbTemp) {
		Temporada t = new Temporada();
		t.setObraCodigo(Integer.parseInt(tfNome.getText()));
		t.setTipoObra("S");
		Serie s = new Serie();
		s.setCodigo(t.getObraCodigo());
		
		try {
			s = fc2.buscaSerie(s);
			List<Temporada> lt = te2.BuscarT(t);
			StringBuffer sb1 = new StringBuffer();
			int cbval=0;
			ObservableList<Serie> obs1 = FXCollections.observableArrayList();
			ObservableList<Serie> obs2 = FXCollections.observableArrayList();
			obs1.add(s);
			for(Temporada tl : lt) {
				Serie ser1 = new Serie();
				sb1.append(tl.getTempnum()+" - "+tl.getTotEp()+"\n");
				cbval = Integer.parseInt(cbTemp.getValue().toString());
				if(cbval == tl.getTempnum()) {
					System.out.println("Selected---------------");
					tfQuant.setText(String.valueOf(tl.getTotEp()));
				}
				ser1.setTitulo(s.getTitulo());
				ser1.setTempconj(sb1.toString());
				obs2.add(ser1);
				sb1.delete(0, sb1.length());
			}
			tbv.setItems(obs1);
			tbv2.setItems(obs2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ListaT(TableView tbv){
		try {
			List<Serie> sr = te2.ListarT2();
			ObservableList<Serie> obs = FXCollections.observableArrayList();
			for(Serie s : sr) {
				obs.add(s);
			}
			tbv.setItems(obs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//EPSODIO
	//---------------------------------------------------------------------------------------------------------------
	public void InsereEp(TextField tfNome, ComboBox cbTemp, TextField tfQuant,
			ComboBox cbNumEp, TextField tfTitulo, TextField tfTime){
		Epsodio ep = new Epsodio();
		ep.setCodigo(Integer.parseInt(tfNome.getText()));
		ep.setDuracao(tfTime.getText());
		ep.setTempNum(Integer.parseInt(cbTemp.getValue().toString()));
		ep.setEpNum(Integer.parseInt(cbNumEp.getValue().toString()));
		ep.setTitulo(tfTitulo.getText().toString());
		ep.setTpObra("S");
		try {
			te2.InsereEp(ep);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
	}
	public void AtualizaEp(TextField tfNome, ComboBox cbTemp, TextField tfQuant,
			ComboBox cbNumEp, TextField tfTitulo, TextField tfTime){
		Epsodio ep = new Epsodio();
		ep.setCodigo(Integer.parseInt(tfNome.getText()));
		ep.setDuracao(tfTime.getText());
		ep.setTempNum(Integer.parseInt(cbTemp.getValue().toString()));
		ep.setEpNum(Integer.parseInt(cbNumEp.getValue().toString()));
		ep.setTitulo(tfTitulo.getText().toString());
		try {
			te2.AtualizaEp(ep);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ExcluirEp(TextField tfNome, ComboBox cbTemp, TextField tfQuant,
			ComboBox cbNumEp){
		Epsodio ep = new Epsodio();
		ep.setCodigo(Integer.parseInt(tfNome.getText()));
		ep.setTempNum(Integer.parseInt(cbTemp.getValue().toString()));
		ep.setEpNum(Integer.parseInt(cbNumEp.getValue().toString()));
		
		try {
			te2.ExcluirEp(ep,"p");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void BuscarEp(TextField tfNome, ComboBox cbTemp, TextField tfQuant,
			ComboBox cbNumEp, TextField tfTitulo, TextField tfTime,TableView tbv,TableView tbv2){
		//TextArea ta){
		Epsodio ep = new Epsodio();
		ep.setCodigo(Integer.parseInt(tfNome.getText()));
		ep.setTempNum(Integer.parseInt(cbTemp.getValue().toString()));
		ep.setEpNum(Integer.parseInt(cbNumEp.getValue().toString()));
		Epsodio ep2 = new Epsodio();
		try {
			Serie s = new Serie();
			s.setCodigo(ep.getCodigo());
			s = fc2.buscaSerie(s);
			//---------------
			ep2 = te2.BuscaEp(ep);
			tfTitulo.setText(ep2.getTitulo());
			tfTime.setText(ep2.getDuracao());
			ObservableList<Temporada> obt = FXCollections.observableArrayList();
			ObservableList<Epsodio> obep = FXCollections.observableArrayList();
			Temporada tp = new Temporada();
			tp.setSnome(s.getTitulo());
			tp.setObraCodigo(ep2.getCodigo());
			tp.setTempnum(ep2.getTempNum());
			obt.add(tp);
			obep.add(ep2);
			
			tbv.setItems(obt);
			tbv2.setItems(obep);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ListarEp(TextField tfNome, ComboBox cbTemp, TextField tfQuant,
			TableView tbv,TableView tbv2){
		try {
			Epsodio ep = new Epsodio();
			ep.setCodigo(Integer.parseInt(tfNome.getText()));
			ep.setTempNum(Integer.parseInt(cbTemp.getValue().toString()));
			Epsodio ep2 = new Epsodio();
			Serie s = new Serie();
			s.setCodigo(ep.getCodigo());
			s = fc2.buscaSerie(s);
			//---------------
			Temporada tp = new Temporada();
			ObservableList<Temporada> obs = FXCollections.observableArrayList();
			ObservableList<Epsodio> obp = FXCollections.observableArrayList();
			tp.setSnome(s.getTitulo());
			tp.setObraCodigo(ep.getCodigo());
			tp.setTempnum(ep.getTempNum());
			obs.add(tp);
			List<Epsodio> lep = te2.ListarEP(ep);
			for(Epsodio epf : lep) {
				obp.add(epf);
			}
			tbv.setItems(obs);
			tbv2.setItems(obp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
	}
}
