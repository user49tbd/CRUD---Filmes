package app.pt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Filme;
import Entity.Plano;
import Entity.Serie;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class Catalogo2 {
	private Connection c;
	private PlanoContro2 pc2;
	public Catalogo2() throws ClassNotFoundException, SQLException {
		classcontro cc = new classcontro();
		c = cc.getc();
		pc2 = new PlanoContro2();
		
	}
	public void InserirCat(Plano pl,String s) throws SQLException {
		String [] a = s.split("/");
		String sql = "";
		if(a[2].contains("F")) {
			sql = "INSERT INTO CATALOGO VALUES (?,?,'F')";
			System.out.println(a[2]+" selecionado filme");
		}
		if(a[2].contains("S")) {
			sql = "INSERT INTO CATALOGO VALUES (?,?,'S')";
			System.out.println(a[2]+" selecionado Serie");
		}
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, pl.getNome());
		ps.setInt(2, Integer.parseInt(a[0]));
		ps.execute();
		ps.close();
	}
	public void ExcluirCat(Plano pl,String s) throws SQLException {
		String [] a = s.split("/");
		String sql = "DELETE CATALOGO WHERE PNOME = ? AND OCODIGO = ? AND TPOBRA = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, pl.getNome());
		ps.setInt(2, Integer.parseInt(a[0]));
		ps.setString(3, a[2].toString());
		ps.execute();
		ps.close();
	}
	public List<String> BuscaCat(Plano pl) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT CAT.PNOME,CAT.OCODIGO,O.TITULO,O.AVALIACAO,O.TPOBRA ");
		sb1.append("FROM CATALOGO CAT INNER JOIN OBRA O ON O.CODIGO = CAT.OCODIGO AND O.TPOBRA = CAT.TPOBRA ");
		sb1.append("WHERE CAT.PNOME = ? AND O.AVALIACAO <= ? AND O.TPOBRA = ? ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setString(1, pl.getNome());
		ps.setInt(2, pl.getObraMedia());
		List <String> ls = new ArrayList();
		
		if(pl.getTpobra().contains("F") || pl.getTpobra().contains("D")) {
			ps.setString(3, "F");
			ResultSet rs = ps.executeQuery();
			StringBuffer sb2 = new StringBuffer();
			while(rs.next()) {
				//sb2.append(rs.getString("PNOME")+"/");
				sb2.append(rs.getInt("OCODIGO")+"/");
				sb2.append(rs.getString("TITULO")+"/");
				sb2.append(rs.getString("AVALIACAO")+"/");
				sb2.append(rs.getString("TPOBRA"));
				ls.add(sb2.toString());
				sb2.delete(0, sb2.length());
			}
			ps.execute();
		}
		
		if(pl.getTpobra().contains("S") || pl.getTpobra().contains("D")) {
			ps.setString(3, "S");
			ResultSet rs = ps.executeQuery();
			StringBuffer sb2 = new StringBuffer();
			while(rs.next()) {
				sb2.append(rs.getInt("OCODIGO")+"/");
				sb2.append(rs.getString("TITULO")+"/");
				sb2.append(rs.getString("AVALIACAO")+"/");
				sb2.append(rs.getString("TPOBRA"));
				ls.add(sb2.toString());
				sb2.delete(0, sb2.length());
			}
			ps.execute();
		}
		ps.close();
		return ls;
		
	}
	public String Listar(ComboBox cb) throws SQLException {
		String  g;
		String a = cb.getItems().toString();
		a = a.replace("[", "");
		a = a.replace("]", "");
		System.out.println(a);
		Plano p = new Plano();
		List<String> ls =  new ArrayList();
		List<String> ls2 =  new ArrayList();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for(String s : a.split(",")) {
			if(s != null) {
				System.out.println(s);
			p.setNome(s.trim());
			p = pc2.buscaP(p);
			ls = BuscaCat(p);
			for(String s2 : ls) {
				g = s2.split("/")[1]+" - "+s2.split("/")[3];
				sb1.append(g+"  |  ");
			}
			sb2.append(s+"/"+" "+sb1.toString()+" "+"//");
			sb1.delete(0, sb1.length());
		}
		}
		String ret = sb2.toString();
		System.out.println(ret);
		return ret;
	}
	public String check(Plano p,ComboBox cbObra) {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT CAT.OCODIGO FROM CATALOGO CAT ");
		sb1.append("WHERE CAT.PNOME = ? AND CAT.OCODIGO = ? AND CAT.TPOBRA = ? ");
		String a="";
		try {
			PreparedStatement ps = c.prepareStatement(sb1.toString());
			ps.setString(1, p.getNome());
			ps.setInt(2, Integer.parseInt(cbObra.getValue().toString().split("/")[0]));
			ps.setString(3, cbObra.getValue().toString().split("/")[2]);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				a = rs.getString("OCODIGO");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
		

	}
	
	
}
