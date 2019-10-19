package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.ServletHelper;
import models.DBhelper;
import reminder.ReminderTimerTask;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = -4005026852514057123L;

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    /*
     * Taylor Basso:
     * I decided to implement the timer task for reminder emails in an easier way for the sake of this
     * group project... The correct way would have been to extract this out and have it in its own jar.
     * 
     * For the sake of this group project, we will just trigger the timer task here. 
     */
    if(!ReminderTimerTask.isRunning) {
      System.out.println("Not running, starting now");
      ServletHelper.startReminderTimerTask();
    }
    
    //Is user already logged in? If so, redirect to dashboard.
    if(request.getSession() != null && request.getSession().getAttribute("username") !=null) {
      response.sendRedirect("dashboard.jsp");
    } else {
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
      rd.forward(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String hashedPassword = request.getParameter("password");
    String userRealName = null;
    
    //This is validated client side in javascript, but doing server side validation as well.
    if(username == null || hashedPassword == null || username.equals("") || hashedPassword.equals("")) {
      String errorMsg = "Login failed. Empty username or password.";
      request.setAttribute("errorMsg", errorMsg);
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
      rd.forward(request, response);
    }

    try {
      userRealName = DBhelper.attemptToLoginUser(username, hashedPassword);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // If user login failed...
    if (null == userRealName || userRealName == "") {
      String errorMsg = "Login failed.";
      request.setAttribute("errorMsg", errorMsg);
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
      rd.forward(request, response);
    } else {
      HttpSession session = request.getSession();
      session.setAttribute("userRealName", userRealName);
      session.setAttribute("username", username);
      response.sendRedirect("dashboard.jsp");
    }
  }

}