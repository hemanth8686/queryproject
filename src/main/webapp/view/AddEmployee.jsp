<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
/* body {
  background-image: url("https://png.pngtree.com/thumb_back/fw800/back_pic/04/55/96/305864ee4494d9f.JPG");
} */
div {
  width: 800px;
  border: 3px solid green;
  padding: 50px;
  margin: 20px;
}
table {
  border-collapse: collapse;
  width: 100%;
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
		<div>
		<table>
		
		
		
		
				<tr >
				<th nowrap="nowrap">EMPLOYEE ID</th>
				<th></th>
					<th></th>
				<th>NAME</th>
					<th></th>
				<th>EMAIL</th>
					<th></th>
					<th></th>
				
			</tr>
		<tr>
				<td></td>
				<c:forEach items="${employeeList}" var="dlist">
					<tr>
						<td><c:out value="${dlist.id}" /></td>
						<td></td>
						<td></td>
						<td><c:out value="${dlist.employeeName}" /></td>
						<td></td>
						<td><c:out value="${dlist.employeeMail}" /></td>
						<td></td>
						<td></td>
						<td></td>
						
						
						
						
						
						</c:forEach>
					</tr>
		</table>
			<table>
		<tr>
		<td ><a
								href="homeMenu">Back</a></td>

				
				</tr>
					<tr>
		<td ><a
								href="homeMenu">Back</a></td>

				
				</tr>					
		</table>
		</div>









	</form>

</body>
</html>