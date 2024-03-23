package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dto.Bank_Account;
import Dto.Customer;

@WebServlet("/fetchactiveaccount")
public class Fetch_Active_Account extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		
	 List<Bank_Account>	list  =customer.getBanksccounts();
	 List<Bank_Account> list2 = new ArrayList<Bank_Account>();
	
		for(Bank_Account bank_account : list)
		{
			if(bank_account.isStatus())
			{
			list2.add(bank_account);
			System.out.print("status is active");
			}
			
			}
		req.getSession().setAttribute("list", list2);
		req.getRequestDispatcher("accounts.jsp").include(req, resp);
			
		}
	}


 