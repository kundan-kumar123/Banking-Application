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
import Dto.BankTransaction;
import Dto.Bank_Account;

@WebServlet("/deposit")
public class Deposit extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amt = req.getParameter("amnt");
		double amount = Double.parseDouble(amt);
		Long acno = (Long) req.getSession().getAttribute("ac_number");
		
		BankDao bankDao = new BankDao();
		Bank_Account bank_Account = bankDao.find(acno);
		bank_Account.setAmount(bank_Account.getAmount()+amount);
		
		
		BankTransaction bankTransaction = new BankTransaction();
		bankTransaction.setDeposit(amount);
		bankTransaction.setWithdraw(0);
		bankTransaction.setBalance(bank_Account.getAmount());
		bankTransaction.setDate_time(LocalDateTime.now());
		List<BankTransaction> list = bank_Account.getList();
		list.add(bankTransaction);
		bank_Account.setList(list);
		
		
		bankDao.update_the_details(bank_Account);
		resp.getWriter().print("<h1>amount deposited successfully</h>");
	}

}
