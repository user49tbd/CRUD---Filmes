package app.pt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Ator;
import Entity.Cliente;
import javafx.scene.control.RadioButton;

public class Cliente2 {
	private Connection c;
	public Cliente2() throws ClassNotFoundException, SQLException {
		classcontro cc = new classcontro();
		c = cc.getc();
	}
	public void InserirAC(Cliente cl) throws SQLException {
		String sqlc = "INSERT INTO CLIENTE VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sqlc);
		ps = c.prepareStatement(sqlc);
		ps.setInt(1, cl.getCodigo());
		ps.setString(2, cl.getNome());
		ps.setString(3, cl.getCpf());
		ps.setString(4, cl.getRg());
		ps.setString(5, cl.getEmail());
		ps.setString(6, cl.getTelefone());
		ps.execute();
		System.out.println("Fase3 Cliente");
		ps.close();
	}
	public void AtualizaC(Cliente cl) throws SQLException {
		StringBuffer sb2 = new StringBuffer();
		sb2.append("UPDATE CLIENTE ");
		sb2.append("SET NOME = ?,CPF = ?, RG = ?,EMAIL = ?,TELEFONE = ? ");
		sb2.append("WHERE CCODIGO = ? ");
		
		PreparedStatement ps = c.prepareStatement(sb2.toString());
		ps.setString(1, cl.getNome());
		ps.setString(2, cl.getCpf());
		ps.setString(3, cl.getRg());
		ps.setString(4, cl.getEmail());
		ps.setString(5, cl.getTelefone());
		ps.setInt(6, cl.getCodigo());
		ps.execute();
		ps.close();
	}
	public Cliente BuscaC(Cliente cl) throws SQLException {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT C.CCODIGO, C.NOME,C.CPF,C.RG,C.EMAIL,C.TELEFONE FROM CLIENTE C ");
		sb1.append("WHERE  C.CCODIGO = ?");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setInt(1, cl.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			cl.setNome(rs.getString("NOME"));
			cl.setCpf(rs.getString("CPF"));
			cl.setRg(rs.getString("RG"));
			cl.setEmail(rs.getString("EMAIL"));
			cl.setTelefone(rs.getString("TELEFONE"));
			System.out.println(cl.getNome());
		}
		ps.execute();
		ps.close();
		return cl;
	}
	public void ExcluirCliente(Cliente cl) throws SQLException {
		String sql1 = "DELETE CLIENTE_PLANO WHERE PCCODIGO = ? ";
		StringBuffer sb1 = new StringBuffer();
		sb1.append("DELETE FROM CLIENTE WHERE CCODIGO = ? ");
		
		PreparedStatement ps = c.prepareStatement(sql1);
		ps.setInt(1, cl.getCodigo());
		ps.execute();
		ps = c.prepareStatement(sb1.toString());
		ps.setInt(1, cl.getCodigo());
		ps.execute();
		ps.close();
	}
	public List<Cliente> ListaC() throws SQLException {
		String sql = ("SELECT C.CCODIGO,C.NOME,C.CPF,C.RG,C.EMAIL,C.TELEFONE FROM CLIENTE C");
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Cliente> lc = new ArrayList();
		while(rs.next()) {
			Cliente cl = new Cliente();
			cl.setCodigo(rs.getInt("CCODIGO"));
			cl.setNome(rs.getString("NOME"));
			cl.setCpf(rs.getString("CPF"));
			cl.setRg(rs.getString("RG"));
			cl.setEmail(rs.getString("EMAIL"));
			cl.setTelefone(rs.getString("TELEFONE"));
			lc.add(cl);
		}
		return lc;
	}
	

}
