<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Payment"%>
<%
//Save---------------------------------
if (request.getParameter("accNo") != null)
{
Payment payObj = new Payment();
String stsMsg = "";
//Insert--------------------------
if (request.getParameter("hidIDSave") == "")
{
stsMsg = payObj.insertPayment(request.getParameter("accNo"),
request.getParameter("cusName"),
request.getParameter("empSalary"),
request.getParameter("billAmount"),
request.getParameter("paymentDate"),
request.getParameter("status"));
}
else//Update----------------------
{
stsMsg = payObj.updatePayment(request.getParameter("hidIDSave"),
request.getParameter("accNo"),
request.getParameter("cusName"),
request.getParameter("billAmount"),
request.getParameter("paymentType"),
request.getParameter("paymentDate"),
request.getParameter("status"));
}
session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidIDDelete") != null)
{
Payment payObj = new Payment();
String stsMsg =
payObj.deletePayment(request.getParameter("hidIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment.js"></script>
<title>Payment Management</title>
</head>
<body>
<h1>Payment Management</h1>

<form id="formPayment" name="formPayment" method="post" action="payment.jsp">
Account Number:
<input id="accNo" name="accNo" type="text"
 class="form-control form-control-sm">
<br> Customer Name:
<input id="cusName" name="cusName" type="text"
 class="form-control form-control-sm">
<br> Bill Amount:
<input id="billAmount" name="billAmount" type="text"
 class="form-control form-control-sm">
<br> Payment Type:
<input id="paymentType" name="paymentType" type="text"
 class="form-control form-control-sm">
<br>
<br> Payment Date:
<input id="paymentDate" name="paymentDate" type="text"
 class="form-control form-control-sm">
<br>
<br> Status:
<input id="status" name="status" type="text"
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<%
 out.print(session.getAttribute("statusMsg"));
%>
<br>
<div id="divPaymentGrid">
<%

Payment payObj = new Payment();
out.print(payObj.readPayment());
%>
</div>
</body>
</html>