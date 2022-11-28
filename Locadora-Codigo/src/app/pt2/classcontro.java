package app.pt2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class classcontro {
	Connection c;
	public Connection getc() throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
		String dbName = "FISE";
		String user = "sa";
		String password = "B29@&&";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		c = DriverManager.getConnection(
				String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;",
						hostName, dbName,user,password)
				);
		//String sql = "USE LOC";
		///String sql = ("EXEC SP_HELP IDIOMA");
		//PreparedStatement ps = c.prepareStatement(sql);
		//ps.execute();
		//sql = "EXEC SP_HELP IDIOMAS";
		//ps = c.prepareStatement(sql);
		//ps.execute();
		//ps.close();
		//System.out.println("executado");
		return c;
	}

}
