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

	<form action="addEmployee" method="post">

		<table>
			<tr>
				<td>Employee Name:<input type="text" name="employeeName">
				</td>
				<td>Employee Email:<input type="text" name="employeeEmail">
				</td>
				<td>MAILTYPE:<select name="mailType">

						<option value="To">TO</option>
						<option value="CC">CC</option>
				</select>
				</td>
				<td><input type="submit" value="submit"></td>
			</tr>
		</table>
		<table>
		
		
		
			<c:forEach items="${employeeList}" var="dlist">
				<tr>
				<tr>

					<td nowrap="nowrap">EMPLOYEE ID:<c:out value="${dlist.id}" /></td>
					<td nowrap="nowrap">EMPLOYEE NAME:<c:out
							value="${dlist.employeeName}" /></td>


					<td nowrap="nowrap">EMPLOYEEE MAIL:<c:out
							value="${dlist.employeeMail}" /></td>

				</tr>


			</c:forEach>
		</table>









	</form>

</body>
</html>