package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import entity.Account;

public class ManagerAccountDAO {
	Connection conn= null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	public List<Account> listAccount(int count)
	{
		List<Account> list = new ArrayList<>();
		try
		{
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("select * from Account limit 6 offset ?");
			stm.setInt(1, (count-1)*6);
			rs = stm.executeQuery();
			
			while(rs.next())
			{
				 list.add(new Account(
							rs.getInt("uID"),
							rs.getString("user"),
							rs.getString("pass"),
							rs.getInt("isSell"),
							rs.getInt("isAdmin")
						));
			}
			return list;
		}
		catch(Exception ex)
		{
			ex.getStackTrace();
		}
		return null;
	}
	public int getTotalAccount()
	{
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("select count(*) from Account");
			rs = stm.executeQuery();
			
			while(rs.next())
			{
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return 0;
	}
	public static void main(String[] args) {
		ManagerAccountDAO dao = new ManagerAccountDAO();
		
//		int total = dao.getTotalAccount();
//		
//		System.out.println(total%3);
		
		List<Account> list = dao.listAccount(1);
		list.forEach(t -> System.out.println(t));
		

	}
}
