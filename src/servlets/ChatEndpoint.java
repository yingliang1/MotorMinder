package servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Servlet implementation class ChatEndpoint
 */
@ServerEndpoint("/ChatEndpoint")
public class ChatEndpoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
//
//	private static Map<Session, String> clients = 
//			Collections.synchronizedMap(new HashMap<Session, String>());

	private static Set<Session> clients = 
			Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void handleOpen(Session session) {
		clients.add(session);

//		clients.put(session, username);
	}
	
	@OnMessage
	public String handleMessage(String message, Session session) throws IOException {
//		String displayMessage = clients.get(session) + ": " + message;
		
		for (Session client: clients) {
			if (!client.equals(session)) {
				client.getBasicRemote().sendText(DashboardTableServlet.getUsernname() +
						": " +  message);
			}
		}
		System.out.println(message);
		
		return message;
	}
	
	@OnClose
	public void handleClose(Session session) {
		clients.remove(session);
	}
	
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
}
