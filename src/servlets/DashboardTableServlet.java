package servlets;

import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServlet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import models.DBhelper;

@ServerEndpoint("/DashboardTableServlet")
public class DashboardTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String username;
    public DashboardTableServlet() {
        super();
    }
    
    @OnOpen
    public void handleOpen() throws ClassNotFoundException, SQLException, ParseException {

    	return;    	
    }
    
    @OnMessage
    public String handleMessage(String msg) throws ClassNotFoundException, SQLException, ParseException {
    	String[] msgs = msg.split(",");
    	String message = "";
    	if (msgs.length == 2 && msgs[0].equals("car")) {
    		username = msgs[1];
    		System.out.println(username);
    		message = msgs[0];
    		message += DBhelper.queryCar(username);
    	} else {
    		System.out.println(message);
    		message = DBhelper.queryService(msg);
    	}
    	System.out.println("handleMessage: " + message);
    	return message;
    }
    
    @OnClose
    public void handleClose() {
    	
    }
    
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
	
	static String getUsernname() {
		return username;
	}
}