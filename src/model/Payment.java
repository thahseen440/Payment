package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eb_db?serverTimezone=UTC","root","");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertPayment(String accNo, String cusName, String billAmount, String paymentType,String paymentDate,String status)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into payment(`ID`,`Account_No`,`Cus_Name`,`BillAmount`,`Payment_Type`,`PayDate`,`Status`)"
	 + " values (?, ?, ?, ?, ?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, accNo);
	 preparedStmt.setString(3, cusName);
	 preparedStmt.setDouble(4, Double.parseDouble(billAmount));
	 preparedStmt.setString(5, paymentType);
	 preparedStmt.setString(6, paymentDate);
	 preparedStmt.setString(7, status);
	// execute the statement

	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the payment details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readPayment()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Account No</th><th>Customer Name</th>" +
	 "<th>Bill Amount</th>" +
	 "<th>Payment type</th>" +"<th>Payment Date</th>" +"<th>Status</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from payment";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String ID = Integer.toString(rs.getInt("ID"));
	 String Account_No = rs.getString("Account_No");
	 String Cus_Name = rs.getString("Cus_Name");
	 String BillAmount = Double.toString(rs.getDouble("BillAmount"));
	 String Payment_Type = rs.getString("Payment_Type");
	 String PayDate = rs.getString("PayDate");
	 String Status = rs.getString("status");
	 // Add into the html table
	 output += "<tr><td>" + Account_No + "</td>";
	 output += "<td>" + Cus_Name + "</td>";
	 output += "<td>" + BillAmount + "</td>";
	 output += "<td>" + Payment_Type + "</td>";
	 output += "<td>" + PayDate + "</td>";
	 output += "<td>" + Status + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' "
			 + "class='btnUpdate btn btn-secondary' data-id='" + ID + "'></td>"
			 + "<td><input name='btnRemove' type='button' value='Remove' "
			 + "class='btnRemove btn btn-danger' data-id='" + ID + "'></td></tr>";
			 } 
			  con.close();
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updatePayment(String ID, String accNo, String cusName, String billAmount, String paymentType,String paymentDate,String status)

	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE payment SET Account_No=?,Cus_Name=?,BillAmount=?,Payment_Type=?,PayDate=?,Status=?   WHERE ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, accNo);
	 preparedStmt.setString(2, cusName);
	 preparedStmt.setDouble(3, Double.parseDouble(billAmount));
	 preparedStmt.setString(4, paymentType);
	 preparedStmt.setString(5, paymentDate);
	 preparedStmt.setString(6, status);
	 preparedStmt.setInt(7, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deletePayment(String ID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from payment where ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

}
