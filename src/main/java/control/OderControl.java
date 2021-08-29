package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/buy")
public class OderControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;chaset = UTF-8");
		
		Cookie [] arr = req.getCookies();
		
		for(Cookie o : arr)
		{
			o.setMaxAge(0);
			resp.addCookie(o);
		}
		
		resp.sendRedirect("home");
	}

}
