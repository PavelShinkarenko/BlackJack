<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Login</title>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/JavaScript" 
		src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.js">
	</script>
 
	<script type="text/javascript">
		function login(){		
			var plName = $('#input_str').val();
			$.ajax({
			url: 'entry',
			type: 'POST',
			data: ({name : plName}),
			success: function(data){ 
					location.href = data
			}
			});
		}
				
	</script>
			
	</head>
	<body>
		<div id="main">
			<h3>  Enter your name</h3>
			<input id="input_str" type="text">
			<input type="button" value="Login" onclick="login()">
        </div>
	</body>
</html>