package app.pt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.Ator;
import Entity.Cliente;
import Entity.Filme;
import Entity.Serie;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Atorc2 {
	private Connection c;
	public Atorc2() throws ClassNotFoundException, SQLException {
		classcontro cc = new classcontro();
		c = cc.getc();
	}
	public void InserirAC(Ator a) throws SQLException {
		String sqla = "INSERT INTO ATOR VALUES (?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sqla);
		ps = c.prepareStatement(sqla);
		ps.setInt(1, a.getCodigo());
		ps.setString(2, a.getNome());
		ps.setString(3, a.getNomeArt());
		ps.setString(4, a.getNacio());
		ps.execute();
		System.out.println("Fase3 Ator");
		ps.close();
	}
	public void AtualizaA(Ator a) throws SQLException {
		StringBuffer sb2 = new StringBuffer();
		sb2.append("UPDATE ATOR ");
		sb2.append("SET NOME= ?,NOMEART = ?,NACIONALIDADE = ? ");
		sb2.append("WHERE ACODIGO = ? ");
		
		PreparedStatement ps = c.prepareStatement(sb2.toString());
		ps = c.prepareStatement(sb2.toString());
		ps.setString(1, a.getNome());
		ps.setString(2, a.getNomeArt());
		ps.setString(3, a.getNacio());
		ps.setInt(4, a.getCodigo());
		ps.execute();
		ps.close();
	}
	public void InsereObra(Ator a ,RadioButton rb1s) throws ClassNotFoundException, SQLException {
		boolean val = ValidFA(a,rb1s);
		System.out.println("//----------> começando");
		if(rb1s.isSelected()) {
			System.out.println("Selecionado");
		}
		else {
			System.out.println("Nao Selecionado");
		}
		if(val == true) {
			StringBuffer sb1 = new StringBuffer();
			sb1.append("INSERT INTO ATOR_OBRA ");
			sb1.append("VALUES ");
			sb1.append("(?,?,?)");
			PreparedStatement ps = c.prepareStatement(sb1.toString());
			ps.setInt(1, a.getCodigo());
			ps.setInt(2, a.getObra());
			if(rb1s.isSelected()) {
				ps.setString(3, String.valueOf("S"));
			}
			else {
				ps.setString(3, String.valueOf("F"));
			}
			ps.execute();
			ps.close();
		}
		
	}
	public Boolean ValidFA(Ator a,RadioButton rb1s) throws ClassNotFoundException, SQLException {
		FilmContro2 fmc2 = new FilmContro2();
		boolean valid = true;
		System.out.println("cemeçando fase 1");
		if(rb1s.isSelected()) {
			Serie s = new Serie();
			s.setCodigo(a.getObra());
			s = fmc2.buscaSerie(s);
			if(s.getTitulo() == null) {
				JOptionPane.showMessageDialog(null, "Serie nao encontrada");
				valid = false;
			}
		}else {
			System.out.println("Filme selecionado");
			Filme f = new Filme();
			f.setCodigo(a.getObra());
			f = fmc2.buscaFilme(f);
			if(f.getTitulo() == null) {
				JOptionPane.showMessageDialog(null, "Filme nao encontrado");
				valid = false;
			}
			else {
				System.out.println("encontrado");
			}
		}
		return valid;
	}
	public void excluirAO(Ator a ,RadioButton rb1s) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		System.out.println(a.getCodigo()+" - "+a.getObra());
		sb1.append("DELETE FROM ATOR_OBRA WHERE EXISTS ");
		sb1.append("(SELECT AO.ACODIGO FROM ATOR_OBRA AO ");
		sb1.append("WHERE AO.ACODIGO = ? AND AO.OTPOBRA = ? AND AO.OCODIGO = ?) ");
		sb1.append("AND ACODIGO = ? AND OTPOBRA = ? AND OCODIGO = ?");
		
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setInt(1, a.getCodigo());
		ps.setInt(3, a.getObra());
		ps.setInt(4, a.getCodigo());
		ps.setInt(6, a.getObra());
		if(rb1s.isSelected()) {
			ps.setString(2, String.valueOf("S"));
			ps.setString(5, String.valueOf("S"));
			ps.execute();
		}else {
			System.out.println("//-EXCLUINDO FILME-//");
			ps.setString(2, String.valueOf("F"));
			ps.setString(5, String.valueOf("F"));
			ps.execute();
		}
		System.out.println(ps.toString());
		ps.close();
	}
	//exclui - ator
	//-----------------------------------------------------------------------------------------------------------
	public void ExcluirAtor(Ator a,RadioButton rb1s) throws SQLException {
		BuscArF(a,rb1s);
		rb1s.setSelected(false);
		BuscArF(a,rb1s);
		rb1s.setSelected(true);
		String sql1 = "DELETE FROM ATOR WHERE ACODIGO = ? ";
		PreparedStatement ps = c.prepareStatement(sql1);
		ps.setInt(1, a.getCodigo());
		ps.execute();
		
	}
	
	public void BuscArF(Ator a,RadioButton rb1s) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT AO.OCODIGO FROM ATOR_OBRA AO INNER JOIN ATOR A ");
		sb1.append("ON AO.ACODIGO = A.ACODIGO WHERE A.ACODIGO = ? AND AO.OTPOBRA = ? ");
		int val =0;
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		if(rb1s.isSelected()) {
			System.out.println("SERIE");
			ps.setInt(1, a.getCodigo());
			ps.setString(2, String.valueOf("S"));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				val = rs.getInt("OCODIGO");
			}
			if(val != 0) {
				excluirAtFS(a,rb1s);
			}
		}
		else {
			System.out.println("FILME");
			ps.setInt(1, a.getCodigo());
			ps.setString(2, String.valueOf("F"));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				val = rs.getInt("OCODIGO");
			}
			if(val != 0) {
				excluirAtFS(a,rb1s);
			}
			
		}
	}
	public void excluirAtFS(Ator a ,RadioButton rb1s) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("DELETE FROM ATOR_OBRA WHERE EXISTS ");
		sb1.append("(SELECT AO.ACODIGO FROM ATOR_OBRA AO ");
		sb1.append("WHERE ACODIGO = ? AND OTPOBRA = ?) AND ACODIGO = ? AND OTPOBRA = ? ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setInt(1, a.getCodigo());
		ps.setInt(3, a.getCodigo());
		
		if(rb1s.isSelected()) {
			ps.setString(2, String.valueOf("S"));
			ps.setString(4, String.valueOf("S"));
			ps.execute();
		}else {
			ps.setString(2, String.valueOf("F"));
			ps.setString(4, String.valueOf("F"));
			ps.execute();
		}
		ps.close();
	}
	//BUSCAR ATOR
		//-----------------------------------------------------------------------------------------------------------
		public Ator BuscaA(Ator a) throws SQLException {
			StringBuffer sb1 = new StringBuffer();
			sb1.append("SELECT A.ACODIGO,A.NOMEART, A.NOME, A.NACIONALIDADE FROM ATOR A ");
			sb1.append("WHERE A.ACODIGO = ?");
			PreparedStatement ps = c.prepareStatement(sb1.toString());
			ps.setInt(1, a.getCodigo());
			ResultSet rs = ps.executeQuery();
			Ator at2 = new Ator();
			if(rs.next()) {
				at2.setCodigo(rs.getInt("ACODIGO"));
				at2.setNomeArt(rs.getString("NOMEART"));
				at2.setNome(rs.getString("NOME"));
				at2.setNacio(rs.getString("NACIONALIDADE"));
			}
			ps.execute();
			ps.close();
			return at2;	
			
		}
		
		public List<String> BuscaAF(Ator a,RadioButton rb1) throws SQLException{
			StringBuffer sb1 = new StringBuffer();
			
			sb1.append("SELECT DISTINCT O.CODIGO, O.TITULO FROM ATOR A INNER JOIN ATOR_OBRA AO "); 
			sb1.append("ON A.ACODIGO = AO.ACODIGO INNER JOIN OBRA O ON AO.OCODIGO = O.CODIGO AND O.TPOBRA = AO.OTPOBRA ");
			sb1.append("WHERE A.ACODIGO = ? AND O.TPOBRA = ?");
			List<String> fmf = new ArrayList();
			PreparedStatement ps = c.prepareStatement(sb1.toString());
			if(rb1.isSelected()) {
				ps.setInt(1, a.getCodigo());
				ps.setString(2, String.valueOf("F"));
			}
			else {
				ps.setInt(1, a.getCodigo());
				ps.setString(2, String.valueOf("S"));
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				fmf.add(rs.getInt("CODIGO")+" - "+rs.getString("TITULO"));
			}
			return fmf;
		}
		//---------------------------------------------------------------------------------------------------------------
		public List<Ator> ListarA() throws SQLException {
			String sql = ("SELECT ACODIGO,NOME,NOMEART,NACIONALIDADE FROM ATOR ");
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Ator> la = new ArrayList();
			while(rs.next()) {
				Ator at = new Ator();
				at.setCodigo(rs.getInt("ACODIGO"));
				at.setNomeArt(rs.getString("NOMEART"));
				at.setNome(rs.getString("NOME"));
				at.setNacio(rs.getString("NACIONALIDADE"));
				la.add(at);
				
			}
			ps.execute();
			ps.close();
			return la;
			
		}
}
