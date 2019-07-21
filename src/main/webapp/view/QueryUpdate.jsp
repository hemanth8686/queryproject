<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body><form action="updateQuery" method="post">

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
				</tr>



				<c:forEach items="${queryById}" var="dlist">
					<tr class="tabcontent">
						<td></td>
					<tr>
					<td><input type="text" name="Id" value="<c:out value="${dlist.queryId}" />"></td>


						<td nowrap="nowrap"><fmt:formatDate pattern="dd-MM-yyyy"
								value="${dlist.raisedDate}" /></td>

						<td nowrap="nowrap"><c:out value="${dlist.raisedBy}" />
						</td>
						<td nowrap="nowrap"><c:out value="${dlist.client}" /></td>
						<td nowrap="nowrap"><c:out value="${dlist.query}" /></td>
						<td nowrap="nowrap"><c:out value="${dlist.attentedBy}" /></td>
						<td nowrap="nowrap"><c:out value="${dlist.raisedThrough}" /></td>
						<%-- <td nowrap="nowrap"> <fmt:formatDate pattern="dd-MM-yyyy"
								value="${dlist.closedDate}" /></td> --%>
								<td><input type="date" name="closedDate">
					</td>
						<td nowrap="nowrap"><select name='completedBy'>
    <c:forEach items="${employeeList}" var="dlist">
            <option value="${dlist.employeeName}">${dlist.employeeName}</option>
    </c:forEach>
</select></td>
						<td nowrap="nowrap"><select name="status"> 
            <option ><c:out value="${dlist.status}" /></option>
           <option value="pending">Pending</option>
<option value="completed">COMPLETED</option>
</select></td>


</td>
    </c:forEach>
    
<tr align="center" ><td colspan="1"><input type="submit" value="sumit query"></td></tr>
    </table>
</form>

</body>
</html>