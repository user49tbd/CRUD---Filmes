package app.pt2;

import javax.swing.JOptionPane;

import Entity.Cliente;
import Entity.Plano;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Client {
	private TextField tfCodigo;
	private TextField tfNome;
	private TextField tfCpf;
	private TextField tfRg;
	private TextField tfEmail;
	private TextField tfTelefone;
	private ComboBox cbPlano;

	// ---------------------------
	private TableView tbvc;
	
	private Cliente1_2 cl12;
	public Client(TextField tfCodigo, TextField tfNome, TextField tfCpf, TextField tfRg, TextField tfEmail,
			TextField tfTelefone,TableView tbvc){
		super();
		this.tfCodigo = tfCodigo;
		this.tfNome = tfNome;
		this.tfCpf = tfCpf;
		this.tfRg = tfRg;
		this.tfEmail = tfEmail;
		this.tfTelefone = tfTelefone;
		this.cbPlano = cbPlano;
		this.tbvc = tbvc;
		cl12 = new Cliente1_2();
	}

	EventHandler<MouseEvent> evh = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent ar) {
			// TODO Auto-generated method stub
			if((ar.getSource().toString().contains("Inserir") || ar.getSource().toString().contains("Atualizar"))
					&& (tfCodigo.getText().isEmpty() || tfNome.getText().isEmpty()
							||tfCpf.getText().isEmpty() || tfRg.getText().isEmpty()
							||  tfEmail.getText().isEmpty()  || tfTelefone.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null,"Campos nao podem ser nulos1");

			}
			else {
				if((ar.getSource().toString().contains("Excluir") || 
						ar.getSource().toString().contains("Buscar"))
						&& (tfCodigo.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null,"codigo nao podem ser nulo");
				}
				else {
				if((ar.getSource().toString().contains("Inserir") || 
						ar.getSource().toString().contains("Atualizar"))&&
						(tfCpf.getText().isEmpty() || tfRg.getText().isEmpty())) {
							JOptionPane.showMessageDialog(null,"Cpg,RG nao podem ser nulo");
						}
					else {
						//----------------------------------------------
						if(ar.getSource().toString().contains("Inserir")) {
							System.out.println("fase1 Cliente");
							cl12.InsereC(tfCodigo, tfNome, tfCpf, tfRg, tfEmail, tfTelefone);
						
						}
						if(ar.getSource().toString().contains("Excluir")) {
							System.out.println("fase1 Ator excluir");
							cl12.ExcluirCliente(tfCodigo);
							
						//----------------------------------------
						}
						if(ar.getSource().toString().contains("Buscar")) {
							System.out.println("buscando cliente");
							tbvc.getItems().clear();
							cl12.BuscaC(tfCodigo, tfNome, tfCpf, tfRg, tfEmail, tfTelefone, tbvc);
						//----------------------------------------
						}
						//----------------------------------------------
						if(ar.getSource().toString().contains("Atualizar")) {
							System.out.println("Atualizando");
							cl12.AtualizaC(tfCodigo, tfNome, tfCpf, tfRg, tfEmail, tfTelefone);
						}
						if(ar.getSource().toString().contains("Listar")) {
							System.out.println("Listando");
							tbvc.getItems().clear();
							cl12.ListaC(tfCodigo, tfNome, tfCpf, tfRg, tfEmail, tfTelefone, tbvc);
						}
								
							
						}
			}
			}
					//----------------------------
			}
	};
	public EventHandler<MouseEvent> getEvh() {
		return evh;
	}
	public void setEvh(EventHandler<MouseEvent> evh) {
		this.evh = evh;
	}
	}
