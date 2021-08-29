package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ManagerDAO;
import entity.Account;

@WebServlet(urlPatterns = "/edit")
public class EditControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset = UTF-8");
		req.setCharacterEncoding("UTF-8");
		String pid=req.getParameter("id");
		String name = req.getParameter("name");
		String image = req.getParameter("image");
		String price = req.getParameter("price");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String category = req.getParameter("category");
		ManagerDAO dao = new ManagerDAO();	
		dao.edit(name, image, price, title, description, category, pid);
		resp.sendRedirect("managerProduct");
	}
}
