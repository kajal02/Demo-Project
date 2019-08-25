<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Food data</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>
<body>
	<h2>Food Data loaded</h2>
	
	<p id="demo"></p>

	<script>
		var kajal = '${jsonList}';
		var myObj, txt="", x;
		myObj = JSON.parse(kajal);
        txt += "<table border='1'>"
        for (x in myObj) {
            txt += "<tr><td>" + myObj[x].permit + "</td>";
            txt += "<td>" + myObj[x].applicant + "</td>";
            txt += "<td>" + myObj[x].address + "</td>";
            txt += "</tr>";
        }
        txt += "</table>";    
       $("#demo").html(txt);
</script>
</body>
</html>