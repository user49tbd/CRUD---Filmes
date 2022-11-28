package app.pt2;

import javax.swing.JOptionPane;

import Entity.Epsodio;
import Entity.Serie;
import Entity.Temporada;
import application.Display03;
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

public class TempEp {
	private RadioButton rbt;
	private RadioButton rbe;
	
	private TextField tfNome;
	private ComboBox cbTemp;
	private TextField tfQuant;
	
	private ComboBox cbNumEp;
	private TextField tfTitulo;
	private TextField tfTime;
	private TableView tbv;
	private TableView tbv2;
	
	private Display03 dps3;
	private Stage a;
	
	public TempEp(RadioButton rbt, RadioButton rbe, TextField tfNome, ComboBox cbTemp, TextField tfQuant,
			ComboBox cbNumEp, TextField tfTitulo, TextField tfTime, TableView tbv,TableView tbv2,Stage a){
		super();
		this.rbt = rbt;
		this.rbe = rbe;
		this.tfNome = tfNome;
		this.cbTemp = cbTemp;
		this.tfQuant = tfQuant;
		this.cbNumEp = cbNumEp;
		this.tfTitulo = tfTitulo;
		this.tfTime = tfTime;
		this.tbv = tbv;
		this.a = a;
		dps3 = new Display03(a);
	}
	
