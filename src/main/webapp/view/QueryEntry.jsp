<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function validateForm(){
	 var raisedDate =  $('#raisedDate').val();
	 var raisedBy =  $('#raisedBy').val();
	 var raisedThrough =  $('#raisedThrough').val();
	 var status =  $('#status').val();
	 var attentedBy =  $('#attentedBy').val();
	 
	 
	 
	 
	 
	 if(raisedDate=='' ){
		 alert("Please enter RaisedDate")
		 return false;
		 
		 
	 }
	 
	 if(raisedBy=='' ){
		 alert("Please enter RaisedBy");
		 return false;
		 
		 
	 }
	 if(raisedThrough=='' ){
		 alert("Please enter RaisedThrough");
		 return false;
		 
		 
	 }
	 if(status=='' ){
		 alert("Please enter Status");
		 return false;
		 
		 
	 }
	 if(attentedBy=='' ){
		 alert("Please enter AttentedBy")
		 return false;
		 
		 
	 }
	 else{
		 true;
	 }
	
	
	
}
</script>

<style type="text/css">
body {
  background-color: gray;
}
body {
  background-image: url("https://png.pngtree.com/thumb_back/fw800/back_pic/04/55/96/305864ee4494d9f.JPG");
}

.button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
</style>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="queryEnter" method="post">
<table>
<tr>




				
<!-- <td>Client:<input type="text" name="client"></td> -->


<td>RAISEDDATE:<input type="date" id="raisedDate" name="raisedDate">
<td>	Client:	<select name='client' id="client">
    <c:forEach items="${clientList}" var="dlist">
            <option value="${dlist.clientName}">${dlist.clientName}</option>
    </c:forEach>
</select></td>
<td>RAISEDBY:<input type="text" id="raisedBy" name="raisedBy">
<td>RaisedThrough:


<select name="raisedThrough" id="raisedThrough">
<option value="mail">Mail</option>
<option value="call">call</option>
</select>
</td>


<!-- <td>Query/Enhanchement:
 <textarea rows="4" cols="50" name="query">
</textarea>  -->
<td>	ATTENTEDBY:	<select name='attentedBy' id="attentedBy">
    <c:forEach items="${employeeList}" var="dlist">
            <option value="${dlist.employeeName}">${dlist.employeeName}</option>
    </c:forEach>
</select></td>
<td>	COMPLETEDBY:	<select name='completedBy' id="completedBy">
<option value="">Select</option>
    <c:forEach items="${employeeList}" var="dlist">
            <option value="${dlist.employeeName}">${dlist.employeeName}</option>
    </c:forEach>
</select></td>
<td>COMPLETEDDATE:<input type="date" name="completedDate" id="completedDate">
<td>Status:

<select name="status" id="status">
<option value="pending">Pending</option>
<option value="completed">Completed</option>
</select>
</td>
<td>Priority:

<select name="Priority" >
<option value="high">High</option>
<option value="medium">medium</option>
<option value="low">low</option>
</select>
</td>


</tr>
<tr></tr>

<tr></tr>
<tr></tr>


</table>
<table >
<tr align="right"  ><td colspan="3">Query/Enhanchement:</td>
<td align="left"> <textarea rows="4" cols="50" name="query">
</textarea></td> </tr>
</table>
<table>

<tr  ><td colspan="10" rowspan="5" align="center"><input type="submit" value="submit" onclick="return validateForm()"></td></tr>

</table>

	<table>
		<tr>
		<td ><a
								href="homeMenu">Back</a></td>

				
				</tr>			
		</table>
</form>

</body>
</html>