package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.DBhelper;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	    rd.forward(request, response);
	  }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
				
		String userRealName = null;
		RequestDispatcher dispatcher = null;
		try {
			if(DBhelper.checkValidUser(userName)) {
			 	String errorMsg = "User name already exists.";
			 	request.setAttribute("errorMsg", errorMsg);
			 	dispatcher = getServletContext().getRequestDispatcher("/create.jsp");
			 	
			}
			else {
				DBhelper.insertUser(userName, password, firstName, lastName, email, phoneNumber);
				userRealName = DBhelper.attemptToLoginUser(userName, password);
				
				if("".equals(userRealName) || userRealName == null) {
					dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
					String errMessage = "Could not create account. Try again later.";
					request.setAttribute("errMessage", errMessage);
				}
				else {
					HttpSession session = request.getSession();
					session.setAttribute("userRealName", userRealName);
					session.setAttribute("username", userName);
					dispatcher = getServletContext().getRequestDispatcher("/dashboard.jsp");
				}
			}
			
			dispatcher.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
