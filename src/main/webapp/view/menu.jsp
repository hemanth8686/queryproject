<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
<
style>.container {
	position: relative;
	text-align: center;
	color: white;
}

.bottom-left {
	position: absolute;
	bottom: 8px;
	left: 16px;
}

.top-left {
	position: absolute;
	top: 8px;
	left: 16px;
}

.top-right {
	position: absolute;
	top: 8px;
	right: 16px;
}

.bottom-right {
	position: absolute;
	bottom: 8px;
	right: 16px;
}

.centered {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

img {
	border-radius: 100%;
}

#mySidenav a {
	position: absolute;
	left: -10px;
	transition: 0.1s;
	padding: 15px;
	width: 150px;
	text-decoration: none;
	font-size: 20px;
	color: white;
	border-radius: 0 5px 5px 0;
}

#mySidenav a:hover {
	left: 0;
}

#about {
	top: 30px;
	background-color: #4CAF50;
}

#blog {
	top: 85px;
	background-color: #2196F3;
}

#projects {
	top: 140px;
	background-color: #f44336;
}

#contact {
	top: 200px;
	background-color: #555;
}

#event {
	top: 260px;
	background-color: skyblue;
}

#projects {
	top: 140px;
	background-color: #f44336;
}

#list {
	top: 330px;
	background-color: orange;
}

#mail {
	top: 390px;
	background-color: #FA8072;
}

#profile {
	top: 390px;
	background-color: #008080;
}

#calculate {
	top: 450px;
	background-color: #f44336;
}

.middle {
	transition: .5s ease;
	opacity: 0;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%)
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="">





		<div id="mySidenav" class="sidenav">
			<table>

				<tr>
					<td><a href="gotoQuery" id="about">Query</a><br></td>
				</tr>
				<tr>
					<td><a href="gotoQueryReport" id="blog">QueryReport</a><br></td>
				</tr>




				<tr>
					<td><a href="gotoAddClient" id="projects">AddClient</a><br></td>
				</tr>
				<tr>
					<td><a href="gotoAddClient" id="contact">AddClient</a><br></td>
				</tr>
				<tr>
					<td><a href="gotoAddEmployee" id="event">AddEmployee</a><br></td>
				</tr>

			</table>
		</div>
	</form>

</body>
</html>