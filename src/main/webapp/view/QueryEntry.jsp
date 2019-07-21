<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="queryEnter" method="post">
<table>
<tr>




				
<!-- <td>Client:<input type="text" name="client"></td> -->


<td>RAISEDDATE:<input type="date" name="raisedDate">
<td>	Client:	<select name='client'>
    <c:forEach items="${clientList}" var="dlist">
            <option value="${dlist.clientName}">${dlist.clientName}</option>
    </c:forEach>
</select></td>
<td>RAISEDBY:<input type="text" name="raisedBy">
<td>RaisedThrough:


<select name="raisedThrough">
<option value="mail">Mail</option>
<option value="call">call</option>
</select>
</td>


<td>Query/Enhanchement:
 <textarea rows="4" cols="50" name="query">
</textarea> 
<td>	ATTENTEDBY:	<select name='attentedBy'>
    <c:forEach items="${employeeList}" var="dlist">
            <option value="${dlist.employeeName}">${dlist.employeeName}</option>
    </c:forEach>
</select></td>
<td>	COMPLETEDBY:	<select name='completedBy'>
<option value="">Select</option>
    <c:forEach items="${employeeList}" var="dlist">
            <option value="${dlist.employeeName}">${dlist.employeeName}</option>
    </c:forEach>
</select></td>
<td>COMPLETEDDATE:<input type="date" name="completedDate">
<td>Status:

<select name="status">
<option value="pending">Pending</option>
<option value="completed">COMPLETED</option>
</select>
</td>
<td>Priority:

<select name="Priority">
<option value="high">High</option>
<option value="medium">medium</option>
<option value="low">low</option>
</select>
</td>


</tr>

<tr align="center" ><td colspan="1"><input type="submit" value="sumit query"></td></tr>

</table>
</form>

</body>
</html>