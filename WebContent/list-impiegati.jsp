<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Impiegati Tracker App</title>
	
	
	
	 <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="template/js/jquery-1.11.0.min.js"></script>
  <!-- Custom Theme files -->
  <link href="css/style.css" rel='stylesheet' type='text/css' />
  <!-- Custom Theme files -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
  <!-- Google Fonts -->
  <link href='http://fonts.googleapis.com/css?family=Doppio+One' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,700' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Lista Impiegati</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Impiegato -->
			
			<input type="button" value="Add Impiegato" 
				   onclick="window.location.href='add-impiegato-form.jsp'; return false;"
			/>
			
			<table>
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>FiscalCode</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempImpiegato" items="${IMPIEGATO_LIST}">
					
					<!-- set up a link for each impiegato -->
					<c:url var="tempLink" value="ImpiegatoControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="impiegatoId" value="${tempImpiegato.id}" />
					</c:url>

					<!--  set up a link to delete a impiegato-->
					<c:url var="deleteLink" value="ImpiegatoControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="impiegatoId" value="${tempImpiegato.id}" />
					</c:url>
																		
					<tr>
						<td> ${tempImpiegato.firstName} </td>
						<td> ${tempImpiegato.lastName} </td>
						<td> ${tempImpiegato.fiscalCode} </td>
						<td> ${tempImpiegato.email} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Sei sicuro di cancellare questo impiegato?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>