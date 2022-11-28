package app.pt2;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.Catg;
import Entity.Filme;
import Entity.Plano;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Catalogo1_2 {
	private Catalogo2 cat2;
	private FilmContro2 fc2;
	private PlanoContro2 pc2;
	public Catalogo1_2() {
		try {
			cat2 = new Catalogo2();
			fc2 = new FilmContro2();
			pc2 = new PlanoContro2();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void inserir(ComboBox cbPlan, ComboBox cbObra) {
		Plano p = new Plano();
		p.setNome(cbPlan.getValue().toString());
		try {
			cat2.InserirCat(p,cbObra.getValue().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Excluir(ComboBox cbPlan, ComboBox cbObra) {
		Plano p = new Plano();
		p.setNome(cbPlan.getValue().toString());
		try {
			cat2.ExcluirCat(p, cbObra.getValue().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Buscar (ComboBox cbPlan,/*TextArea ta,*/TableView tbvc,TableView tbvc2,TextField tfbusc) {
		Plano p = new Plano();
		p.setNome(cbPlan.getValue().toString());
		StringBuffer sb1 = new StringBuffer();
		try {
			p = pc2.buscaP(p);
			List<String> ls = cat2.BuscaCat(p);
			System.out.println(ls);
			ObservableList<Filme> obf = FXCollections.observableArrayList();
			ObservableList<Plano> obp = FXCollections.observableArrayList();
			obp.add(p);
			tbvc.setItems(obp);
			sb1.append(p.getNome()+"\n");
			for(String a : ls){
				String [] arc = a.split("/");
				if(tfbusc.getText().isEmpty() == false) {
					System.out.println("Selected111");
					if(arc[1].contains(tfbusc.getText())) {
						Filme f = new Filme();
						f.setCodigo(Integer.parseInt(arc[0]));
						f.setTitulo(arc[1]);
						f.setAvaliacao(Integer.parseInt(arc[2]));
						f.setTpObra(arc[3]);
						obf.add(f);
				}
				}
				else {
					System.out.println("Selected2222");
					Filme f = new Filme();
					f.setCodigo(Integer.parseInt(arc[0]));
					f.setTitulo(arc[1]);
					f.setAvaliacao(Integer.parseInt(arc[2]));
					f.setTpObra(arc[3]);
					obf.add(f);
					
				}
				}
			tbvc2.setItems(obf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Listar(ComboBox cb,TableView tbvc){
		StringBuffer sb1 = new StringBuffer();
		try {
			String[] ls = cat2.Listar(cb).split("//");
			int al1 = 0;
			String ar = "  ";
			ObservableList<Catg> obs = FXCollections.observableArrayList();
			for(String a : ls){
				String[] sp = a.split("/");
				Catg c = new Catg();
				c.setPnome(sp[0]);
				c.setTotfilm(sp[1]);
				obs.add(c);
			}
			tbvc.setItems(obs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Não a como listar valores nulos");
		}
}
	public int verifer(ComboBox cbPlan) {
		Plano p = new Plano();
		p.setNome(cbPlan.getValue().toString());
		//StringBuffer sb1 = new StringBuffer();
		int totcat = 0;
		try {
			p = pc2.buscaP(p);
			List<String> ls = cat2.BuscaCat(p);
			//System.out.println(ls);
			totcat = ls.size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totcat;
		
	}
}
