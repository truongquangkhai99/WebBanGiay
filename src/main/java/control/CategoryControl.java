package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import entity.Category;
import entity.Product;


@WebServlet(urlPatterns = "/category")
public class CategoryControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset= UTF-8");
		
		 String cateID = req.getParameter("cid");
		
		 DAO dao = new DAO();
		
		 List<Product> listProductID = dao.listProductByCID(cateID);
		 
		 
		 List<Category> listCategory = dao.listCategory();  
		 Product productLast = dao.lastProduct();
		 
		 req.setAttribute("listProduct1", listProductID);
		 req.setAttribute("listCategory", listCategory);
		 req.setAttribute("productLast", productLast);

		 req.setAttribute("Tag", cateID);
		 req.getRequestDispatcher("Home.jsp").forward(req, resp);
	}

}
