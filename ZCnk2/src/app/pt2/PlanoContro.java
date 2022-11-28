package app.pt2;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PlanoContro {
	private TextField tfNomeP;
	private ComboBox cbPeriodo;
	private TextField tfObraMed;
	private TextField tfQuant;
	private ComboBox cbQuali;
	private TextField tfPreco;
	private TableView tbvp;
	private RadioButton rbf;
	private RadioButton rbs;
	PlanoContro1_2 pc12;
	public PlanoContro(TextField tfNomeP, ComboBox cbPeriodo, TextField tfObraMed, TextField tfQuant, ComboBox cbQuali,
			TextField tfPreco,TableView tbvp, RadioButton rbf, RadioButton rbs) {
		super();
		this.tfNomeP = tfNomeP;
		this.cbPeriodo = cbPeriodo;
		this.tfObraMed = tfObraMed;
		this.tfQuant = tfQuant;
		this.cbQuali = cbQuali;
		this.tfPreco = tfPreco;
		this.tbvp = tbvp;
		this.rbf = rbf;
		this.rbs = rbs;
		
		pc12 = new PlanoContro1_2(tfPreco, cbQuali, tfPreco, tfPreco, cbQuali, tfPreco, /*ta,*/tbvp, rbs, rbs);
	}
	EventHandler<MouseEvent> rvh = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent ar) {
			
			if((ar.getSource().toString().contains("Inserir") ||
					ar.getSource().toString().contains("Atualizar"))
					&&(tfNomeP.getText().isEmpty() || cbPeriodo.getValue() == null ||
					tfObraMed.getText().isEmpty() || tfQuant.getText().isEmpty() ||
					cbQuali.getValue() == null || tfPreco.getText().isEmpty()
					|| (rbf.isSelected()==false && rbs.isSelected()==false))) {
				if(rbf.isSelected()==false && rbs.isSelected()==false) {
					JOptionPane.showMessageDialog(null,"series e filmes nao podem ser nulos simultaneamente");
					
				}else {
					JOptionPane.showMessageDialog(null,"Campos nao podem ser nulos");
				}
			}
			else {
				if((ar.getSource().toString().contains("Excluir") ||
						ar.getSource().toString().contains("Buscar"))
						&& (tfNomeP.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null,"Nome do plano nao podem ser nulo");
					
				}
				else {
					if(ar.getSource().toString().contains("Inserir")) {
						System.out.println("Inserindo");
						pc12.InsereP(tfNomeP, cbPeriodo, tfObraMed, tfQuant, cbQuali, tfPreco, rbf, rbs);
					}
					if(ar.getSource().toString().contains("Atualizar")) {
						System.out.println("Atualizando");
						pc12.AtualizaP(tfNomeP, cbPeriodo, tfObraMed, tfQuant, cbQuali, tfPreco, rbf, rbs);
					}
					if(ar.getSource().toString().contains("Excluir")) {
						System.out.println("Excluindo");
						pc12.ExcluiPlano(tfNomeP);
					}
					if(ar.getSource().toString().contains("Buscar")) {
						tbvp.getItems().clear();
						System.out.println("Buscando");
						pc12.BuscaP(tfNomeP, cbPeriodo, tfObraMed, tfQuant, cbQuali, tfPreco,tbvp, rbf, rbs);
					}
					if(ar.getSource().toString().contains("Listar")) {
						tbvp.getItems().clear();
						System.out.println("Listando");
						pc12.ListaP(tbvp);
					}
					
				}
			}
			
		}
		
	};
	public EventHandler<MouseEvent> getRvh() {
		return rvh;
	}
	
	

}
