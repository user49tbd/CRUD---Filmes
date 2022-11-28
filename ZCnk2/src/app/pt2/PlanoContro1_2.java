package app.pt2;

import java.sql.SQLException;
import java.util.List;

import Entity.Ator;
import Entity.Plano;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PlanoContro1_2 {
	private TextField tfNomeP;
	private ComboBox cbPeriodo;
	private TextField tfObraMed;
	private TextField tfQuant;
	private ComboBox cbQuali;
	private TextField tfPreco;
	private TableView tbvp;
	private RadioButton rbf;
	private RadioButton rbs;
	PlanoContro2 pc;
	public PlanoContro1_2(TextField tfNomeP, ComboBox cbPeriodo, TextField tfObraMed, TextField tfQuant,
			ComboBox cbQuali, TextField tfPreco,TableView tbvp, RadioButton rbf, RadioButton rbs) {
		super();
		this.tfNomeP = tfNomeP;
		this.cbPeriodo = cbPeriodo;
		this.tfObraMed = tfObraMed;
		this.tfQuant = tfQuant;
		this.cbQuali = cbQuali;
		this.tfPreco = tfPreco;
		//this.ta = ta;
		this.tbvp = tbvp;
		this.rbf = rbf;
		this.rbs = rbs;
		
		try {
			pc = new PlanoContro2();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void InsereP(TextField tfNomeP, 
			ComboBox<String> cbPeriodo, TextField tfObraMed, TextField tfQuant,
			ComboBox<String> cbQuali, TextField tfPreco, RadioButton rbf, RadioButton rbs) {
		Plano p = new Plano();
		
		p.setNome(tfNomeP.getText().toLowerCase());
		p.setPeriodo(cbPeriodo.getValue().toString().toLowerCase());
		p.setObraMedia(Integer.parseInt(tfObraMed.getText()));
		p.setQuant(Integer.parseInt(tfQuant.getText()));
		p.setQuali(cbQuali.getValue().toString());
		p.setPreco(Double.parseDouble(tfPreco.getText()));
		
		try {
			pc.InserePlano(p, rbf, rbs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void AtualizaP(TextField tfNomeP, 
			ComboBox<String> cbPeriodo, TextField tfObraMed, TextField tfQuant,
			ComboBox<String> cbQuali, TextField tfPreco, 
			/*TextArea ta,*/ RadioButton rbf, RadioButton rbs) {
		Plano p = new Plano();
		
		p.setNome(tfNomeP.getText().toLowerCase());
		p.setPeriodo(cbPeriodo.getValue().toString().toLowerCase());
		p.setObraMedia(Integer.parseInt(tfObraMed.getText()));
		p.setQuant(Integer.parseInt(tfQuant.getText()));
		p.setQuali(cbQuali.getValue().toString());
		p.setPreco(Double.parseDouble(tfPreco.getText()));
		
		try {
			pc.AtualizaP(p, rbf, rbs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void BuscaP(TextField tfNomeP, 
			ComboBox<String> cbPeriodo, TextField tfObraMed, TextField tfQuant,
			ComboBox<String> cbQuali, TextField tfPreco,TableView tbvp, RadioButton rbf, RadioButton rbs) {
		Plano p = new Plano();
		p.setNome(tfNomeP.getText());
		try {
			Plano p2 = pc.buscaP(p);
			cbPeriodo.setValue(p2.getPeriodo());
			tfObraMed.setText(String.valueOf(p2.getObraMedia()));
			tfQuant.setText(String.valueOf(p2.getQuant()));
			cbQuali.setValue(p2.getQuali());
			tfPreco.setText(String.valueOf(p2.getPreco()));
			
			StringBuffer sb1 = new StringBuffer();
			sb1.append(p2.getNome()+"\t\t"+p2.getPeriodo()+"\t\t"+p2.getObraMedia()+
					"\t\t"+p2.getQuant()+"\t\t"+p2.getQuali()+"\t\t"+p2.getPreco()+
					"\t\t");
			
			System.out.println("//-> "+p2.getTpobra());
			
			if(p2.getTpobra().contains("D")) {
				System.out.println("1º selected");
				rbf.setSelected(true);
				rbs.setSelected(true);
				sb1.append("D");
			}
			if(p2.getTpobra().contains("S")) {
				System.out.println("2º selected");
				rbf.setSelected(false);
				rbs.setSelected(true);
				sb1.append("S");
			}
			if(p2.getTpobra().contains("F")) {
				System.out.println("3º selected");
				rbf.setSelected(true);
				rbs.setSelected(false);
				sb1.append("F");
			}
			tbvp.getItems().add(p2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ListaP(TableView tbvp){
		try {
			List <Plano> lp = pc.ListaP();
			StringBuffer sb1 = new StringBuffer();
			ObservableList<Plano> obsp = FXCollections.observableArrayList();
			for(Plano a : lp){
				obsp.add(a);
			}
			tbvp.setItems(obsp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ExcluiPlano(TextField tfNomeP) {
		Plano p = new Plano();
		p.setNome(tfNomeP.getText());
		try {
			pc.excluirP(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
