package app.pt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entity.Epsodio;
import Entity.Filme;
import Entity.Plano;
import Entity.Serie;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class FilmContro2 {
	private Connection c;
	public FilmContro2() throws ClassNotFoundException, SQLException {
		classcontro cc = new classcontro();
		c = cc.getc();
		
		
	}
	public void InsereObra(Filme f,Serie s,RadioButton rb1) throws SQLException {
		String sql;
		sql = "INSERT INTO OBRA VALUES (?,?,?,?,?) ";
		String sql2 = "INSERT INTO GENERO VALUES (?, ?, ?) ";
		String sql3 = "INSERT INTO SERIE VALUES (?, ?, ?) ";
		PreparedStatement ps = c.prepareStatement(sql);
		if(rb1.isSelected()) {
			ps = c.prepareStatement(sql);
			ps.setInt(1, s.getCodigo());
			ps.setString(2, s.getTitulo());
			ps.setDate(3, s.getDataconvert());
			ps.setInt(4, (int)(s.getAvaliacao()));
			ps.setString(5,"S");
			ps.execute();
			String [] lg = s.getGenero().split(",");
			for(String i:lg) {
				if (i != "") {
				ps =c.prepareStatement(sql2);
				ps.setInt(1, s.getCodigo());
				ps.setString(2, "S");
				ps.setString(3, i);
				ps.execute();
				}
			}
			ps = c.prepareStatement(sql3);
			ps.setInt(1, s.getCodigo());
			ps.setString(2, "S");
			ps.setInt(3, s.getNumeroTemporadas());
			ps.execute();
			ps.close();
		}else {
				ps = c.prepareStatement(sql);
				ps.setInt(1, f.getCodigo());
				ps.setString(2, f.getTitulo());
				ps.setDate(3, f.getDataconvert());
				ps.setInt(4, (int)(f.getAvaliacao()));
				ps.setString(5,"F");
				ps.execute();
				String [] lg1 = f.getGenero().split(",");
				sql2 = "INSERT INTO GENERO VALUES (?, ?, ?) ";
				for(String i:lg1) {
					System.out.println(i);
					if (i != "") {
						ps = c.prepareStatement(sql2);
						ps.setInt(1, f.getCodigo());
						ps.setString(2, "F");
						ps.setString(3, i);
					
					ps.execute();
					}
				}
				ps.close();
			}
		}
	public Filme buscaFilme(Filme f) throws SQLException {
		StringBuffer sb1f = new StringBuffer();
		sb1f.append("SELECT CODIGO, TITULO, DATAA, AVALIACAO ");
		sb1f.append("FROM OBRA WHERE CODIGO = ? AND TPOBRA = ?");
		PreparedStatement ps = c.prepareStatement(sb1f.toString());
		ps.setInt(1, f.getCodigo());
		ps.setString(2,String.valueOf("F"));
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			f.setTitulo(rs.getString("TITULO"));
			f.setData(rs.getDate("DATAA").toLocalDate());
			f.setAvaliacao(rs.getInt("AVALIACAO"));
		}
		else {
			f = new Filme();
		}
		ps.execute();
		ps.close();
		return f;
	}
	public List<String> buscaFilmeGenero(Filme f, Serie s,RadioButton rb1) throws SQLException{
		List<String> g = new ArrayList();
		String sql = "SELECT GEN.TPGENERO FROM GENERO GEN WHERE GEN.GCODIGO = ? AND GEN.GTPOBRA = ? ";
		PreparedStatement ps = c.prepareStatement(sql);
		if(rb1.isSelected()) {
			ps.setInt(1, s.getCodigo());
			ps.setString(2, String.valueOf("S"));
			System.out.println("Serie Selecionado");
		}
		else {
			ps.setInt(1, f.getCodigo());
			ps.setString(2, String.valueOf("F"));
			System.out.println("Filem Selecionado");
		}
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Filme fr = new Filme();
			g.add(rs.getString("TPGENERO"));
			System.out.println("G- "+g.get(0));
		}
		ps.execute();
		ps.close();
		return g;
	}
	
	public Serie buscaSerie(Serie s) throws SQLException {
		StringBuffer sb1f = new StringBuffer();
		sb1f.append("SELECT CODIGO, TITULO, DATAA, AVALIACAO ");
		sb1f.append("FROM OBRA WHERE CODIGO = ? AND TPOBRA = ?");
		PreparedStatement ps = c.prepareStatement(sb1f.toString());
		ps.setInt(1, s.getCodigo());
		ps.setString(2,String.valueOf("S"));
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			s.setTitulo(rs.getString("TITULO"));
			s.setData(rs.getDate("DATAA").toLocalDate());
			s.setAvaliacao(rs.getInt("AVALIACAO"));
		}
		else {
			s = new Serie();
		}
		ps.execute();
		ps.close();
		return s;
	}
	public int BuscaNumTemp(Serie s) throws SQLException {
		int a = 0;
		String sql = "SELECT NUMTEMP FROM SERIE WHERE SCODIGO = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, s.getCodigo());
		ps.execute();
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			s.setNumeroTemporadas(rs.getInt("NUMTEMP"));
		}
		ps.execute();
		ps.close();
		return s.getNumeroTemporadas();
	}
	//EXCLUIR
	//-----------------------------------------------------------------------------------------------------------
	public void ExcluiFilmeSerie(Filme f,Serie s,RadioButton rb1) throws SQLException, ClassNotFoundException {
		ExcluirFP(f,s,rb1);
		ExcluiRelacaoAF(f,s,rb1);
		XFSGenero(f,s,rb1);
		if(rb1.isSelected()) {
			XFSerie(s);
		}
		XFSObra(f,s,rb1);
	}
	public void XFSGenero(Filme f,Serie s,RadioButton rb1) throws SQLException {
		String gd = "DELETE GENERO WHERE GCODIGO = ? AND GTPOBRA = ?";
		PreparedStatement ps = c.prepareStatement(gd);
		if(rb1.isSelected()){
			ps.setInt(1, s.getCodigo());
			ps.setString(2, String.valueOf("S"));
		}else {
			ps.setInt(1, f.getCodigo());
			ps.setString(2, String.valueOf("F"));
		}
		ps.executeUpdate();
		ps.execute();
		ps.close();
	}
	
	public void XFSObra(Filme f,Serie s,RadioButton rb1) throws SQLException {
		String gd = "DELETE FROM OBRA WHERE CODIGO = ? AND TPOBRA = ?";
		PreparedStatement ps = c.prepareStatement(gd);
		if(rb1.isSelected()){
			ps.setInt(1, s.getCodigo());
			ps.setString(2, String.valueOf("S"));
		}else {
			ps.setInt(1, f.getCodigo());
			ps.setString(2, String.valueOf("F"));
		}
		ps.execute();
		ps.close();
	}
	public void XFSerie(Serie s) throws SQLException, ClassNotFoundException {
		ExcluirT(s,1,"b");
		String gd = "DELETE SERIE WHERE SCODIGO = ? AND STPOBRA = ?";
		PreparedStatement ps = c.prepareStatement(gd);
		ps.setInt(1, s.getCodigo());
		ps.setString(2, String.valueOf("S"));
		ps.executeUpdate();
		ps.execute();
		ps.close();
	}
	//-----------------------------------------------------------------------------------------------------------
	//EXCLUIR RELACAO
	public void ExcluiRelacaoAF(Filme f,Serie s,RadioButton rb1) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("DELETE FROM ATOR_OBRA WHERE EXISTS ");
		sb1.append("(SELECT AO.OCODIGO FROM ATOR_OBRA AO INNER JOIN OBRA O ON "); 
		sb1.append("AO.OCODIGO = O.CODIGO AND AO.OTPOBRA = O.TPOBRA WHERE O.CODIGO = ? AND O.TPOBRA = ? ) ");
		sb1.append("AND ATOR_OBRA.OCODIGO = ? AND ATOR_OBRA.OTPOBRA = ? ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		if(rb1.isSelected()) {
			ps.setInt(1, s.getCodigo());
			ps.setString(2, String.valueOf("S"));
			ps.setInt(3, s.getCodigo());
			ps.setString(4, String.valueOf("S"));
			ps.execute();
		}
		else {
			ps.setInt(1, f.getCodigo());
			ps.setString(2, String.valueOf("F"));
			ps.setInt(3, f.getCodigo());
			ps.setString(4, String.valueOf("F"));
			ps.execute();
		}
		ps.close();
	}
	public void ExcluirFP(Filme f,Serie s,RadioButton rb1) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("DELETE CAT FROM CATALOGO CAT ");
		sb1.append("INNER JOIN OBRA O ON O.CODIGO = CAT.OCODIGO AND CAT.TPOBRA = O.TPOBRA ");
		sb1.append("WHERE O.CODIGO = ? AND O.TPOBRA = ? ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		if(rb1.isSelected()) {//s
			ps.setInt(01, s.getCodigo());
			ps.setString(2, "S");
		}
		else {
			ps.setInt(1, f.getCodigo());
			ps.setString(2, "F");
		}
		ps.execute();
		ps.close();
	}
	//------//////////
	public void ExcluirT(Serie s2,int nt,String a) throws SQLException, ClassNotFoundException{
		//-------------------------------------
		TempEp2 tep2 = new TempEp2();
		Epsodio ep = new Epsodio();
		int cod = s2.getCodigo();
		//-------------------------------------
		Serie s = new Serie();
		
		s.setCodigo(cod);
		int numt = BuscaNumTemp(s);
		int st = s.getNumeroTemporadas();
		int b = 0;
		System.out.println("CODIGO OBRA: "+s.getCodigo());
		System.out.println("NUMERO DE TEMPORADAS: "+s.getNumeroTemporadas());
		
		System.out.println("Atual: "+nt+"- Anterior: "+st);
		StringBuffer sb1 = new StringBuffer();
		sb1.append("DELETE FROM TEMPORADA ");
		sb1.append("WHERE EXISTS ");
		sb1.append("(SELECT  T.OCODIGO, T.TPOBRA, T.TNUM FROM TEMPORADA T ");
		sb1.append("WHERE T.OCODIGO = ? AND T.TPOBRA= 'S' AND T.TNUM = ? ) ");
		sb1.append("AND OCODIGO = ? AND TPOBRA='S' AND TNUM = ? ");
		
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		
		if (a == "a") {
		if(st > nt) {
			for(b = nt+1;b<=st;b++) {
				System.out.println(b+" - até - "+st);
				ep.setCodigo(cod);
				ep.setTempNum(b);
				tep2.ExcluirEp(ep, "all");
				
				ps.setInt(1, cod);
				ps.setInt(2, b);
				ps.setInt(3, cod);
				ps.setInt(4, b);
				
				
				ps.execute();
			}
		}
		}else {
			if (a == "b") {
				for(int i = 1;i<=st;i++) {
					ep.setCodigo(cod);
					ep.setTempNum(i);
					tep2.ExcluirEp(ep, "all");
					ps.setInt(1, cod);
					//ps.setString(2, "S");
					ps.setInt(2, i);
					ps.setInt(3, cod);
					//ps.setString(2, "S");
					ps.setInt(4, i);
					ps.execute();
				}
		}
		}
		ps.close();
		
	}
	//-----------------------------------------------------------------------------------------------------------
	public void Atualiza(Filme f,Serie s, RadioButton rb1) throws SQLException, ClassNotFoundException {
		ExcluirT(s,s.getNumeroTemporadas(),"a");
		StringBuffer sb1 = new StringBuffer();
		String sql2 = "INSERT INTO GENERO VALUES (?, ?, ?) ";
		String sql3 = "UPDATE SERIE SET NUMTEMP = ? WHERE SCODIGO = ? ";
		sb1.append("UPDATE OBRA SET TITULO = ?,DATAA = ?, AVALIACAO = ?");
		sb1.append("WHERE CODIGO = ? AND TPOBRA = ?");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		if(rb1.isSelected()) {
			ps.setString(1, s.getTitulo());
			ps.setDate(2, s.getDataconvert());
			ps.setInt(3,(int) s.getAvaliacao());
			ps.setInt(4, s.getCodigo());
			ps.setString(5, String.valueOf("S"));
			ps.execute();
			
			XFSGenero(f,s,rb1);
			String [] lg = s.getGenero().split(",");
			for(String i:lg) {
				if (i != "") {
				ps = c.prepareStatement(sql2);
				ps.setInt(1, s.getCodigo());
				ps.setString(2, "S");
				ps.setString(3, i);
				ps.execute();
				}
			}
			ps = c.prepareStatement(sql3);
			ps.setInt(1, s.getNumeroTemporadas());
			ps.setInt(2, s.getCodigo());
			ps.execute();
		}else {
			ps.setString(1, f.getTitulo());
			ps.setDate(2, f.getDataconvert());
			ps.setInt(3,(int) f.getAvaliacao());
			ps.setInt(4, f.getCodigo());
			ps.setString(5, String.valueOf("F"));
			ps.execute();
			XFSGenero(f,s,rb1);
			String [] lg = f.getGenero().split(",");
			for(String i:lg) {
				if (i != "") {
				ps = c.prepareStatement(sql2);
				ps.setInt(1, f.getCodigo());
				ps.setString(2, "F");
				ps.setString(3, i);
				ps.execute();
				}
			}
		}
		
		ps.close();
		AtualizaFSCat(f,s,rb1);
	}
	public void AtualizaFSCat(Filme f,Serie s, RadioButton rb1) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("DELETE CAT FROM CATALOGO CAT ");
		sb1.append("INNER JOIN OBRA O ON O.CODIGO = CAT.OCODIGO AND CAT.TPOBRA = O.TPOBRA  ");
		sb1.append("INNER JOIN PLANO P ON P.NOME = CAT.PNOME ");
		sb1.append("WHERE O.CODIGO = ? AND O.TPOBRA = ? AND ? > P.OAVALIACAO ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		if(rb1.isSelected()) {//s
			ps.setInt(1, s.getCodigo());
			ps.setString(2, "S");
			ps.setInt(3, (int) s.getAvaliacao());
		}else {
			ps.setInt(1, f.getCodigo());
			ps.setString(2, "F");
			ps.setInt(3, (int) f.getAvaliacao());
		}
		ps.execute();
		ps.close();
	}
	//-----------------------------------------------------------------------------------------------------------
	public List<Filme> Listar() throws SQLException {
		String sql = "SELECT O.CODIGO, O.TITULO, O.DATAA, O.AVALIACAO FROM OBRA O WHERE O.TPOBRA = 'F'";
		PreparedStatement ps= c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Filme> lf = new ArrayList();
		while(rs.next()) {
			Filme f = new Filme();
			f.setCodigo(rs.getInt("CODIGO"));
			f.setTitulo(rs.getString("TITULO"));
			f.setData(rs.getDate("DATAA").toLocalDate());
			f.setAvaliacao(rs.getInt("AVALIACAO"));
			lf.add(f);
		}
		ps.execute();
		ps.close();
		return lf;
	}
	public List<Serie> ListarS() throws SQLException {
		String sql = "SELECT O.CODIGO, O.TITULO, O.DATAA, O.AVALIACAO FROM OBRA O WHERE O.TPOBRA = 'S'";
		PreparedStatement ps= c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Serie> ls = new ArrayList();
		while(rs.next()) {
			Serie s = new Serie();
			s.setCodigo(rs.getInt("CODIGO"));
			s.setTitulo(rs.getString("TITULO"));
			s.setData(rs.getDate("DATAA").toLocalDate());
			s.setAvaliacao(rs.getInt("AVALIACAO"));
			ls.add(s);
		}
		ps.execute();
		ps.close();
		return ls;
	}
	
	//GETTERS FILME - SERIE--------------------------------------------------------------------------------------
	public List<String> getObra(String val,Plano p) throws SQLException {
		String sql = "";
		if(val.contains("F")) {
			sql = "SELECT O.CODIGO,O.TITULO,O.TPOBRA,O.AVALIACAO FROM OBRA O WHERE TPOBRA = 'F' ";
		}
		else {
			if(val.contains("S")) {
				sql = "SELECT O.CODIGO,O.TITULO,O.TPOBRA,O.AVALIACAO FROM OBRA O WHERE TPOBRA = 'S' ";
			}
		}
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		String cod;
		String nam;
		String type;
		List<String> lis = new ArrayList();
		while(rs.next()) {
			if(p.getObraMedia() >= rs.getInt("AVALIACAO")) {
			cod = String.valueOf(rs.getInt("CODIGO"));
			nam = rs.getString("TITULO");
			type = rs.getString("TPOBRA");
			lis.add(cod+"/"+nam+"/"+type);
			}
		}
		ps.execute();
		ps.close();
		return lis;
	}
	
	
	
}
