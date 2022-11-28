package app.pt2;

import javax.swing.JOptionPane;

import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ClientePlano {
	private TextField tfCodigo;
	private ComboBox cbPlano;
	private DatePicker dpDatai;
	private DatePicker dpDataf;
	private TextField tfValor;
	//private TextArea ta;
	private TableView tbva;
	
	private ClientePlano1_2 cp12;
	
	
	public ClientePlano(TextField tfCodigo, ComboBox cbPlano, DatePicker dpDatai, DatePicker dpDataf, TextField tfValor,
			TableView tbva){//TextArea ta) {
		super();
		this.tfCodigo = tfCodigo;
		this.cbPlano = cbPlano;
		this.dpDatai = dpDatai;
		this.dpDataf = dpDataf;
		this.tfValor = tfValor;
		//this.ta = ta;
		this.tbva = tbva;
		cp12 = new ClientePlano1_2();
		
	}
	EventHandler<MouseEvent> evh = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent ar) {
			// TODO Auto-generated method stub
			if((ar.getSource().toString().contains("Assinar") || 
					ar.getSource().toString().contains("Atualizar"))
					&& (tfCodigo.getText().isEmpty() || cbPlano.getValue() == null ||
					dpDatai.getValue() == null || dpDataf.getValue() == null || 
					tfValor.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null,"Campos não podem ser nulos");
			}
			else {
				if((ar.getSource().toString().contains("Buscar") 
						|| ar.getSource().toString().contains("Cancelar"))
						&& (tfCodigo.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null,"Codigo não pode ser nulo");
				}
				else {
					if(ar.getSource().toString().contains("Assinar")) {
						System.out.println("Assinando");
						if(dpDatai.getValue().compareTo(dpDataf.getValue()) < 0) {
							cp12.InserirP(tfCodigo, cbPlano, dpDatai, dpDataf);
						}
						else {
							JOptionPane.showMessageDialog(null, "DataFim invalida");
						}
					}
					if(ar.getSource().toString().contains("Atualizar")) {
						System.out.println("Atualizando");
						if(dpDatai.getValue().compareTo(dpDataf.getValue()) < 0) {
							//BUSCAR // DATA
							//if() {
							cp12.AtualizarP(tfCodigo, cbPlano, dpDatai, dpDataf);
							//}
						}
						else {
							JOptionPane.showMessageDialog(null, "DataFim invalida");
						}
					}
					if(ar.getSource().toString().contains("Cancelar")) {
						System.out.println("Cancelando");
						cp12.ExcluiP(tfCodigo);
					}
					if(ar.getSource().toString().contains("Buscar")) {
						System.out.println("Buscando");
						//-------cp12.BuscaP(tfCodigo, cbPlano, dpDatai, dpDataf, ta,tfValor);
						cp12.BuscaP(tfCodigo, cbPlano, dpDatai, dpDataf, tbva,tfValor);
					}
					if(ar.getSource().toString().contains("Listar")) {
						System.out.println("Listando");
						//cp12.ListarP(ta);
						cp12.ListarP(tbva);
					}
					
					
					
					
					
					
				}
			}
		}
		
	};
	public EventHandler<MouseEvent> getEvh() {
		return evh;
	}
	
	
}
