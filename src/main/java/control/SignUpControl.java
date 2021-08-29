package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.Account;

@WebServlet(urlPatterns = "/signup")
public class SignUpControl extends  HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		String repass = req.getParameter("repass");
		
		if(!pass.equals(repass))
		{
			resp.sendRedirect("Login.jsp");
		}
		else
		{
			UserDAO usDAO = new UserDAO();
			Account us = usDAO.checkAccountExist(user);
			if(us == null)
			{
				usDAO.insertUser(user, pass);
				resp.sendRedirect("home");
			}
			else
			{
				resp.sendRedirect("Login.jsp");
			}
		}
	}

}
