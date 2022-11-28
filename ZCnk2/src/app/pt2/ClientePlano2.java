package app.pt2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entity.Ator;
import Entity.ClPlan;
import Entity.Cliente;
import javafx.scene.control.RadioButton;

public class ClientePlano2 {
	private Connection c;
	public ClientePlano2() throws ClassNotFoundException, SQLException {
		classcontro cc = new classcontro();
		c = cc.getc();
	}
	public void InserirP(ClPlan cp) throws SQLException {
		String sqlpl = "INSERT INTO CLIENTE_PLANO VALUES (?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sqlpl);
			ps.setInt(1,cp.getCodigo());
			ps.setString(2,cp.getPlanoNome());
			ps.setDate(3, Date.valueOf(cp.getDataI()));
			ps.setDate(4, Date.valueOf(cp.getDataF()));
			ps.execute();
			ps.close();
	}
	public void AtualizaPl(ClPlan cp) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("UPDATE CLIENTE_PLANO ");
		sb1.append("SET PNOME = ?, DATAI = ?, DATAF = ? ");
		sb1.append("WHERE EXISTS ");
		sb1.append("(SELECT CP.PCCODIGO,CP.PNOME FROM CLIENTE_PLANO CP "); 
		sb1.append("WHERE CP.PCCODIGO = ?) ");
		sb1.append("AND PCCODIGO = ? ");
		
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setString(1, cp.getPlanoNome());
		ps.setDate(2, Date.valueOf(cp.getDataI()));
		ps.setDate(3, Date.valueOf(cp.getDataF()));
		ps.setInt(4, cp.getCodigo());
		ps.setInt(5, cp.getCodigo());
		ps.execute();
		ps.close();
	}
	public void ExcluirCliente(ClPlan cp) throws SQLException {
		String sql1 = "DELETE CLIENTE_PLANO WHERE PCCODIGO = ? ";
		PreparedStatement ps = c.prepareStatement(sql1);
		ps.setInt(1, cp.getCodigo());
		ps.execute();
		ps.close();
	}
	public ClPlan BuscaCP(ClPlan cp) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT CP.PCCODIGO,CP.PNOME, CP.DATAI, CP.DATAF FROM CLIENTE_PLANO CP ");
		sb1.append("WHERE CP.PCCODIGO = ? ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setInt(1, cp.getCodigo());
		ResultSet rs = ps.executeQuery();
		ClPlan cp2r = new ClPlan();
		if(rs.next()) {
			cp2r.setCodigo(rs.getInt("PCCODIGO"));
			cp2r.setPlanoNome(rs.getString("PNOME"));
			cp2r.setDataI(rs.getDate("DATAI").toLocalDate());
			cp2r.setDataF(rs.getDate("DATAF").toLocalDate());
			
		}
		ps.execute();
		ps.close();
		return cp2r;
		
	}
	public String DataStatus(LocalDate ld) {
		String status="Ativo";
		int a = ld.compareTo(ld.now());
		if(a < 0) {
			status = "Bloqueado";
		}
		return status;
	}
	public List<ClPlan> ListaCP() throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT CP.PCCODIGO,CP.PNOME, CP.DATAI, CP.DATAF FROM CLIENTE_PLANO CP ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ResultSet rs = ps.executeQuery();
		List<ClPlan> lp = new ArrayList();
		while(rs.next()) {
			ClPlan cp2r = new ClPlan();
			cp2r.setCodigo(rs.getInt("PCCODIGO"));
			cp2r.setPlanoNome(rs.getString("PNOME"));
			cp2r.setDataI(rs.getDate("DATAI").toLocalDate());
			cp2r.setDataF(rs.getDate("DATAF").toLocalDate());
			lp.add(cp2r);
		}
		ps.execute();
		ps.close();
		return lp;
		
	}
	
	
	
	
	
	
	
}
