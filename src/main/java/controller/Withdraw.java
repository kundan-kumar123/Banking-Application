package Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BankDao;
import Dto.Bank_Account;
import Dto.BankTransaction;


@WebServlet("/withdraw")
public class Withdraw extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amt = req.getParameter("amnt");

		double amount = Double.parseDouble(amt);

		Long ac_no =   (Long) req.getSession().getAttribute("ac_number");
																																																													
		BankDao bank_Dao = new BankDao();

		Bank_Account bank_Account = bank_Dao.find(ac_no);
		
		if(bank_Account.getAmount()<amount) {
			resp.getWriter().print("<h1>Insufficient balance your available balance is"+ bank_Account.getAmount() +"</h1>");
		}
		
		else {
			if(amount>bank_Account.getAcc_limit()) {
				resp.getWriter().print("<h1>You are exceeding your account limit your account limit is"+ bank_Account.getAcc_limit() +"</h1>");					
			}
			else {
				bank_Account.setAmount(bank_Account.getAmount()-amount);
				
				BankTransaction bank_transaction = new BankTransaction();
				bank_transaction.setDeposit(0);
				bank_transaction.setWithdraw(amount);
				bank_transaction.setBalance(bank_Account.getAmount());
				bank_transaction.setDate_time(LocalDateTime.now());

				List<BankTransaction> list = bank_Account.getList();		
				list.add(bank_transaction);
				bank_Account.setList(list);
				
				
				bank_Dao.update_status_detail(bank_Account);
				resp.getWriter().print("<h1>Amount Withdrawn successfully </h1>");
				req.getRequestDispatcher("Accont_Home_Page.html").include(req, resp);
							
			}
		}		
	}
	

}