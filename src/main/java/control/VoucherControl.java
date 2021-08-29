package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/voucher")
public class VoucherControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		String s = req.getParameter("nameVoucher");
		
		if(s.toUpperCase().equals("SALE10"))
		{
			HttpSession session = req.getSession();
			double newSum = (double) session.getAttribute("sum");
			
			session.setAttribute("sum", newSum - (newSum * 0.1));
			resp.sendRedirect("Cart.jsp");
		}
	}
}
