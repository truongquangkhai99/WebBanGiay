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

@WebServlet(urlPatterns = "/search")
public class SearchControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html?charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String values= req.getParameter("txt");
		DAO dao = new DAO();
		
		List<Product> listSearch = dao.getProductBYID(values);
		req.setAttribute("listProduct1", listSearch);
		
		 List<Category> listCategory = dao.listCategory();  
		 Product productLast = dao.lastProduct();
		 req.setAttribute("listCategory", listCategory);
		 req.setAttribute("productLast", productLast);
		 
		 req.setAttribute("values", values);
		req.getRequestDispatcher("Home.jsp").forward(req, resp);
	}

}
