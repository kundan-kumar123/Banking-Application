
package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BankDao;
import Dao.CustomerDao;
import Dto.Bank_Account;
import Dto.Customer;

@WebServlet("/createbankaccount")
public class Create_bank_Account extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acc_type=req.getParameter("accounttype");
		Customer customer=(Customer) req.getSession().getAttribute("customer");
		List<Bank_Account> list=customer.getBanksccounts();
		boolean flag=true;
		for(Bank_Account bank_account:list)
		{
			if(bank_account.getAccount_type().equals(acc_type))
			{
				flag=false;
				break;
			}
		}
		if(flag==true)
		{
			Bank_Account bank_account=new Bank_Account();
			bank_account.setAccount_type(acc_type);
			if(bank_account.getAccount_type().equals("savings"))
			bank_account.setAcc_limit(10000);
			else
			bank_account.setAcc_limit(15000);
			bank_account.setCustomer(customer);
			
			BankDao bankDao=new BankDao();
			bankDao.save_account(bank_account);
			
			List< Bank_Account> list2=list;
			list2.add(bank_account);
			customer.setBanksccounts(list2);
			
			CustomerDao customerDao=new CustomerDao();
			customerDao.update(customer);
			resp.getWriter().print("<h1>Your account has created successfully wait for admin approval</h>");
			
		}
		else
		{
			resp.getWriter().print("<h1>Account is existed</h>");
		}
	}

}