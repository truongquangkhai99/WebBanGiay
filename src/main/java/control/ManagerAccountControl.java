package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagerAccountDAO;
import entity.Account;

@WebServlet(urlPatterns = "/managerAccount")
public class ManagerAccountControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		ManagerAccountDAO dao = new ManagerAccountDAO();
		int total = dao.getTotalAccount();
		int page = total/6;
		if(total % 6 != 0)
		{
			page++;
		}
		
		req.setAttribute("page", page);
		
		String pageID = req.getParameter("pageID");
		if(pageID == null)
		{
			pageID = "1";
			
		}
		List<Account> list = dao.listAccount(Integer.parseInt(pageID));
		req.setAttribute("listAccount", list);
		
		req.setAttribute("pageID", pageID);
		req.getRequestDispatcher("ManagerAccount.jsp").forward(req, resp);
	}
}
