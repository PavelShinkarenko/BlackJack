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
	<script type="text/JavaScript" 
		src="${pageContext.request.contextPath}/resources/js/script.js">
	</script>
	</head>
	<body>
		<div id="game_table">
			<div id="dealer_cards">
			</div>
			<span>player bet:</span><span id="playerBet"></span><b><span id = "result"> </span></b>
			<div id="player">
				<div id="buttons">
					<h3 id="player_name"></h3>
					your money:<span id="cash"></span>
					<br><br>
					<input id="addCash" type="text" placeholder="add money">
					<input type="button" value="Add money!" onclick="addCash()">
					<br><br><br>
					<input id="betAmount" type="text" min = "1" placeholder="bet amount">
					<input id="bet" type="button" value="Bet" onclick="bet()">
					<br>
					<input id="hit" type="button" value="Hit" onclick="hit()">
					<input id="stand" type="button" value="Stand" onclick="stand()">
					<br><br><br><br>
					<input type="button" value="Exit" onclick="exit()">
				</div>
				<div id="player_cards">
				<div>
			</div>			
        </div>
	</body>
</html>