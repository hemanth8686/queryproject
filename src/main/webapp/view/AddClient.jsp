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
<form action="addClient" method="post">
<table>

<tr>
<td>
<input type="text" name="clientName" placeholder="clientName">


</td>

<td>
<input type="submit" value="submit"> 
</td>
</tr>
</table>

<table>

<tr>

</tr>
</table>

<table>
<c:forEach items="${clientList}" var="dlist">
					<tr>
						<td></td>
					<tr>

					
						<td nowrap="nowrap">CLIENT:<c:out value="${dlist.clientName}" /></td>
					
					</tr>


				</c:forEach>
</table>
</form>

</body>
</html>