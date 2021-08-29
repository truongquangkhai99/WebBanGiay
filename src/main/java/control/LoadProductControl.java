package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagerDAO;
import entity.Category;
import entity.Product;

@WebServlet(urlPatterns = "/loadProductByID")
public class LoadProductControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset = UTF-8");
		
		int id =Integer.parseInt(req.getParameter("id"));
		ManagerDAO dao = new ManagerDAO();
		Product product = dao.loadProductID(id);
		
		req.setAttribute("detail", product);
		
		List<Category> listCate = dao.listCategory();
		req.setAttribute("listCate", listCate);
		req.getRequestDispatcher("Edit.jsp").forward(req, resp);
	}
}
