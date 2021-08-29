package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import entity.Category;
import entity.Product;

public class DAO {
	
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	public List<Product> listProduct()
	{
		List<Product> list = new ArrayList<>();
		
		
		try {
			conn = DBContext. getJDBCConnection();
			stm = conn.prepareStatement("select * from product limit 6");
			rs = stm.executeQuery();
			
			while (rs.next()) {
				list.add(new Product(rs.getInt(1)
						,rs.getString(2)
						,rs.getString(3)
						,rs.getDouble(4)
						,rs.getString(5)
						,rs.getString(6)
						));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(conn !=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public List<Product> listNextProduct(int amount)
	{
		List<Product> list = new ArrayList<>();
		try {
			conn = DBContext. getJDBCConnection();
			stm = conn.prepareStatement("select * from product limit 6 offset ?");
			stm.setInt(1, amount);
			rs = stm.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1)
						,rs.getString(2)
						,rs.getString(3)
						,rs.getDouble(4)
						,rs.getString(5)
						,rs.getString(6)
						));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(conn !=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public List<Category> listCategory()
	{
		List<Category> listC = new ArrayList<>();
		
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("select * from category");
			
			rs = stm.executeQuery();
			
			while(rs.next())
			{
				listC.add(new Category(
							 rs.getInt("cid")
							,rs.getString("cname")
						));
			}
			return listC;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
		
	}
	public Product lastProduct()
	{
		
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("select  * from product order by id desc limit 1");
			rs= stm.executeQuery();
			
			while(rs.next())
			{
				return new Product(
						 rs.getInt(1)
						,rs.getString(2)
						,rs.getString(3)
						,rs.getDouble(4)
						,rs.getString(5)
						,rs.getString(6)
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Product> listProductByCID(String cateID)
	{ 
		List<Product> listProduct = new ArrayList<>();
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("select * from product where cateID= ?");
			stm.setString(1, cateID);
			
			rs = stm.executeQuery();
			while (rs.next()) {
				
				listProduct.add(new Product(
						 rs.getInt(1)
						,rs.getString(2)
						,rs.getString(3)
						,rs.getDouble(4)
						,rs.getString(5)
						,rs.getString(6)
						));
			}
			return listProduct;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public Product product(String cid)
	{
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("select * from product where id= ?");
			stm.setString(1, cid);
			rs = stm.executeQuery();
			while (rs.next()) {
				return new Product(
						     rs.getInt(1)
							,rs.getString(2)
							,rs.getString(3)
							,rs.getDouble(4)
							,rs.getString(5)
							,rs.getString(6)
						);
				
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
	public List<Product> getProductBYID(String values)
	{	
		List<Product> list = new ArrayList<>();
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("select * from product where name like ?");
			stm.setString(1, "%"+values+"%");
			rs = stm.executeQuery();
			while(rs.next())
			{
				list.add(new Product(
						 rs.getInt(1)
						,rs.getString(2)
						,rs.getString(3)
						,rs.getDouble(4)
						,rs.getString(5)
						,rs.getString(6)
						));
			}
			return list;
		}
		catch(Exception ex) {
			ex.getStackTrace();
		}
		finally {
			if(conn !=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public static void main(String[] args) {
		DAO dao = new DAO();
		List<Product> t = dao.getProductBYID("2");
		t.forEach((y) ->System.out.println(y));
	}
}
