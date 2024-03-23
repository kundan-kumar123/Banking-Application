<%@page import="Dto.Customer"%>

<%@page import="Dto.Bank_Account"%>

<%@page import="Dao.BankDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Insert title here</title>

</head>

<h1>Welcome_to_balance_page</h1>

<body>

<% Long acno= (Long) request.getSession().getAttribute("ac_number");

BankDao bankdao=new BankDao();

Bank_Account bank_account=bankdao.find(acno);

Customer customer=bank_account.getCustomer();

%>

<h1>Hello <%if(customer.getGender().equals("male")) {%>Mr <%} else { %> Ms. <%}%> <%=customer.getcName() %></h1>

<h1>Hello:your account balance is :<%=bank_account.getAmount() %></h1>

</body>

</html>