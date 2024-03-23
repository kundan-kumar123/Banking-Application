<%@page import="Dto.Bank_Account"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<h>Welcome to active account page</h>
<body>
<% List<Bank_Account> list = (List<Bank_Account>) request.getSession().getAttribute("list"); 
if(list.isEmpty())
{%> 
<h1>No_active_accounts_found</h1>
<%}else{ %>
<h1>Select_bank_account</h1>	
<%for(Bank_Account bank_account : list){ %>
<a href="setactiveaccount?acno = <%=bank_account.getAcc_no() %> "><button><%=bank_account.getAcc_no() %></button></a>
<%} %>
<%} %>

</body>
</html>