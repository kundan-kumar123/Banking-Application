package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BankDao;
import Dto.Bank_Account;
@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String name=req.getParameter("name");
		BankDao bankDao = new BankDao();
		
		if(email.equals("admin@gmail.com") && name.equals("admin"))
		{
			List<Bank_Account> list=bankDao.fetchAll();
			for (Bank_Account bank_Account : list) {
				System.out.println(bank_Account.getAcc_no());
			}
			req.getSession().setAttribute("list", list);
			//resp.getWriter().println("<h1>admin login sucess</h1>");
			
			req.getRequestDispatcher("adminhome.jsp").include(req, resp);
		}
		else
		{
			resp.getWriter().print("<h1>Invalid credential</h1>");
			req.getRequestDispatcher("Admin_login.html").include(req, resp);
		}
		
	}
	
	

}
