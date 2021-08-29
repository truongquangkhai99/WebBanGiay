package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns =  "/cart")
public class CartControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
        String id = req.getParameter("id");
        Cookie arr[] = req.getCookies();
        
        String txt = "";
        for (Cookie o : arr) {
           if (o.getName().equals("id")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
               resp.addCookie(o);
            }
        }
        if (txt.isEmpty()) {
            txt = id;
        } else {
            txt = txt + "/" + id;
        }
        Cookie c = new Cookie("id", txt);
        c.setMaxAge(60 * 60 * 24);
        resp.addCookie(c);
       
        resp.sendRedirect("print");
	}
}
