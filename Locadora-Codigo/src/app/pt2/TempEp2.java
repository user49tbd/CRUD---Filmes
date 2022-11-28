package app.pt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import Entity.Epsodio;
import Entity.Serie;
import Entity.Temporada;

public class TempEp2 {

	private Connection c;
	private FilmContro2 fc2;
	public TempEp2() throws ClassNotFoundException, SQLException {
		classcontro cc = new classcontro();
		c = cc.getc();
		
		fc2 = new FilmContro2();
	}
	public void InserirT(Temporada t) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("INSERT INTO TEMPORADA ");
		sb1.append("VALUES ");
		sb1.append("(?,?,?,?) ");
		
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setInt(1, t.getObraCodigo());
		ps.setString(2,t.getTipoObra());
		ps.setInt(3, t.getTempnum());
		ps.setInt(4, t.getTotEp());
		
		ps.execute();
		ps.close();
	
	}
	public void AtualizaT(Temporada t) throws SQLException {
		Temporada f = new Temporada();
		List<Temporada> lt = BuscarT(t);
		for(Temporada z : lt) {
			if(z.getTempnum() == t.getTempnum()) {
				f.setObraCodigo(t.getObraCodigo());
				f.setTempnum(z.getTempnum());
				f.setTotEp(z.getTotEp());
			}
		}
		System.out.println("Antes valor "+f.getTotEp());
		StringBuffer sb1 = new StringBuffer();
		sb1.append("UPDATE TEMPORADA ");
		sb1.append("SET TOTEP = ? ");
		sb1.append("WHERE OCODIGO = ? AND TPOBRA = ? AND TNUM = ? ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setInt(1, t.getTotEp());
		ps.setInt(2, t.getObraCodigo());
		ps.setString(3, t.getTipoObra());
		ps.setInt(4, t.getTempnum());
		
		ps.execute();
		ps.close();
		Epsodio ep = new Epsodio();
		ep.setTempNum(f.getTempnum());
		ep.setCodigo(f.getObraCodigo());
		List<Epsodio> lep = ListarEP(ep);
		System.out.println("começando loop -> "+lep.size());
		if(f.getTotEp() > t.getTotEp()) {
		for(Epsodio ep2 : lep) {
			if(ep2.getEpNum() > t.getTotEp()) {
				ExcluirEp(ep2,"p");
			}
		}
		}
	}
	public void ExcluirT(Temporada t) throws SQLException {
		Epsodio ep = new Epsodio();
		//----------------------------------------------------------------
		ep.setCodigo(t.getObraCodigo());
		ep.setTempNum(t.getTempnum());
		ep.setTpObra(t.getTipoObra());
		ExcluirEp(ep, "all");
		
		StringBuffer sb1 = new StringBuffer();
		sb1.append("DELETE FROM TEMPORADA ");
		sb1.append("WHERE EXISTS ");
		sb1.append("(SELECT  T.OCODIGO, T.TPOBRA, T.TNUM FROM TEMPORADA T "); 
		sb1.append("WHERE T.OCODIGO = ? AND T.TPOBRA=? AND T.TNUM = ?) ");
		sb1.append("AND OCODIGO = ? AND TPOBRA=? AND TNUM = ? ");
		
		PreparedStatement ps  = c.prepareStatement(sb1.toString());
		
		ps.setInt(1, t.getObraCodigo());
		ps.setString(2, t.getTipoObra());
		ps.setInt(3, t.getTempnum());
		
		ps.setInt(4, t.getObraCodigo());
		ps.setString(5, t.getTipoObra());
		ps.setInt(6, t.getTempnum());
		
		ps.execute();
		ps.close();
	}
	public List<Temporada> BuscarT(Temporada t) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT TNUM,TOTEP FROM TEMPORADA ");
		sb1.append("WHERE OCODIGO = ? AND TPOBRA = 'S' ");
		List<Temporada> lt = new ArrayList();
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setInt(1, t.getObraCodigo());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Temporada t1 = new Temporada();
			t1.setTempnum(rs.getInt("TNUM"));
			t1.setTotEp(rs.getInt("TOTEP"));
			lt.add(t1);
			
		}
		ps.execute();
		ps.close();
		return lt;
	}
	public StringBuffer ListarT() throws SQLException{
		List<Integer> li = ListarTO();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT TNUM,TOTEP FROM TEMPORADA ");
		sb1.append("WHERE OCODIGO = ? AND TPOBRA = 'S' ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ResultSet rs;
		for(int in :li) {
			ps.setInt(1, in);
			rs = ps.executeQuery();
			//----------------------------------
			Serie s = new Serie();
			s.setCodigo(in);
			s = fc2.buscaSerie(s);
			sb2.append("Titulo: "+s.getTitulo());
			sb2.append("| ");
			while(rs.next()) {
				sb2.append(" T "+rs.getInt("TNUM")+" - "+rs.getInt("TOTEP")+" | ");
			}
			sb2.append("\n");
		}
		return sb2;
	}
	public List<Serie> ListarT2() throws SQLException{
		List<Integer> li = ListarTO();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT TNUM,TOTEP FROM TEMPORADA ");
		sb1.append("WHERE OCODIGO = ? AND TPOBRA = 'S' ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ResultSet rs;
		List<Serie> lsr = new ArrayList();
		for(int in :li) {
			ps.setInt(1, in);
			rs = ps.executeQuery();
			//----------------------------------
			Serie s = new Serie();
			s.setCodigo(in);
			s = fc2.buscaSerie(s);
			sb2.append("| ");
			while(rs.next()) {
				sb2.append(" T "+rs.getInt("TNUM")+" - "+rs.getInt("TOTEP")+" | ");
			}
			s.setTempconj(sb2.toString());
			lsr.add(s);
			sb2.delete(0, sb2.length());
		}
		return lsr;
	}
	public List<Integer> ListarTO() throws SQLException{
		List<Integer> li = new ArrayList();
		String sql1 = "SELECT DISTINCT T.OCODIGO FROM TEMPORADA T";
		PreparedStatement ps = c.prepareStatement(sql1);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			li.add(rs.getInt("OCODIGO"));
		}
		ps.execute();
		ps.close();
		return li;
	}
	//Episodio
	//---------------------------------------------------------------------------------------------------------------
		public void InsereEp(Epsodio e) throws SQLException{
			SimpleDateFormat sdt=new SimpleDateFormat("HH:mm:ss");
			StringBuffer sb1 = new StringBuffer();
			sb1.append("INSERT INTO EPSODIO ");
			sb1.append("VALUES ");
			sb1.append("(?,?,?,?,?,?) ");
			PreparedStatement ps = c.prepareStatement(sb1.toString());
			//java.sql.Time t = new Time(sdt.parse(sdt.format(e.getDuracao())).getTime());
			ps.setInt(1, e.getEpNum());
			ps.setInt(2, e.getTempNum());
			ps.setInt(3, e.getCodigo());
			ps.setString(4, String.valueOf(e.getTpObra()));
			ps.setString(5, e.getTitulo());
			ps.setString(6,e.getDuracao());
			ps.execute();
			ps.close();
		}
		public void AtualizaEp(Epsodio e) throws SQLException {
			StringBuffer sb1 = new StringBuffer();
			sb1.append("UPDATE EPSODIO ");
			sb1.append("SET TITULO = ? , DURACAO = ? ");
			sb1.append("WHERE OCODIGO = ? AND TNUM = ? AND EPNUM = ? AND TPOBRA = 'S' ");
			PreparedStatement ps = c.prepareStatement(sb1.toString());
			ps.setString(1, e.getTitulo());
			ps.setString(2, e.getDuracao());
			ps.setInt(3, e.getCodigo());
			ps.setInt(4, e.getTempNum());
			ps.setInt(5, e.getEpNum());
			ps.execute();
			ps.close();
		}
		public void ExcluirEp(Epsodio e,String p) throws SQLException {
			
			if(p == "p") {
				StringBuffer sb1 = new StringBuffer();
				sb1.append("DELETE FROM EPSODIO ");
				sb1.append("WHERE  OCODIGO = ? AND TNUM = ? AND EPNUM = ? AND TPOBRA = 'S' ");
				PreparedStatement ps = c.prepareStatement(sb1.toString());
				ps.setInt(1, e.getCodigo());
				ps.setInt(2, e.getTempNum());
				ps.setInt(3, e.getEpNum());
				ps.execute();
				ps.close();
			}
			else {
				if(p == "all") {
					StringBuffer sb1 = new StringBuffer();
					sb1.append("DELETE FROM EPSODIO ");
					sb1.append("WHERE  OCODIGO = ? AND TNUM = ? AND TPOBRA = 'S' ");
					PreparedStatement ps = c.prepareStatement(sb1.toString());
					ps.setInt(1, e.getCodigo());
					ps.setInt(2, e.getTempNum());
					ps.execute();
					ps.close();
				}
				}
			}
		public Epsodio BuscaEp(Epsodio e) throws SQLException {
			StringBuffer sb1 = new StringBuffer();
			sb1.append("SELECT E.OCODIGO,E.TNUM,E.EPNUM,E.TPOBRA,E.TITULO,E.DURACAO FROM EPSODIO E ");
			sb1.append("WHERE  E.OCODIGO = ? AND E.TNUM = ? AND E.EPNUM = ? AND E.TPOBRA = 'S' ");
			PreparedStatement ps = c.prepareStatement(sb1.toString());
			ps.setInt(1, e.getCodigo());
			ps.setInt(2, e.getTempNum());
			ps.setInt(3, e.getEpNum());
			ResultSet rs = ps.executeQuery();
			Epsodio ep = new Epsodio();
			if(rs.next()) {
				ep.setCodigo(rs.getInt("OCODIGO"));
				ep.setTempNum(rs.getInt("TNUM"));
				ep.setEpNum(rs.getInt("EPNUM"));
				ep.setTpObra("S");
				ep.setTitulo(rs.getString("TITULO"));
				ep.setDuracao(rs.getTime("DURACAO").toString());
			}
			return ep;
		}
		public List<Epsodio> ListarEP(Epsodio e) throws SQLException {
			StringBuffer sb1 = new StringBuffer();
			sb1.append("SELECT E.OCODIGO,E.TNUM,E.EPNUM,E.TPOBRA,E.TITULO,E.DURACAO FROM EPSODIO E ");
			sb1.append("WHERE  E.OCODIGO = ? AND E.TNUM = ? AND E.TPOBRA = 'S' ");
			PreparedStatement ps =  c.prepareStatement(sb1.toString());
			ps.setInt(1, e.getCodigo());
			ps.setInt(2, e.getTempNum());
			ResultSet rs = ps.executeQuery();
			List<Epsodio> lep = new ArrayList();
			while(rs.next()) {
				Epsodio ep = new Epsodio();
				ep.setCodigo(rs.getInt("OCODIGO"));
				ep.setTempNum(rs.getInt("TNUM"));
				ep.setEpNum(rs.getInt("EPNUM"));
				ep.setTpObra("S");
				ep.setTitulo(rs.getString("TITULO"));
				ep.setDuracao(rs.getTime("DURACAO").toString());
				lep.add(ep);
			}
			return lep;
		}
	}











