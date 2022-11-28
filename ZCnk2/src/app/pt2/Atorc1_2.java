package app.pt2;

import java.sql.SQLException;
import java.util.List;

import Entity.Ator;
import Entity.Filme;
import Entity.Serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Atorc1_2 {
	private Atorc2 a2;
	public Atorc1_2() {
		
			try {
				a2 = new Atorc2();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public void InsereA(TextField tfCodigo,TextField tfNome,
			ComboBox<String> cbNacao,TextField tfNomeArt) {
		Ator a = new Ator();
		a.setCodigo(Integer.parseInt(tfCodigo.getText()));
		a.setNome(tfNome.getText().toLowerCase());
		a.setNacio(cbNacao.getValue().toString().toLowerCase());
		a.setNomeArt(tfNomeArt.getText().toLowerCase());
		try {
			System.out.println("Fase2 Ator");
			a2.InserirAC(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void AtualizarA(TextField tfCodigo,TextField tfNome,
			ComboBox<String> cbnacio,TextField NomeArt){
		Ator a = new Ator();
		a.setCodigo(Integer.parseInt(tfCodigo.getText()));
		a.setNome(tfNome.getText().toLowerCase());
		a.setNacio(cbnacio.getValue().toString().toLowerCase());
		a.setNomeArt(NomeArt.getText().toLowerCase());
		try {
			a2.AtualizaA(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void InsertO(TextField tfCodigo,TextField tfObra,RadioButton rb1s) {
		Ator a = new Ator();
		a.setCodigo(Integer.parseInt(tfCodigo.getText()));
		a.setObra(Integer.parseInt(tfObra.getText()));
		try {
			a2.InsereObra(a, rb1s);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void RemoveO(TextField tfCodigo,TextField tfObra,RadioButton rb1s) {
		Ator a = new Ator();
		a.setCodigo(Integer.parseInt(tfCodigo.getText()));
		a.setObra(Integer.parseInt(tfObra.getText()));
		if(rb1s.isSelected()) {
			System.out.println("Selecionado12Excluir");
		}
		else {
			System.out.println("Nao Selecionado12Excluir");
		}
		try {
			a2.excluirAO(a, rb1s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ExcluirA(TextField tfCodigo, RadioButton rb1s, RadioButton rb2f) {
		Ator a = new Ator();
		a.setCodigo(Integer.parseInt(tfCodigo.getText()));
		try {
			a2.ExcluirAtor(a, rb1s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//BUSCA ATOR
	//-----------------------------------------------------------------------------------------------------------
	public void BuscaAt(TextField tfCodigo,TextField tfNome,ComboBox<String> cbnacio,TextField NomeArt,
			TableView tbva,TableView tbvfs,RadioButton rb1) {
		Ator a = new Ator();
		a.setCodigo(Integer.parseInt(tfCodigo.getText()));
		try {
			System.out.print("Iniciando busca");
			Ator at2 = new Ator();
			at2 = a2.BuscaA(a);
			tfNome.setText(at2.getNome());
			NomeArt.setText(at2.getNomeArt());
			cbnacio.setValue(at2.getNacio());
			List<String> ld = a2.BuscaAF(a,rb1);
			
			ObservableList<Ator> obsa = FXCollections.observableArrayList();
			obsa.add(at2);
			tbva.setItems(obsa);
			ObservableList<Filme> obsf = FXCollections.observableArrayList();
			ObservableList<Serie> obss = FXCollections.observableArrayList();
			
			if(rb1.isSelected() == true) {
				for(String fn:ld){
					Serie s1 = new Serie();
					s1.setTitulo(fn);
					obss.add(s1);
				}
				tbvfs.setItems(obss);
			}
			else {
				for(String fn:ld){
					Filme f1 = new Filme();
					f1.setTitulo(fn);
					obsf.add(f1);
				}
				tbvfs.setItems(obsf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//LISTAR ATOR
		//-----------------------------------------------------------------------------------------------------------
		public void ListarAt(TableView tbvafs){//------TextArea ta) {
			try {
				List<Ator> la = a2.ListarA();
				StringBuffer sb1 = new StringBuffer();
				ObservableList<Ator> obs = FXCollections.observableArrayList();
				for(Ator a : la){
					obs.add(a);
				}
				tbvafs.setItems(obs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public List<Integer> getter(Ator a){
			return null;
			
		} 
}
