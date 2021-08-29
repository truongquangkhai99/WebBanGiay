package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.Account;

@WebServlet(urlPatterns = "/login")
public class LoginControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html?charset=UTF-8");
		String user= req.getParameter("user");
		String pass= req.getParameter("pass");
		
		UserDAO userDao = new UserDAO();
		
		Account account = userDao.getAccount(user, pass);
		
		if(account == null)
		{
			req.setAttribute("mess", "Moi ban nhap lai !");
			req.getRequestDispatcher("Login.jsp").forward(req, resp);
			
		}
		else
		{
			//req.setAttribute("account", account);
			//req.getRequestDispatcher("home").forward(req, resp);
			HttpSession session = req.getSession();
			session.setAttribute("accountSession", account);
			session.setMaxInactiveInterval(60*60*24);
			resp.sendRedirect("home");
		}
		
	}
}
