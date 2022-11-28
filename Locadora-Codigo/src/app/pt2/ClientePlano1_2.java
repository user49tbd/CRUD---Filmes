package app.pt2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.ClPlan;
import Entity.Cliente;
import Entity.Plano;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientePlano1_2 {
	private ClientePlano2 cp2;
	private Cliente2 cl2;
	private PlanoContro2 pc2;
	public ClientePlano1_2(){
		try {
			cp2 = new ClientePlano2();
			cl2 = new Cliente2();
			pc2 = new PlanoContro2();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void InserirP(TextField tfCodigo,ComboBox cbPlano,DatePicker dpDatai,
			DatePicker dpDataf) {
		ClPlan cp = new ClPlan();
		cp.setCodigo(Integer.parseInt(tfCodigo.getText()));
		cp.setPlanoNome(cbPlano.getValue().toString().toLowerCase());
		cp.setDataI(dpDatai.getValue());
		cp.setDataF(dpDataf.getValue());
		try {
			cp2.InserirP(cp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
	}
	public void AtualizarP(TextField tfCodigo,ComboBox cbPlano,DatePicker dpDatai,
			DatePicker dpDataf) {
		ClPlan cp = new ClPlan();
		cp.setCodigo(Integer.parseInt(tfCodigo.getText()));
		cp.setPlanoNome(cbPlano.getValue().toString().toLowerCase());
		cp.setDataI(dpDatai.getValue());
		cp.setDataF(dpDataf.getValue());
		try {
			cp2.AtualizaPl(cp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
		}
	}
		public void ExcluiP(TextField tfCodigo) {
			ClPlan cp = new ClPlan();
			cp.setCodigo(Integer.parseInt(tfCodigo.getText()));
			try {
				cp2.ExcluirCliente(cp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		public void BuscaP(TextField tfCodigo,ComboBox cbPlano,DatePicker dpDatai,
				DatePicker dpDataf/*,TextArea ta*/,TableView tbva,TextField tfValor) {
			ClPlan cp = new ClPlan();
			Plano p = new Plano();
			cp.setCodigo(Integer.parseInt(tfCodigo.getText()));
			try {
				cp = cp2.BuscaCP(cp);
				p.setNome(cp.getPlanoNome());
				p = pc2.buscaP(p);
				cbPlano.setValue(cp.getPlanoNome());
				dpDatai.setValue(cp.getDataI());
				dpDataf.setValue(cp.getDataF());
				tfValor.setText(String.valueOf(p.getPreco()));
				Cliente cl = new Cliente();
				cl.setCodigo(cp.getCodigo());
				cl = cl2.BuscaC(cl);
				String status = cp2.DataStatus(cp.getDataF());
				
				/*
				StringBuffer sb1 = new StringBuffer(cp.getCodigo()+"\t"+cl.getNome()+"\t"+
				cp.getPlanoNome()+"\t"+cp.getDataI()+"\t"+cp.getDataF());
				ta.setText(sb1.toString()+"\t"+p.getPreco()+"\t"+status);
				*/
				ClPlan as1 = new ClPlan();
				as1.setCodigo(cp.getCodigo());
				as1.setClnome(cl.getNome());
				as1.setPlanoNome(cp.getPlanoNome());
				as1.setDataI(cp.getDataI());
				as1.setDataF(cp.getDataF());
				as1.setPpreco(p.getPreco());
				as1.setStatus(status);
				
				ObservableList<ClPlan> obs = FXCollections.observableArrayList();
				obs.add(as1);
				tbva.setItems(obs);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void ListarP(TableView tbva){//TextArea ta) {
			StringBuffer sb1 = new StringBuffer();
			ClPlan cp = new ClPlan();
			String a ="  ";
			try {
				
				List <ClPlan> lc = cp2.ListaCP();
				Plano p = new Plano();
				Cliente cl = new Cliente();
				////////////////////////////////////////////////
				int al1 = 0;
				int al2 = 0;
				int al3 = 0;
				int al4 = 0;
				int al5 = 0;
				int al6 = 0;
				int al7 = 0;
				String ar = "  ";
				/*
				for(ClPlan ac : lc){
					p.setNome(ac.getPlanoNome());
					p = pc2.buscaP(p);
					cl.setCodigo(ac.getCodigo());
					cl = cl2.BuscaC(cl);
					
					if(String.valueOf(ac.getCodigo()).length() > al1) {
						al1 = String.valueOf(ac.getCodigo()).length();
						}
					if(cl.getNome().length() > al2) {
						al2 = cl.getNome().length();
					}
					if(ac.getPlanoNome().length() > al3) {
						al3 = ac.getPlanoNome().length();
						
					}
					if(String.valueOf(p.getPreco()).length() > al4) {
						al4 = String.valueOf(p.getPreco()).length();
					}
					}
					*/
				String status = "";
				ObservableList<ClPlan> obs = FXCollections.observableArrayList();
				for(ClPlan ac : lc){
					p.setNome(ac.getPlanoNome());
					p = pc2.buscaP(p);
					cl.setCodigo(ac.getCodigo());
					cl = cl2.BuscaC(cl);
					status = cp2.DataStatus(ac.getDataF());
					/*
				sb1.append(
						ac.getCodigo()+ar.repeat(Math.abs(String.valueOf(ac.getCodigo()).length() - al1))+"\t"+
						cl.getNome()+ar.repeat(Math.abs(cl.getNome().length() - al2))+"\t"+
						ac.getPlanoNome()+ar.repeat(Math.abs(ac.getPlanoNome().length() - al3))+"\t"+
						ac.getDataI()+"\t"+ ac.getDataF() +"\t"+
						p.getPreco()+ar.repeat(Math.abs(String.valueOf(p.getPreco()).length()- al3))
						+"\t"+status+"\n"
						);
						*/
					ClPlan as1 = new ClPlan();
					as1.setCodigo(ac.getCodigo());
					as1.setClnome(cl.getNome());
					as1.setPlanoNome(ac.getPlanoNome());
					as1.setDataI(ac.getDataI());
					as1.setDataF(ac.getDataF());
					as1.setPpreco(p.getPreco());
					as1.setStatus(status);
					obs.add(as1);
				}
				//ta.setText(sb1.toString());
				/////////////////////////////////////////////////
				tbva.setItems(obs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
