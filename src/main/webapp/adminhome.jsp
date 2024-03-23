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

<body>
	<h1>Welcome_to_Admin_home_page</h1>
	<% List<Bank_Account> list = (List <Bank_Account>)request.getSession().getAttribute("list"); %>
	
	<table border="1" cellspacing="0">
		<tr>
			<th>Account_number</th>
			<th>Account_Type</th>
			<th>Customer_name</th>
			<th>Customer_id</th>
			<th>Account_status</th>
			<th>Change_status</th>
		</tr>
	<% for(Bank_Account bank_account : list){ %>
		<tr>
			<th><%=bank_account.getAcc_no()%></th>
			<th><%=bank_account.getAccount_type()%></th>
			<th><%=bank_account.getCustomer().getcName()%></th>
			<th><%=bank_account.getCustomer().getCid()%></th>
			<th><%=bank_account.isStatus() %></th>
			<th><a href="Changestatus?accno=<%=bank_account.getAcc_no()%>"><button>Change Status</button></a></th>
		</tr>
	<%} %>
	
	</table>
</body>
</html>