package app.pt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Plano;
import javafx.scene.control.RadioButton;

public class PlanoContro2 {
	private Connection c;
	public PlanoContro2() throws ClassNotFoundException, SQLException {
		classcontro cc = new classcontro();
		
		c =cc.getc();
	}
	
	public void InserePlano(Plano p,RadioButton rb2f, RadioButton rb1s) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("INSERT INTO PLANO ");
		sb1.append("VALUES ");
		sb1.append("(?, ?, ?, ?, ?, ?,?) ");
		
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		
		ps.setString(1, p.getNome());
		ps.setString(2, p.getPeriodo());
		ps.setInt(3,p.getObraMedia());
		ps.setInt(4, p.getQuant());
		ps.setString(5, p.getQuali());
		ps.setDouble(6, p.getPreco());
		
		if(rb1s.isSelected() && rb2f.isSelected()) {
			ps.setString(7, "D");
		}
		else {
			if(rb1s.isSelected()) {
				ps.setString(7, "S");
			}
			else {
				if(rb2f.isSelected()) {
					ps.setString(7, "F");
				}
			}
		}
		ps.execute();
		ps.close();
	}
	//ATUALIZAR
	//------------------------------------------------------------------------------------------------------------
	public void AtualizaP(Plano p,RadioButton rb1s, RadioButton rb2f) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("UPDATE PLANO ");
		sb1.append("SET PERIODO = ?, OAVALIACAO = ?, QUANT = ? , ");
		sb1.append("VQUALIDADE = ?, PRECO = ?, TPOBRA = ? ");
		sb1.append("WHERE NOME = ? ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		
		ps.setString(1, p.getPeriodo());
		ps.setInt(2, p.getObraMedia());
		ps.setInt(3, p.getQuant());
		ps.setString(4, p.getQuali());
		ps.setDouble(5, p.getPreco());
		ps.setString(7, p.getNome());
		
		if(rb1s.isSelected() && rb2f.isSelected()) {
			ps.setString(6, "D");
		}
		else {
			if(rb1s.isSelected()) {
				ps.setString(6, "S");
			}
			else {
				if(rb2f.isSelected()) {
					ps.setString(6, "F");
				}
			}
		
	}
		ps.execute();
		ps.close();
		AtualizaPCat(p);
		AtualizarPCat2(p);
}
	public void AtualizaPCat(Plano p) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("DELETE CAT FROM CATALOGO CAT ");
		sb1.append("INNER JOIN OBRA O ON O.CODIGO = CAT.OCODIGO AND CAT.TPOBRA = O.TPOBRA ");
		sb1.append("WHERE CAT.PNOME = ? AND O.AVALIACAO > ? ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setString(1, p.getNome());
		ps.setInt(2, p.getObraMedia());
		ps.execute();
		ps.close();
	}
	public int totGet(Plano p) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT COUNT(P.NOME) AS TOT FROM PLANO P ");
		sb1.append("INNER JOIN CATALOGO CAT ON CAT.PNOME = P.NOME ");
		sb1.append("INNER JOIN OBRA O ON O.CODIGO = CAT.OCODIGO AND O.TPOBRA = CAT.TPOBRA ");
		sb1.append("WHERE P.NOME = ? ");
		sb1.append("GROUP BY P.NOME ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setString(1, p.getNome());
		ResultSet rs = ps.executeQuery();
		int num = 0;
		if(rs.next()) {
			num = rs.getInt("TOT");
		}
		ps.execute();
		return num;
	}
	public void AtualizarPCat2(Plano p) throws SQLException {
		int tot = totGet(p);
		if(p.getQuant() < tot ) {
			StringBuffer sb1 = new StringBuffer();
			sb1.append("SELECT CAT.PNOME,CAT.OCODIGO,CAT.TPOBRA FROM CATALOGO CAT ");
			sb1.append("INNER JOIN OBRA O ON O.CODIGO = CAT.OCODIGO AND CAT.TPOBRA = O.TPOBRA ");
			sb1.append("WHERE CAT.PNOME = ? ");
			sb1.append("ORDER BY O.AVALIACAO ASC ");
			PreparedStatement ps = c.prepareStatement(sb1.toString());
			ps.setString(1,p.getNome());
			ResultSet rs = ps.executeQuery();
			List<String>ls = new ArrayList();
			StringBuffer sb2 = new StringBuffer();
			while(rs.next()) {
				sb2.append(rs.getString("PNOME")+"/"+rs.getInt("OCODIGO")+"/"+rs.getString("TPOBRA"));
				System.out.println(sb2.toString());
				ls.add(sb2.toString());
				sb2.delete(0, sb2.length());
			}
			ps.execute();
			int a=0;
			List<String>lsn = new ArrayList();
			
			for(int i = 1;i<=Math.abs((tot-p.getQuant()));i++) {
				a = ls.size()-i;
				lsn.add(ls.get(a));
			}
			
			sb1.delete(0, sb1.length());
			sb1.append("DELETE CAT FROM CATALOGO CAT ");
			sb1.append("WHERE CAT.PNOME = ? AND CAT.OCODIGO  = ? AND TPOBRA = ? ");
			ps = c.prepareStatement(sb1.toString());
			for(String s : lsn) {
				String [] args = s.split("/");
				ps.setString(1, args[0]);
				ps.setString(2, args[1]);
				ps.setString(3, args[2]);
				ps.execute();
			}
			ps.close();
		}
	}
	//BUSCAR
	//------------------------------------------------------------------------------------------------------------
	public Plano buscaP(Plano p) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT P.NOME,P.PERIODO,P.OAVALIACAO,P.QUANT, ");
		sb1.append("P.VQUALIDADE,P.PRECO,P.TPOBRA FROM PLANO P ");
		sb1.append("WHERE P.NOME = ? ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setString(1, p.getNome());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			System.out.println("FAZE - FINAL");
			Plano p2 = new Plano();
			p2.setNome(rs.getString("NOME"));
			p2.setPeriodo(rs.getString("PERIODO"));
			p2.setObraMedia(rs.getInt("OAVALIACAO"));
			p2.setQuant(rs.getInt("QUANT"));
			p2.setQuali(rs.getString("VQUALIDADE"));
			p2.setPreco(rs.getDouble("PRECO"));
			p2.setTpobra(rs.getString("TPOBRA"));
			ps.execute();
			ps.close();
			return p2;
		}else {
			Plano p2 = new Plano();
			ps.execute();
			ps.close();
			return p2;
		}
		
		
	}
	//LISTAR
	//------------------------------------------------------------------------------------------------------------
	public List<Plano> ListaP() throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT P.NOME,P.PERIODO,P.OAVALIACAO,P.QUANT, ");
		sb1.append("P.VQUALIDADE,P.PRECO,P.TPOBRA FROM PLANO P ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ResultSet rs = ps.executeQuery();
		List <Plano> lp = new ArrayList();
		while(rs.next()) {
			System.out.println("FAZE - FINAL");
			Plano p2 = new Plano();
			p2.setNome(rs.getString("NOME"));
			p2.setPeriodo(rs.getString("PERIODO"));
			p2.setObraMedia(rs.getInt("OAVALIACAO"));
			p2.setQuant(rs.getInt("QUANT"));
			p2.setQuali(rs.getString("VQUALIDADE"));
			p2.setPreco(rs.getDouble("PRECO"));
			p2.setTpobra(rs.getString("TPOBRA"));
			lp.add(p2);
		}
		ps.execute();
		ps.close();
		return lp;
		
	}
	
	//EXCLUIR
	//-----------------------------------------------------------------------------------------------------------
	public void excluirP(Plano p) throws SQLException {
		//---------------------------------------------
		ExcluirPCat(p);
		//---------------------------------------------
		StringBuffer sb1 = new StringBuffer();
		sb1.append("DELETE CLIENTE_PLANO ");
		sb1.append("WHERE EXISTS ");
		sb1.append("(SELECT CP.PNOME FROM CLIENTE_PLANO CP INNER JOIN PLANO P ");
		sb1.append("ON P.NOME = CP.PNOME WHERE P.NOME = ?) AND PNOME = ? ");

		String sql = "DELETE FROM PLANO WHERE NOME = ? ";
		
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setString(1, p.getNome());
		ps.setString(2, p.getNome());
		ps.execute();
		ps = c.prepareStatement(sql);
		ps.setString(1, p.getNome());
		ps.execute();
		ps.close();
		
	}
	public void ExcluirPCat(Plano p) throws SQLException {
		String sql = "DELETE FROM CATALOGO WHERE PNOME = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, p.getNome());
		ps.execute();
		ps.close();
	}
	//-----------------------------------------------------------------------------------------------------------
	public List<String> getPlano() throws SQLException {
		String sql = "SELECT P.NOME FROM PLANO P ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<String> ls = new ArrayList();
		while(rs.next()) {
			ls.add(rs.getString("NOME"));
		}
		return ls;
	}
	
	
	}
