package app.pt2;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.ClPlan;
import Entity.Cliente;
import Entity.Plano;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Cliente1_2 {
	private Cliente2 cl2;
	private ClientePlano2 clpl;
	public Cliente1_2() {
		try {
			cl2 = new Cliente2();
			clpl = new ClientePlano2();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void InsereC(TextField tfCodigo,TextField tfNome,
			TextField tfCpf,TextField tfRg, TextField tfEmail,
			TextField tfTelefone) {
		Cliente cl = new Cliente();
		cl.setCodigo(Integer.parseInt(tfCodigo.getText()));
		cl.setNome(tfNome.getText());
		cl.setCpf(tfCpf.getText());
		cl.setRg(tfRg.getText());
		cl.setEmail(tfEmail.getText());
		cl.setTelefone(tfTelefone.getText());
		try {
			System.out.println("Fase2 Cliente");
			cl2.InserirAC(cl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
		
	}
	public void AtualizaC(TextField tfCodigo,TextField tfNome,
			TextField tfCpf,TextField tfRg, TextField tfEmail,
			TextField tfTelefone) {
		Cliente cl = new Cliente();
		cl.setCodigo(Integer.parseInt(tfCodigo.getText()));
		cl.setNome(tfNome.getText());
		cl.setCpf(tfCpf.getText());
		cl.setRg(tfRg.getText());
		cl.setEmail(tfEmail.getText());
		cl.setTelefone(tfTelefone.getText());
		try {
			System.out.println("Fase2 Cliente");
			cl2.AtualizaC(cl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
		
	}
	public void BuscaC(TextField tfCodigo,TextField tfNome,TextField tfCpf
			,TextField tfRg,TextField tfEmail,
			TextField tfTelefone,TableView tbvc){//TextArea ta) {
		Cliente cl = new Cliente();
		cl.setCodigo(Integer.parseInt(tfCodigo.getText()));
		System.out.println("Buscapt1");
		try {
			Cliente cli2 = new Cliente();
			cli2 = cl2.BuscaC(cl);
			tfNome.setText(cli2.getNome());
			tfCpf.setText(cli2.getCpf());
			tfRg.setText(cli2.getRg());
			tfEmail.setText(cli2.getEmail());
			tfTelefone.setText(cli2.getTelefone());
			ClPlan clp = new ClPlan();
			clp.setCodigo(cli2.getCodigo());
			
			if(clpl.BuscaCP(clp).getPlanoNome() != null) {
				cli2.setPlano(clpl.BuscaCP(clp).getPlanoNome());
			}
			else {
				cli2.setPlano("Sem-Plano");
			}
			tbvc.getItems().add(cli2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
	}
	public void ExcluirCliente(TextField tfCodigo) {
		Cliente cl = new Cliente();
		cl.setCodigo(Integer.parseInt(tfCodigo.getText()));
		try {
			cl2.ExcluirCliente(cl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ListaC(TextField tfCodigo,TextField tfNome,TextField tfCpf
			,TextField tfRg,TextField tfEmail,
			TextField tfTelefone,TableView tbvc){
		StringBuffer sb1 = new StringBuffer();
		System.out.println("Buscapt1list");
		
		try {
			//////////////////////
			
			List<Cliente> lc = cl2.ListaC();
			ObservableList<Cliente> obsc = FXCollections.observableArrayList();
			for(Cliente c :lc) {
				ClPlan clp = new ClPlan();
				clp.setCodigo(c.getCodigo());
				
				if(clpl.BuscaCP(clp).getPlanoNome() != null) {
					c.setPlano(clpl.BuscaCP(clp).getPlanoNome());
				}
				else {
					c.setPlano("Sem-Plano");
				}
				obsc.add(c);
			}
			tbvc.setItems(obsc);
			//////////////////////
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
