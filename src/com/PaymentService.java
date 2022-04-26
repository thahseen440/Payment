package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;




//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Payment;
@Path("/Payment")
public class PaymentService {
	
	Payment payObj = new Payment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment()
	 {
	 return payObj.readPayment();
	 }


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("accNo") String accNo,
	 @FormParam("cusName") String cusName,
	 @FormParam("billAmount") String billAmount,
	 @FormParam("paymentType") String paymentType,
	 @FormParam("paymentDate") String paymentDate,
	 @FormParam("status") String status)
	{
	 String output = payObj.insertPayment(accNo, cusName, billAmount, paymentType,paymentDate,status);
	return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	 String ID = paymentObject.get("ID").getAsString();
	 String accNo = paymentObject.get("accNo").getAsString();
	 String cusName = paymentObject.get("cusName").getAsString();
	 String billAmount = paymentObject.get("billAmount").getAsString();
	 String paymentType = paymentObject.get("paymentType").getAsString();
	 String paymentDate = paymentObject.get("paymentDate").getAsString();
	 String status = paymentObject.get("status").getAsString();
	 String output = payObj.updatePayment(ID,accNo,cusName,billAmount,paymentType,paymentDate,status);
	return output;
	}


	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String ID = doc.select("ID").text();
	 String output = payObj.deletePayment(ID);
	return output;
	}

}
