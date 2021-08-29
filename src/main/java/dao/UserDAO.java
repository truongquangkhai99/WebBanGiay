package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import context.DBContext;
import entity.Account;

public class UserDAO {
	Connection conn=null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	public Account getAccount(String user,String pass)
	{
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("select * from account where user = ? and pass= ?");
			stm.setString(1,user);
			stm.setString(2,pass);
			
			rs= stm.executeQuery();
			
			while(rs.next())
			{
				return new Account(
							 rs.getInt("uID")
							,rs.getString("user")
							,rs.getString("pass")
							,rs.getInt("isSell")
							,rs.getInt("isAdmin")
						 );
			}
		}
		catch(Exception ex)
		{
			ex.getStackTrace();
		}
		return null;
	}
	public Account checkAccountExist(String user)
	{
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("select * from account where user = ?");
			stm.setString(1, user);
			rs= stm.executeQuery();
			while(rs.next())
			{
				return new Account(
						     rs.getInt("uID")
							,rs.getString("user")
							,rs.getString("pass")
							,rs.getInt("isSell")
							,rs.getInt("isAdmin")
						);
			}
		}
		catch(Exception ex)
		{
			ex.getStackTrace();
		}
		
		return null;
	}
	public void insertUser(String user,String pass)
	{
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("insert into Account (user,pass,isSell,isAdmin) values (?,?,0,0)");
			stm.setString(1, user);
			stm.setString(2, pass);
			stm.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.getStackTrace();
		}

	}
	public static void main(String[] args) {
		UserDAO usDao = new UserDAO();
		
		usDao.insertUser("Anh", "123456");
	}
}
