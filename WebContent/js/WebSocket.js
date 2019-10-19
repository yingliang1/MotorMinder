		var webSocketChat = new WebSocket("ws://localhost:8080/MotorMinder/ChatEndpoint");
		var messagesTextArea = document.getElementById("display_message");
		messagesTextArea.style.color = "#000";
		webSocketChat.onopen = function(message) {
			processChatOpen(message);
		}
		
		webSocketChat.onmessage = function(message) {
			processChatMessage(message);
		}
		
		webSocketChat.onclose = function(message) {
			processChatClose(message); 
		}
		
		webSocketChat.onerror = function(message) {
			processChatError(message);
		}
		
		function processChatOpen(message) {
			
		}
		
		function processChatMessage(message) {
			messagesTextArea.innerHTML += message.data + "\n";
		}
		
		function sendChatMessage() {
			webSocketChat.send(input_message.value);
			messagesTextArea.innerHTML += "You: ";
			input_message.value = "";
		}
		
		function processChatClose(message) {
			webSocketChat.send("client disconnected...");
			messagesTextArea.innerHTML += "Server Disconnect...\n";
		}
		
		function processChatError(message) {
			
		}
		