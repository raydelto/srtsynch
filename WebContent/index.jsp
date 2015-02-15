<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Free SRT Synchronizing tool</title>

<script src="js/scripts.js"></script>


</head>
<body bgcolor="black">
<div align="center">
<%
	if(request.getAttribute("message") !=null){
%>
	<div id="message">
		<font color="red">
			<h4><%= request.getAttribute("message") %></h4>	
		</font>
	</div>

<%} %>
	<font color="white">
		<h3>Upload the SRT file that you would like to syncrhonize</h3>
		<h4>You may specify negative or positive values depending if you want to add or substract time to the original SRT file.</h3>
		<form action="Syncrhonize" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>Srt file</td><td><input type="file" name="file"/></td>
			</tr>
			<tr>
				<td>Hours</td><td><input  type="number"  onkeypress="return isNumber(event)"  value="0" name="hours" /></td>
			</tr>
			<tr>
				<td>Minutes</td><td><input  type="number"  onkeypress="return isNumber(event)" value="0" name="minutes"/></td>
			</tr>
			<tr>
				<td>Seconds</td><td><input  type="number"  onkeypress="return isNumber(event)" value="0" name="seconds"/></td>
			</tr>
			<tr>
			<td colspan="2">
				<input type="submit" value="Synchronize"/ style="width:100%" onclick="hideMessage()">
			</td>
			</tr>			
		</table>
		</form>
		
		<p><b>This web app is open source. You see its source code at <a href="https://github.com/raydelto/srtsynch">GitHub</a> and make it better.</b></p>
		
	</font>
</div>

</body>
</html>