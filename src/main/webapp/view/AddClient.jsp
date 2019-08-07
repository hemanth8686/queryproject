<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
div {
  width: 400px;
  border: 3px solid green;
  padding: 50px;
  margin: 20px;
}
table {
  border-collapse: collapse;
  width: 60%;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
  background-color: #4CAF50;
  color: white;
}
</style>
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addClient" method="post">
<table>

<tr>
<td colspan="3" align="right">
<input type="text" name="clientName" placeholder="clientName">

<td align="center">
<input type="submit" value="submit"> 
</td>
</td>


</tr>
</table>

<table>

<tr>

</tr>
</table>

<table>
<tr >
				<th nowrap="nowrap">Client Name</th>
				<th></th>
				
				
			</tr>
		<tr>
				<td></td>
				<c:forEach items="${clientList}" var="dlist">
					<tr>
						<td><c:out value="${dlist.clientName}" /></td>
						<td></td>
						
						
						
						
						
						
						</c:forEach>
					</tr>
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