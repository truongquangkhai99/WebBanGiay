package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagerDAO;

@WebServlet(urlPatterns = "/delete")
public class DeleteControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		ManagerDAO dao = new ManagerDAO();
		dao.deleteProducByID(id);
		
		resp.sendRedirect("managerProduct");
		
	}
}
