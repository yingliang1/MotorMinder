<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>
</head>
<body>
	
	<textarea rows="10" cols="80" id="display_message" disabled></textarea>
	<br />
	<form>
		<input id="input_message" type="text" placeholder="message" >
		<input onclick="sendChatMessage();" value="Send Message" type="button">
	</form>
</body>
<script type="text/javascript" src="js/WebSocket.js"></script>
</html>