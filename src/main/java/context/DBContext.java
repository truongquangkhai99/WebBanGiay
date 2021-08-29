package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBContext {
	public static Connection getJDBCConnection()
	{
		final String url ="jdbc:mysql://localhost:3306/wish";
		final String user ="root";
		final String password ="AnhBao@251116";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static void main(String[] args) {
		Connection conn = getJDBCConnection();

		if(conn !=null)
		{
			System.out.println("Co ket noi");
		}
		else
		{
			System.out.println("Ko co ket noi");
		}
		
	}
	
}
