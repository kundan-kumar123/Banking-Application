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

@WebServlet("/Changestatus")
public class Change_Status extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acno = req.getParameter("accno");
		System.out.println(acno);
		long accno = Long.parseLong(acno);
		BankDao bankDao = new BankDao();
		Bank_Account bankAccount = bankDao.find(accno);
		if(bankAccount.isStatus()) {
			bankAccount.setStatus(false);
		}
		else {
			bankAccount.setStatus(true);
		}
		bankDao.update_the_details(bankAccount);
		resp.getWriter().print("<h1>Status got updated</h>");
		// Here we are going to take the updated information from BankAccount table
		List<Bank_Account> list=bankDao.fetchAll();
		req.getSession().setAttribute("list", list);
		req.getRequestDispatcher("adminhome.jsp").include(req, resp);
	}

}
