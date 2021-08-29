package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import entity.Product;

@WebServlet(urlPatterns = "/searchAjax")
public class SearchAjaxControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String values= req.getParameter("txt");
		DAO dao = new DAO();
		List<Product> list = dao.getProductBYID(values);
		PrintWriter out = resp.getWriter();
		for(Product o: list)
		{
			out.println(" <div class=\"product col-12 col-md-6 col-lg-4\">\r\n"
					+ "                                <div class=\"card\">\r\n"
					+ "                                    <img class=\"card-img-top\" src=\""+o.getImage()+"\" alt=\"Card image cap\">\r\n"
					+ "                                  		<div class=\"card-body\">\r\n"
					+ "                                        <h4 class=\"card-title show_txt\"><a href=\"detail?cid="+o.getId()+"\" title=\"View Product\">"+o.getName()+"</a></h4>\r\n"
					+ "                                        <p class=\"card-text show_txt\">"+o.getTitle()+"</p>\r\n"
					+ "                                        <div class=\"row\">\r\n"
					+ "                                            <div class=\"col\">\r\n"
					+ "                                                <p class=\"btn btn-danger btn-block\">"+o.getPrice()+"$</p>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"col\">\r\n"
					+ "                                                <a href=\"cart?id="+o.getId()+"\" class=\"btn btn-success btn-block\">Add to cart</a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div> \r\n" + "                                </div>\r\n"
					+ "                            </div>");
		}
	}
}
