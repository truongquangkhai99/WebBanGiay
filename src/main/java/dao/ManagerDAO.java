package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import entity.Category;
import entity.Product;

public class ManagerDAO {
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	public List<Product> listProductByUser(String isSell)
	{
		List<Product> list = new ArrayList<>();
		try{
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("select * from product where sell_ID = ?");
			stm.setString(1, isSell);
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
		catch(Exception ex)
		{
			ex.getStackTrace();
		}
		return null;
	}
	public void deleteProducByID(int id)
	{
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("delete from product where id = ? ");
			stm.setInt(1, id);
			stm.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.getStackTrace();
		}
		
	}
	public List<Category> listCategory()
	{
		List<Category> listCate = new ArrayList<>();
		try
		{
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement("select * from category");
			rs = stm.executeQuery();
			while(rs.next())
			{
				listCate.add(new Category(
						 	 rs.getInt("cid")
							,rs.getString("cname")
						));
			}
			return listCate;
		}
		catch(Exception ex)
		{
			ex.getStackTrace();
		}
		return null;
	}
	public void insert(String name,String image,String price,String title,String description ,String category,int id)
	{
		String sql = "insert into product(name,image,price,title,description,cateID,sell_ID) values (?,?,?,?,?,?,?)";
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			stm.setString(2, image);
			stm.setString(3, price);
			stm.setString(4, title);
			stm.setString(5, description);
			stm.setString(6, category);
			stm.setInt(7, id);
			stm.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.getStackTrace();
		}
	}
	public void edit(String name,String image,String price,String title,String description ,String category,String pid)
	{
		String sql = "update product set name = ?,image = ?,price = ?,title = ?,description = ?,cateID = ? where id = ?";
		try {
			conn = DBContext.getJDBCConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			stm.setString(2, image);
			stm.setString(3, price);
			stm.setString(4, title);
			stm.setString(5, description);
			stm.setString(6, category);
			stm.setString(7, pid);
			stm.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.getStackTrace();
		}
	}
	public Product loadProductID(int id)
	{
		try {
			conn = DBContext.getJDBCConnection();
			
			stm = conn.prepareStatement("select * from product where id = ?");
			stm.setInt(1, id);
			rs = stm.executeQuery();
			
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
		}
		catch(Exception ex)
		{
			ex.getStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		ManagerDAO dao = new ManagerDAO();
		Product ls = dao.loadProductID(1);
		System.out.println(ls);
	}
}
