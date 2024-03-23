package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import Dao.CustomerDao;
import Dto.Customer;

@WebServlet("/customerSignUp")
public class Customer_sign_up extends HttpServlet{
@Override
public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

		String username = req.getParameter("cname");

        String mob = req.getParameter("mobile");      
        Long mob_no = Long.parseLong(mob);

        String password = req.getParameter("password");

        String email = req.getParameter("Email");

        String gender = req.getParameter("gender");

        String dob = req.getParameter("dob");

	Date date = Date.valueOf(dob);
	Period period = Period.between(date.toLocalDate(), LocalDate.now());
	int age = period.getYears();
	Customer customer = new Customer();
	CustomerDao customerDao = new CustomerDao();
	
	if(age > 18)
	{
		
		
		//res.getWriter().print("<h1>HE IS ELIGIBLE TO CREATE BANK ACCOUNT</h1>");
		//customer.setCid(age); it will get generated randomly
		if(customerDao.check1(email).isEmpty() && customerDao.check2(mob_no).isEmpty())
		{
		customer.setcName(username);
		customer.setEmail(email);
		 customer.setGender(gender);
		 customer.setMobile(mob_no);
		 customer.setDob(date);
		 customer.setPassword(password);
		 
		 customerDao.save(customer);
		 res.getWriter().print("<h1>Account has been created successfully. Your custid is:"+customer.getCid()+"</h1>");
		 req.getRequestDispatcher("Customer_login.html").include(req, res);
		 
	
	    }
		else {
			if(!customerDao.check1(email).isEmpty()) {
				res.getWriter().print("<h1>Email already exists</h1>");
				req.getRequestDispatcher("Customer_Sign_Up.html").include(req, res);
			}
			if(!customerDao.check2(mob_no).isEmpty()) {
				res.getWriter().print("<h1>Mobile number already exists</h1>");
				req.getRequestDispatcher("Customer_Sign_Up.html").include(req, res);
			}
		}
	
	
	}
	else {
		res.getWriter().print("<h1>you are underage</h1>");
		req.getRequestDispatcher("Customer_Sign_Up.html").include(req, res);
	}
	}
}


