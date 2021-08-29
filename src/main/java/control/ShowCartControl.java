package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.Product;

@WebServlet(urlPatterns = "/print")
public class ShowCartControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
        Cookie arr[] = req.getCookies();
        List<Product> list = new ArrayList<>();
        DAO dao = new DAO();
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                String txt[] = o.getValue().split("/");
                for (String s : txt) {
                    list.add(dao.product(s));
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            int count = 1;
            for (int j = i+1; j < list.size(); j++) {
                if(list.get(i).getId() == list.get(j).getId()){
                    count++;
                    list.remove(j);
                    j--;
                }
               
            }
            list.get(i).setAmount(count);
        }
        double total = 0;
        for (Product o : list) {
            total = total + o.getAmount() * o.getPrice();
        }
        
        HttpSession session = req.getSession();
        session.setAttribute("list", list);
        session.setAttribute("total", total);
        session.setAttribute("vat", 0.1 * total);
        session.setAttribute("sum", 1.1 * total);
        
        
        req.getRequestDispatcher("Cart.jsp").forward(req, resp);
	}
}
