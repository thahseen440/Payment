/**
 * 
 */

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validatePaymentForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "PaymentAPI",
 type : type,
 data : $("#formPayment").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
  location.reload(true);
 onEmployeeSaveComplete(response.responseText, status);

 }
 }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidIDSave").val($(this).data("ID"));
 $("#accNo").val($(this).closest("tr").find('td:eq(0)').text());
 $("#cusName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#billAmount").val($(this).closest("tr").find('td:eq(2)').text());
 $("#paymentType").val($(this).closest("tr").find('td:eq(3)').text());
 $("#paymentDate").val($(this).closest("tr").find('td:eq(4)').text());
 $("#status").val($(this).closest("tr").find('td:eq(5)').text());
});

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "PaymentAPI",
 type : "DELETE",
 data : "ID=" + $(this).data("id"),
 dataType : "text",
 complete : function(response, status)
 {

  location.reload(true);
 onPaymentDeleteComplete(response.responseText, status);

 }
 });
});

// CLIENT-MODEL================================================================
function validatePaymentForm()
{
// account number
if ($("#accNo").val().trim() == "")
 {
 return "Insert Account Number.";
 }
// NAME
if ($("#cusName").val().trim() == "")
 {
 return "Insert Customer Name.";
 } 

// Bill amount-------------------------------
if ($("#billAmount").val().trim() == "")
 {
 return "Insert Customer's Bill Amount'.";
 }
// is numerical value
var tmpBill = $("#billAmount").val().trim();
if (!$.isNumeric(tmpBill))
 {
 return "Insert a numerical value for Bill amount.";
 }
// convert to decimal price
//$("#billAmount").val(parseFloat(tmpbill).toFixed(2));
// Payment type------------------------
if ($("#paymentType").val().trim() == "")
 {
 return "Insert Payment Type.";
 }
 // payment Date------------------------
if ($("#paymentDate").val().trim() == "")
 {
 return "Insert payment Date.";
 }
 // status------------------------
if ($("#status").val().trim() == "")
 {
 return "Insert status.";
 }
return true;
}

function onPaymentSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);

 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidIDSave").val("");
 $("#formEmployee")[0].reset();
}

function onPaymentDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 