	EventHandler<MouseEvent> evh = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent ar) {
			TempEp1_2 tep12 = new TempEp1_2(rbt,rbe,tfNome,cbTemp,tfQuant,
					cbNumEp,tfTitulo,tfTime);
			// TODO Auto-generated method stub
			
			if(rbe.isSelected() || rbt.isSelected()) {
				if(rbe.isSelected()) {
					if((ar.getSource().toString().contains("Inserir") ||
							ar.getSource().toString().contains("Atualizar"))
							&&(tfNome.getText().isEmpty() || cbTemp.getValue() == null ||
							cbNumEp.getValue() == null || tfTitulo.getText().isEmpty()
							|| tfTime.getText().isEmpty()) ||
							cbTemp.getValue() == null) {
						JOptionPane.showMessageDialog(null,"Campos do episodio podem ser nulos");
					}
					else {
						if((ar.getSource().toString().contains("Excluir") ||
								ar.getSource().toString().contains("Buscar"))&&
								(tfNome.getText().toString().isEmpty() || cbTemp.getValue()==null ||
								cbNumEp.getValue()==null)) {
							JOptionPane.showMessageDialog(null,"Codigo da obra ou temporada ou"
									+ " numero do episodio nao podem ser nulos");
						}
						else {
							
							if(ar.getSource().toString().contains("Inserir")) {
								System.out.println("Inserindo Episodio");
								tep12.InsereEp(tfNome, cbTemp, tfQuant, cbNumEp, tfTitulo, tfTime);
							}
							if(ar.getSource().toString().contains("Atualizar")) {
								System.out.println("Atualizando Episodio");
								tep12.AtualizaEp(tfNome, cbTemp, tfQuant, cbNumEp, tfTitulo, tfTime);
							}
							if(ar.getSource().toString().contains("Excluir")) {
								System.out.println("Excluindo Episodio");
								tep12.ExcluirEp(tfNome, cbTemp, tfQuant, cbNumEp);
							}
							if(ar.getSource().toString().contains("Buscar")) {
								System.out.println("Buscando Episodio");
									String tfn = tfNome.getText();
									String tfq = tfQuant.getText();
									int i = cbTemp.getSelectionModel().getSelectedIndex();
									System.out.println(i);
									
									boolean be = rbe.isSelected();
									boolean bt = rbt.isSelected();
									
									int i2 = cbNumEp.getSelectionModel().getSelectedIndex();
									
									dps3.setChange("2");
									dps3.change(dps3.getA(), dps3.getVb());
									
									dps3.getTfNome().setText(tfn);
									dps3.vetini();
									dps3.getCbTemp().getSelectionModel().select(i);
									dps3.getTfQuant().setText(tfq);
									dps3.getRbe().setSelected(be);
									dps3.getRbt().setSelected(bt);
									dps3.vecepini();
									dps3.getCbNumEp().getSelectionModel().select(i2);
									dps3.getCe().tbds(dps3.getCbNumEp(),dps3.getTfTitulo(),
											dps3.getTfTime(), dps3.getRbt(), dps3.getRbe());
									
									TableColumn<Temporada,String> coltemp = new TableColumn("Serie - Temporada");
									
									TableColumn<Temporada,String> colnom = new TableColumn("Nome");
									colnom.setCellValueFactory(new PropertyValueFactory("snome"));
									
									TableColumn<Temporada,Integer> colcod = new TableColumn("Codigo");
									colcod.setCellValueFactory(new PropertyValueFactory("obraCodigo"));
									
									TableColumn<Temporada,Integer> coltnum = new TableColumn("Temporada");
									coltnum.setCellValueFactory(new PropertyValueFactory("tempnum"));
									
									coltemp.getColumns().addAll(colnom,colcod,coltnum);
									dps3.getTbv().getColumns().add(coltemp);
									dps3.getTbv().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
									//----------------------------------------------------------------------------
									
									TableColumn<Epsodio,String> colep = new TableColumn("Episodio");
									
									TableColumn<Epsodio,Integer> colnumep = new TableColumn("Numero");
									colnumep.setCellValueFactory(new PropertyValueFactory("epNum"));
									
									TableColumn<Epsodio,String> coltitulo = new TableColumn("Titulo");
									coltitulo.setCellValueFactory(new PropertyValueFactory("Titulo"));
									
									TableColumn<Epsodio,String> coldura = new TableColumn("Duracao");
									coldura.setCellValueFactory(new PropertyValueFactory("duracao"));
									
									colep.getColumns().addAll(colnumep,coltitulo,coldura);
									dps3.getTbv2().getColumns().add(colep);
									dps3.getTbv2().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
									
									tep12.BuscarEp(dps3.getTfNome(), dps3.getCbTemp(), dps3.getTfQuant()
											, dps3.getCbNumEp(), dps3.getTfTitulo(), dps3.getTfTime(), 
											dps3.getTbv(),dps3.getTbv2());
									
									}
							
							
							
							//}
							if(ar.getSource().toString().contains("Listar") 
									&&( tfNome.getText().isEmpty() || cbTemp.getValue() == null)) {
								JOptionPane.showMessageDialog(null,"Campos nao podem ser nulos");
							}
							else {
								if(ar.getSource().toString().contains("Listar")){
								System.out.println("Listando Episodio");
								String tfn = tfNome.getText();
								String tfq = tfQuant.getText();
								int i = cbTemp.getSelectionModel().getSelectedIndex();
								System.out.println(i);
								
								boolean be = rbe.isSelected();
								boolean bt = rbt.isSelected();
								dps3.setChange("2");
								dps3.change(dps3.getA(), dps3.getVb());
								
								dps3.getTfNome().setText(tfn);
								dps3.vetini();
								dps3.getCbTemp().getSelectionModel().select(i);
								dps3.getTfQuant().setText(tfq);
								dps3.getRbe().setSelected(be);
								dps3.getRbt().setSelected(bt);
								dps3.getCe().tbds(dps3.getCbNumEp(),dps3.getTfTitulo(),
										dps3.getTfTime(), dps3.getRbt(), dps3.getRbe());
								
								TableColumn<Temporada,String> coltemp = new TableColumn("Serie - Temporada");
								
								TableColumn<Temporada,String> colnom = new TableColumn("Nome");
								colnom.setCellValueFactory(new PropertyValueFactory("snome"));
								
								TableColumn<Temporada,Integer> colcod = new TableColumn("Codigo");
								colcod.setCellValueFactory(new PropertyValueFactory("obraCodigo"));
								
								TableColumn<Temporada,Integer> coltnum = new TableColumn("Temporada");
								coltnum.setCellValueFactory(new PropertyValueFactory("tempnum"));
								
								coltemp.getColumns().addAll(colnom,colcod,coltnum);
								dps3.getTbv().getColumns().add(coltemp);
								dps3.getTbv().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
								//----------------------------------------------------------------------------
								
								TableColumn<Epsodio,String> colep = new TableColumn("Episodio");
								
								TableColumn<Epsodio,Integer> colnumep = new TableColumn("Numero");
								colnumep.setCellValueFactory(new PropertyValueFactory("epNum"));
								
								TableColumn<Epsodio,String> coltitulo = new TableColumn("Titulo");
								coltitulo.setCellValueFactory(new PropertyValueFactory("Titulo"));
								
								TableColumn<Epsodio,String> coldura = new TableColumn("Duracao");
								coldura.setCellValueFactory(new PropertyValueFactory("duracao"));
								
								colep.getColumns().addAll(colnumep,coltitulo,coldura);
								dps3.getTbv2().getColumns().add(colep);
								dps3.getTbv2().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
								
								tep12.ListarEp(dps3.getTfNome(), dps3.getCbTemp(), dps3.getTfQuant()
										, dps3.getTbv(),dps3.getTbv2());
								
								
								}
							}
							
							
							
						}
					}
				}
				if(rbt.isSelected()) {
				//------------------------------------------------------------p
				if((ar.getSource().toString().contains("Inserir") ||
						ar.getSource().toString().contains("Atualizar"))
						&&(tfNome.getText().isEmpty() || cbTemp.getValue() == null ||
						tfQuant.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null,"Campos nao podem ser nulos");
				}
				else {
				
				if((ar.getSource().toString().contains("Excluir") ||
						ar.getSource().toString().contains("Buscar"))&&
						(tfNome.getText().toString().isEmpty() || cbTemp.getValue()==null)) {
					JOptionPane.showMessageDialog(null,"Codigo da obra ou temporada nao podem ser nulos");
				}
				else {
					
					if(ar.getSource().toString().contains("Inserir")) {
						System.out.println("Inserindo Temporada");
						tep12.InsereT(tfNome, cbTemp, tfQuant);
					}
					if(ar.getSource().toString().contains("Atualizar")) {
						System.out.println("Atualizando Temporada");
						tep12.AtualizaT(tfNome, cbTemp, tfQuant);
					}
					if(ar.getSource().toString().contains("Excluir")) {
						System.out.println("Excluindo Temporada");
						tep12.ExcluiT(tfNome, cbTemp);
					}
					if(ar.getSource().toString().contains("Buscar")) {
						System.out.println("Buscando Temporada");
						
						String tfn = tfNome.getText();
						String tfq = tfQuant.getText();
						int i = cbTemp.getSelectionModel().getSelectedIndex();
						System.out.println(i);
						
						dps3.setChange("2");
						dps3.change(dps3.getA(), dps3.getVb());
						
						dps3.getTfNome().setText(tfn);
						dps3.vetini();
						dps3.getCbTemp().getSelectionModel().select(i);
						dps3.getTfQuant().setText(tfq);
						
						
						
						TableColumn<Serie,String> colTitulo= new TableColumn("Titulo");
						colTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
						
						dps3.getTbv().getColumns().add(colTitulo);
						dps3.getTbv().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
						
						TableColumn<Serie,String> colTempconj= new TableColumn("Temporadas");
						colTempconj.setCellValueFactory(new PropertyValueFactory("tempconj"));
						
						dps3.getTbv2().getColumns().add(colTempconj);
						dps3.getTbv2().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
						
						tep12.BuscaT(tfNome, dps3.getTbv(),dps3.getTbv2(),tfQuant,cbTemp);
					}
					if(ar.getSource().toString().contains("Listar")) {
						System.out.println("Listando Temporada");
						
						dps3.setChange("1");
						dps3.change(dps3.getA(), dps3.getVb());
						
						TableColumn<Serie,String> colTP= new TableColumn("Temporada - Episodio");
						
						TableColumn<Serie,String> colTitulo= new TableColumn("Titulo");
						colTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
						
						TableColumn<Serie,String> colTempconj = new TableColumn("Temporadas");
						colTempconj.setCellValueFactory(new PropertyValueFactory("tempconj"));
						
						colTP.getColumns().addAll(colTitulo,colTempconj);
						dps3.getTbv().getColumns().add(colTP);
						
						dps3.getTbv().setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
						
						tep12.ListaT(dps3.getTbv());
					}
					
				}
				}
				}
			}
		}
		
	};
	public EventHandler<MouseEvent> getEvh() {
		return evh;
	}
	
	
}
