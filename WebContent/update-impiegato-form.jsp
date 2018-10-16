<!DOCTYPE html>
<html>

<head>
	<title>Update Impiegato</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Impiegato tracker</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Impiegato</h3>
		
		<form action="ImpiegatoControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="impiegatoId" value="${THE_IMPIEGATO.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" 
								   value="${THE_IMPIEGATO.firstName}" /></td>
					</tr>
					
					

					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" 
								   value="${THE_IMPIEGATO.lastName}" /></td>
					</tr>
					
					<tr>
						<td><label>Fiscal Code:</label></td>
						<td><input type="text" name="fiscalCode" 
								   value="${THE_IMPIEGATO.fiscalCode}" /></td>
					</tr>
					
					
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" 
								   value="${THE_IMPIEGATO.email}" /></td>
					</tr>
					
					

					
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="ImpiegatoControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











