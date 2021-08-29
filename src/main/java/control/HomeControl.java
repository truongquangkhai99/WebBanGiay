package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.Category;
import entity.Product;

@WebServlet(urlPatterns = "/home")
public class HomeControl extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("text/html;charset= UTF-8");
		
		 DAO dao = new DAO();
		  
		  
		 List<Product> listProduct1 = dao.listProduct();
		 List<Category> listCategory = dao.listCategory();  
		 Product productLast = dao.lastProduct();
		 
		 
		 req.setAttribute("listProduct1", listProduct1);
		 req.setAttribute("listCategory", listCategory);
		 req.setAttribute("productLast", productLast);
		 
		 
		 req.getRequestDispatcher("Home.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(req, resp);
	}
}
