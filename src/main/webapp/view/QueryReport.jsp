<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>

<link href="style.css" rel="stylesheet" type="text/css">
<title>Table V04</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">
td {
	vertical-align: bottom;
}

th {
	vertical-align: bottom;
}

<
style>body {
	font-family: "Lato", sans-serif;
}

.tablink {
	background-color: #555;
	color: white;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 14px 16px;
	font-size: 17px;
	width: 25%;
}

.tablink:hover {
	background-color: #777;
}

TD {
	font-family: Arial;
	font-size: 10pt;
}
/* Style the tab content */
.tabcontent {
	color: white;
	display: none;
	padding: 50px;
	text-align: center;
}

#London {
	background-color: red;
}

#Paris {
	background-color: green;
}

#Tokyo {
	background-color: rgb(211, 211, 211);
}

#Oslo {
	background-color: orange;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="queryReport" method="post">
		<table>
			<table width="60%">
				<tr>
					<td>FromDate:<input type="date" name="fromDate">
					</td>
					<td>ToDate:<input type="date" name="toDate">
					</td>
					<td><input type="submit" value="submit"></td>
				</tr>
			</table>

			<table>

				<tr style="bgcolor: 4682B4">
				<tr id="Tokyo">
					<th>ID</th>
					<th>RAISEDDATE</th>
					<th>RAISEDBY</th>
					<th>client</th>
					<th>query</th>
					<th nowrap="nowrap">attentedBy</th>
					<th>raisedThrough</th>
					<th>closedDate</th>
					<th>closedBy</th>
					<th>status</th>
					<th>Update/Delete</th>
				</tr>



				<c:forEach items="${queryReport}" var="dlist">

					<c:if test="${dlist.queryStatus == 1}">




						<tr class="tabcontent" bgcolor="green">
							<td></td>
						<tr bgcolor="green">

							<td nowrap="nowrap"><c:out value="${dlist.queryId}" /></td>

							<td nowrap="nowrap"><fmt:formatDate pattern="dd-MM-yyyy"
									value="${dlist.raisedDate}" /></td>

							<td nowrap="nowrap"><c:out value="${dlist.raisedBy}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.client}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.query}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.attentedBy}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.raisedThrough}" /></td>
							<td nowrap="nowrap"><fmt:formatDate pattern="dd-MM-yyyy"
									value="${dlist.closedDate}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.closedBy}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.status}" /></td>


							<%-- <td nowrap="nowrap"><select name="status"> 
            <option ><c:out value="${dlist.status}" /></option>
           <option value="pending">Pending</option>
<option value="completed">COMPLETED</option> --%>
							</select>
							</td>

							<td nowrap="nowrap"><a
								href="gotoupdateQuery?Id=<c:out value="${dlist.queryId}" />">Update</a>
<a
								href="deleteQuery?Id=<c:out value="${dlist.queryId}" />">Delete</a>
							</td>
							<td nowrap="nowrap">

							</td>
					</c:if>
				</c:forEach>






				</tr>











				</tr>


				<c:forEach items="${queryReport}" var="dlist">
					<c:if test="${dlist.queryStatus == 2}">





						<tr class="tabcontent" >
							<td></td>
						<tr bgcolor="Red">

							<td nowrap="nowrap"><c:out value="${dlist.queryId}" /></td>

							<td nowrap="nowrap"><fmt:formatDate pattern="dd-MM-yyyy"
									value="${dlist.raisedDate}" /></td>

							<td nowrap="nowrap"><c:out value="${dlist.raisedBy}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.client}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.query}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.attentedBy}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.raisedThrough}" /></td>
							<td nowrap="nowrap"><fmt:formatDate pattern="dd-MM-yyyy"
									value="${dlist.closedDate}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.closedBy}" /></td>
							<td nowrap="nowrap"><c:out value="${dlist.status}" /></td>


							<%-- <td nowrap="nowrap"><select name="status"> 
            <option ><c:out value="${dlist.status}" /></option>
           <option value="pending">Pending</option>
<option value="completed">COMPLETED</option> --%>
							</select>
							</td>

							<td nowrap="nowrap"><a
								href="gotoupdateQuery?Id=<c:out value="${dlist.queryId}" />">Update</a>
<a
								href="deleteQuery?Id=<c:out value="${dlist.queryId}" />">Delete</a>
							</td>
							
							
					</c:if>
				</c:forEach>

			</table>
		</table>
		
		<table>
		<td ><a
								href="homeMenu">Back</a>

							</td>
		</table>







	</form>

</body>
</html>