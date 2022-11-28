package app.pt2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.Catg;
import Entity.Filme;
import Entity.Plano;
import application.Display08;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Catalogo {
	private ComboBox cbPlan;
	private ComboBox cbObra;
	private TextField tfbuscaobra;
	private TableView tbvc;
	private Catalogo1_2 cat12;
	private Catalogo2 cat2;
	private Display08 dps8;
	private Stage a;
	private int i;
	private PlanoContro2 pc2;
	
	public Catalogo(ComboBox cbPlan, ComboBox cbObra, /*TextArea ta,*/TableView tbvc,TextField tfbuscaobra,
			Stage a) {
		super();
		this.cbPlan = cbPlan;
		this.cbObra = cbObra;
		this.tbvc = tbvc;
		this.tfbuscaobra = tfbuscaobra;
		cat12 = new Catalogo1_2();
		try {
			cat2 = new Catalogo2();
			dps8 = new Display08(a);
			pc2 = new PlanoContro2();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	EventHandler<MouseEvent> evh = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent ar) {
			// TODO Auto-generated method stub
			if((ar.getSource().toString().contains("Inserir") || 
					ar.getSource().toString().contains("Excluir"))&&
					(cbPlan.getValue() == null || cbObra.getValue() == null)) {
				JOptionPane.showMessageDialog(null, "Campos não podem ser nulos");
			}
			else {
				if(ar.getSource().toString().contains("Buscar") && cbPlan.getValue() == null) {
					JOptionPane.showMessageDialog(null, "Plano não pode ser nulo");
				}
				else{
					if(ar.getSource().toString().contains("Inserir")) {
						System.out.println("Inserindo");
						Plano p = new Plano();
						p.setNome(cbPlan.getValue().toString());
						String conf = cat2.check(p, cbObra);
						int val = cat12.verifer(cbPlan);
						try {
							p = pc2.buscaP(p);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(conf =="") {
							if(p.getQuant() > val) {
								cat12.inserir(cbPlan, cbObra);
							}else {
								JOptionPane.showMessageDialog(null, "EXCEDEU A QUANTIDADE DE OBRAS DO PLANO");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "VALOR JA FOI CADASTRADO");
						}
						
					}
					if(ar.getSource().toString().contains("Excluir")) {
						System.out.println("Excluindo");
						cat12.Excluir(cbPlan, cbObra);
					}
					if(ar.getSource().toString().contains("Buscar")) {
						System.out.println("Buscando");
						tbvc.getColumns().clear();
						tbvc.getItems().clear();
						
						int i = cbPlan.getSelectionModel().getSelectedIndex();
						
						System.out.println("-------->>>-"+i);
						dps8.setChange("2");
						dps8.setIc(i);
						dps8.change(dps8.getA(), dps8.getVb());
						
						TableColumn<Plano,String> colp = new TableColumn("Nome - Plano");
						colp.setCellValueFactory(new PropertyValueFactory("nome"));
						
						//---------
						
						dps8.getTbvc().getColumns().add(colp);
						dps8.getTbvc().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
						
						TableColumn<Filme,String> colcat = new TableColumn("Catalogo");
						
						TableColumn<Filme,String> colcod = new TableColumn("Codigo");
						colcod.setCellValueFactory(new PropertyValueFactory("codigo"));
						
						TableColumn<Filme,String> coltitulo = new TableColumn("Titulo");
						coltitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
						
						TableColumn<Filme,String> colavaliacao = new TableColumn("Avaliacao");
						colavaliacao.setCellValueFactory(new PropertyValueFactory("avaliacao"));
						
						TableColumn<Filme,String> coltpobra = new TableColumn("Tipo-Obra");
						coltpobra.setCellValueFactory(new PropertyValueFactory("tpObra"));
						
						colcat.getColumns().addAll(colcod,coltitulo,colavaliacao,coltpobra);
						
						dps8.getTbvc2().getColumns().add(colcat);
						dps8.getTbvc2().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
						
						//cat12.Buscar(cbPlan, ta,tfbuscaobra);
						cat12.Buscar(cbPlan, dps8.getTbvc(),dps8.getTbvc2(),tfbuscaobra);
					}
					if(ar.getSource().toString().contains("Listar")) {
						System.out.println("Listando");
						tbvc.getColumns().clear();
						tbvc.getItems().clear();
						
						dps8.setChange("1");
						dps8.change(dps8.getA(), dps8.getVb());
						
						TableColumn<Catg,String> colc = new TableColumn("Catalogo");
						
						TableColumn<Catg,String> colp = new TableColumn("Plano");
						colp.setCellValueFactory(new PropertyValueFactory("pnome"));
						
						TableColumn<Catg,String> colf = new TableColumn("Filmes");
						colf.setCellValueFactory(new PropertyValueFactory("totfilm"));
						//cat12.Listar(cbPlan,ta);
						
						colc.getColumns().addAll(colp,colf);
						dps8.getTbvc().getColumns().add(colc);
						dps8.getTbvc().setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
						cat12.Listar(cbPlan,dps8.getTbvc());
					}
				}
			}
		}
		
	};
	public EventHandler<MouseEvent> getEvh() {
		return evh;
	}
	
}
