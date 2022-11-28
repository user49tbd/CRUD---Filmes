package app.pt2;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.Ator;
import Entity.Filme;
import Entity.Serie;
import application.Display05;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Atorc {
	private TextField tfCodigo;
	private TextField tfNome;
	//private TextField tfNacio;
	private ComboBox cbNacao;
	private TextField tfNomeArt;
	private TextField tfObra;
	
	//---------------------------
	private RadioButton rb1s;
	private RadioButton rb2f;
	
	private TableView tbvafs;
	private Atorc1_2 a12;
	private Atorc2 a2;
	
	private Display05 dps;
	
	
	public Atorc(TextField tfCodigo, TextField tfNome, ComboBox cbNacao, TextField tfNomeArt, TextField tfObra,
			RadioButton rb1s, RadioButton rb2f, TableView tbvafs,String change,Stage a) { //TextArea ta) {
		super();
		this.tfCodigo = tfCodigo;
		this.tfNome = tfNome;
		this.cbNacao = cbNacao;
		this.tfNomeArt = tfNomeArt;
		this.tfObra = tfObra;
		this.rb1s = rb1s;
		this.rb2f = rb2f;
		this.tbvafs = tbvafs;
		//this.ta = ta;
		a12 = new Atorc1_2();
		try {
			a2 = new Atorc2();
			dps = new Display05(a);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	EventHandler<MouseEvent> evh = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent ar) {
			// TODO Auto-generated method stub
			if((ar.getSource().toString().contains("Inserir") || ar.getSource().toString().contains("Atualizar"))
					&& (tfCodigo.getText().isEmpty() || tfNome.getText().isEmpty()
							||cbNacao.getValue() == null 
							|| tfNomeArt.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null,"Campos nao podem ser nulos1");

			}
			else {
				if((ar.getSource().toString().contains("Excluir") || 
						ar.getSource().toString().contains("Buscar"))
						&& (tfCodigo.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null,"codigo nao podem ser nulo");
				}
				else {
					//----------------------------------------
					if(ar.getSource().toString().contains("Inserir")) {
						System.out.println("fase1 Ator");
						
						Ator a = new Ator();
						a.setCodigo(Integer.parseInt(tfCodigo.getText()));
						try {
							a = a2.BuscaA(a);
							if(a.getNome() == null) {
								a12.InsereA(tfCodigo, tfNome, cbNacao, tfNomeArt);
							}
							else {
								JOptionPane.showMessageDialog(null, "ATOR JA CADASTRADO");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					if(ar.getSource().toString().contains("Excluir")) {
						System.out.println("fase1 Ator excluir");
						a12.ExcluirA(tfCodigo, rb1s, rb2f);
						//----------------------------------------
					}
					if(ar.getSource().toString().contains("Buscar")) {
						boolean s = false;
						boolean f = false;
						
						System.out.println(dps.getRb1s());
						
						String cod = tfCodigo.getText().toString();
						
						s = rb1s.isSelected();
						f = rb2f.isSelected();
						dps.setChange("2");
						dps.change(dps.getA(),dps.getVb());
						dps.getRb1s().setSelected(s);
						dps.getRb2f().setSelected(f);
						dps.getTfCodigo().setText(cod);
						
						System.out.println("buscando ator");
						TableColumn<Ator,String> colAtor = new TableColumn<>("Ator");
						
						TableColumn<Ator,Integer> colcod = new TableColumn<>("Codigo");
						colcod.setCellValueFactory(new PropertyValueFactory("codigo"));
						
						TableColumn<Ator,String> colnomeArt = new TableColumn<>("Nome_Artistico");
						colnomeArt.setCellValueFactory(new PropertyValueFactory("nomeArt"));
						
						TableColumn<Ator,String> colnome = new TableColumn<>("Nome");
						colnome.setCellValueFactory(new PropertyValueFactory("nome"));
						
						TableColumn<Ator,String> colnacio = new TableColumn<>("Nacionalidade");
						colnacio.setCellValueFactory(new PropertyValueFactory("nacio"));
						
						colAtor.getColumns().addAll(colcod,colnomeArt,colnome,colnacio);
						dps.getTbvafs().getColumns().add(colAtor);
						dps.getTbvafs().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
						
						String [] nacao = {"FRANCES","INGLES","ESTADUNIDENSE","ALEMAO","BRASILEIRO","ITALIANO",
								"MEXICANO","CHINES","JAPONES","TURCO"};
						
						//-------------------------------------------------------------------------
						if(rb1s.isSelected() == true) {
							TableColumn<Serie,Integer> colTitulo = new TableColumn<>("Serie - Titulo");
							colTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
							
							dps.getTbvafs2().getColumns().add(colTitulo);
							dps.getTbvafs2().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
						}
						else {
							TableColumn<Filme,Integer> colTitulo = new TableColumn<>("Filme - Titulo");
							colTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
							
							dps.getTbvafs2().getColumns().add(colTitulo);
							dps.getTbvafs2().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
						}
						a12.BuscaAt(dps.getTfCodigo(), dps.getTfNome(), dps.getCbNacao(), 
								dps.getTfNomeArt(),dps.getTbvafs(),dps.getTbvafs2(), rb2f);
					}
					if(ar.getSource().toString().contains("Atualizar")) {
						System.out.println("Atualizando");
						
						
						Ator a = new Ator();
						a.setCodigo(Integer.parseInt(tfCodigo.getText()));
						try {
							a = a2.BuscaA(a);
							if(a.getNome() != null) {
								a12.AtualizarA(tfCodigo, tfNome, cbNacao, tfNomeArt);
							}
							else {
								JOptionPane.showMessageDialog(null, "ATOR NÃO CADASTRADO");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					if(ar.getSource().toString().contains("Listar")) {
						
						boolean s = false;
						boolean f = false;
						
						s = rb1s.isSelected();
						f = rb2f.isSelected();
						dps.setChange("1");
						dps.change(dps.getA(),dps.getVb());
						dps.setRb1s(s);
						dps.setRb2f(f);
						dps.getRb1s().setSelected(s);
						dps.getRb2f().setSelected(f);
						
						TableColumn<Ator,String> colAtor = new TableColumn<>("Ator");
						
						TableColumn<Ator,Integer> colcod = new TableColumn<>("Codigo");
						colcod.setCellValueFactory(new PropertyValueFactory("codigo"));
						
						TableColumn<Ator,String> colnomeArt = new TableColumn<>("Nome_Artistico");
						colnomeArt.setCellValueFactory(new PropertyValueFactory("nomeArt"));
						
						TableColumn<Ator,String> colnome = new TableColumn<>("Nome");
						colnome.setCellValueFactory(new PropertyValueFactory("nome"));
						
						TableColumn<Ator,String> colnacio = new TableColumn<>("Nacionalidade");
						colnacio.setCellValueFactory(new PropertyValueFactory("nacio"));
						
						colAtor.getColumns().addAll(colcod,colnomeArt,colnome,colnacio);
						dps.getTbvafs().getColumns().add(colAtor);
						dps.getTbvafs().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
						System.out.println("Listando");
						//------------------a12.ListarAt(ta);
						a12.ListarAt(dps.getTbvafs());
					}
				}
				if(rb1s.isSelected() || rb2f.isSelected()) {
					if((ar.getSource().toString().contains("Insere Obra") || 
							ar.getSource().toString().contains("Remove Obra"))
							&&(tfCodigo.getText().isEmpty() || tfObra.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Obra ou codigo nao podem ser nulos");
					}
						else {
							if(ar.getSource().toString().contains("Insere Obra")) {
								Ator a = new Ator();
								a.setCodigo(Integer.parseInt(tfCodigo.getText()));
								try {
									List<String> ls = a2.BuscaAF(a, rb2f);
									int cont=0;
									if(ls.size()>0) {
									for(String h : ls) {
										cont++;
										if(h.split(" - ")[0].trim().equalsIgnoreCase(tfObra.getText().trim())) {
											JOptionPane.showMessageDialog(null, "Valor já cadastrado");
											break;
										}
										else {
											if(cont == ls.size()) {
												a12.InsertO(tfCodigo, tfObra, rb1s);
											}
										}
									}
									}
									else {
										a12.InsertO(tfCodigo, tfObra, rb1s);
										System.out.println("vazio");
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							else {
								if(ar.getSource().toString().contains("Remove Obra")) {
									System.out.println("removendo obra obra");
									a12.RemoveO(tfCodigo, tfObra, rb1s);
								}
							}
						}
				}
						
							}
						}
					
					
					//----------------------------
			};

	public EventHandler<MouseEvent> getEvh() {
		return evh;
	}
	public void setEvh(EventHandler<MouseEvent> evh) {
		this.evh = evh;
	}
	
}
