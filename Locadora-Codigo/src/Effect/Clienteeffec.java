package Effect;
import java.sql.SQLException;

import Entity.Plano;
import app.pt2.PlanoContro2;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
public class Clienteeffec {
	String val;
	EventHandler<MouseEvent> eh = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getSource().toString().contains(val)) {
				
			}
		}
	};
	public void ef1(RadioButton rb,TextField tfCodigo,TextField tfNome,TextField tfNomeArt,
	TextField tfFilme,TextField tfCpf,TextField tfRg,ComboBox cbPlano,ComboBox cbNacao,
	RadioButton rb1s,RadioButton rb1f) {
			rb.addEventHandler(MouseEvent.MOUSE_CLICKED,  new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println(arg0.getSource().toString());
					if(arg0.getSource().toString().contains("Cliente")) {
						tfCpf.setDisable(false);
						tfRg.setDisable(false);
						cbPlano.setDisable(false);
						
						tfNomeArt.setDisable(true);
						tfFilme.setDisable(true);
						rb1s.setDisable(true);
						rb1f.setDisable(true);
					}
					else {
					if(arg0.getSource().toString().contains("Ator")) {
						tfCpf.setDisable(true);
						tfRg.setDisable(true);
						cbPlano.setDisable(true);
						
						tfNomeArt.setDisable(false);
						tfFilme.setDisable(false);
						rb1s.setDisable(false);
						rb1f.setDisable(false);
					}
				}
				}
			});
		}
		public void disab(ComboBox cbNumEp,TextField tfTitulo,TextField tfTime,
				RadioButton rb,RadioButton rbe) {
					EventHandler<MouseEvent> evhm =	new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent ar) {
					if(ar.getSource().toString().contains("Temporada")) {
						cbNumEp.setDisable(true);
						tfTitulo.setDisable(true);
						tfTime.setDisable(true);
						System.out.println("OK");
					}
					else {
						if(ar.getSource().toString().contains("Episodio")) {
							cbNumEp.setDisable(false);
							tfTitulo.setDisable(false);
							tfTime.setDisable(false);
							System.out.println("OK2");
						}
						
				}
				}
				
			};
			rb.addEventHandler(MouseEvent.MOUSE_CLICKED, evhm);
			rbe.addEventHandler(MouseEvent.MOUSE_CLICKED, evhm);
		}
		public void tbds(ComboBox cbNumEp,TextField tfTitulo,TextField tfTime,
				RadioButton rb,RadioButton rbe) {
							cbNumEp.setDisable(false);
							tfTitulo.setDisable(false);
							tfTime.setDisable(false);
							System.out.println("OK2");
						}
		}


