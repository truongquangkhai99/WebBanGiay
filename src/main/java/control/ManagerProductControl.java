package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ManagerDAO;
import entity.Account;
import entity.Category;
import entity.Product;

@WebServlet(urlPatterns = "/managerProduct")
public class ManagerProductControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = req.getSession();
		ManagerDAO dao = new ManagerDAO();
		Account ac = (Account)session.getAttribute("accountSession");
		
		
		List<Product> list = dao.listProductByUser(Integer.toString(ac.getIsSell()));
		req.setAttribute("list", list);
		
		List<Category> listCate = dao.listCategory();
		req.setAttribute("listCate", listCate);
		req.getRequestDispatcher("ManagerProduct.jsp").forward(req, resp);

	}
}
