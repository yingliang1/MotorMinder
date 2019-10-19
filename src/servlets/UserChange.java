package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.ServletHelper;
import models.MySQLDBHelper;

public class UserChange  extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	              throws ServletException, IOException {
    ServletHelper.checkSessionActive(request, response, getServletContext());
		session = request.getSession();
		String username = (String) session.getAttribute("username");
		String idUser=request.getParameter("idUser");
		//System.out.println(idUser);
		String username1=request.getParameter("username");
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String phoneNum=request.getParameter("phoneNum");
		
		Connection connection = MySQLDBHelper.getInstance().databaseconnect();
		PreparedStatement psmt = null;
	    try {
			psmt = connection.prepareStatement("update user set username=?,"
					+ "firstName=?,"
					+ "lastName=?,email=?,phoneNum=? "
					+ "where username=?;");
			try
			{
			    psmt.setString(1, username1);
			    psmt.setString(2, firstName);
			    psmt.setString(3, lastName);
			    psmt.setString(4, email);
			    psmt.setString(5, phoneNum);
			    psmt.setString(6, username);
			    psmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			   PreparedStatement psmt1 = null;
			   psmt1 = connection.prepareStatement("select idUser from user where username=? ");
			  try {
				  psmt1.setString(1, username);
				 
			  }catch (SQLException e)
				{
					e.printStackTrace();
				}
			   request.setAttribute("idUser", idUser);
			   request.setAttribute("username", username1);
			   request.setAttribute("firstName", firstName);
			   request.setAttribute("lastName", lastName);
			   request.setAttribute("email", email);
			   request.setAttribute("phoneNum", phoneNum);
			   PrintWriter out = response.getWriter();
			   out.print("<script>alert('Update Suceess.');</script>");
			   request.getRequestDispatcher("/userupdate.jsp").forward(request, response);
			  
			    
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		
	}
	}

