<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Free SRT Synchronizing tool</title>

<script src="js/scripts.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href="https://fonts.googleapis.com/css?family=Titillium+Web" rel="stylesheet">


</head>
<body>
<div class="body-container">
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- srtsynch -->
<ins class="adsbygoogle"
     style="display:block"
     data-ad-client="ca-pub-4149796698660066"
     data-ad-slot="3475790328"
     data-ad-format="auto"
     data-full-width-responsive="true"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script>

<%
	if(request.getAttribute("message") !=null){
%>
	<div id="message">
		<font color="red">
			<h4><%= request.getAttribute("message") %></h4>
		</font>
	</div>

<%} %>
	<div class="main-container">
		<div class="instructions-box">
			<div class="header1">Upload the SRT file that you would like to syncrhonize</div>
			<div class="header2">You may specify negative or positive values depending if you want to add or substract time to the original SRT file</div>
		</div>
		
		<form action="Syncrhonize" method="post" enctype="multipart/form-data">
		<div class="menu-container">
			<div class="row">
				<div class="column">
					<label>Srt file</label>
				</div>
				<div class="column">
					<input type="file" name="file" class="form-element"/>
				</div>
			</div>

			<div class="row">
				<div class="column">
					<label>Hours</label>
				</div>
				<div class="column">
					<input  type="number" onkeypress="return isNumber(event)"  value="0" name="hours" class="form-element"/>
				</div>
			</div>

			<div class="row">
				<div class="column">
					<label>Minutes</label>
				</div>
				<div class="column">
					<input  type="number" onkeypress="return isNumber(event)"  value="0" name="minutes" class="form-element"/>
				</div>
			</div>

			<div class="row">
				<div class="column">
					<label>Seconds</label>
				</div>
				<div class="column">
					<input  type="number" onkeypress="return isNumber(event)"  value="0" name="seconds" class="form-element"/>
				</div>
			</div>

			<div class="button-container">
				<input type="submit" value="Synchronize" onclick="hideMessage()" class="srt-button">
			</div>
		</div>
		</form>
		<div class="instructions-box">
			<div class="header2">
				This web app is open source. You can see its source code at <a href="https://github.com/raydelto/srtsynch" target="new">GitHub</a> and make it better.
			</div>
			<div class="header2">
				You can download the Java Desktop version of this app from  <a href="http://www.raydelto.org/bin/SrtSynch.jar" target="new">this link</a>.
			</div>
		</div>
	</div>
</div>

</body>
</html>
