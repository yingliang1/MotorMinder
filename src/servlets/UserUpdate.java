package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.ServletHelper;
import models.MySQLDBHelper;

public class UserUpdate extends HttpServlet{
	   

	 /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	              throws ServletException, IOException {
      ServletHelper.checkSessionActive(request, response, getServletContext());
			String username1=request.getParameter("username"); 
			//String username1 = (String) request.getSession().getAttribute("username");
			Connection connection = MySQLDBHelper.getInstance().databaseconnect();
			PreparedStatement psmt = null;
		    try {  
				psmt = connection.prepareStatement("select * from user where username=?;");
				try
				{
				    psmt.setString(1, username1);
				    ResultSet rs = psmt.executeQuery();
				    while (rs.next())
				    {
					    
				    	String idUser = rs.getString("idUser");
				    	//System.out.println(idUser)
				    	
						String firstName = rs.getString("firstName");
						String lastName = rs.getString("lastName");
						String email = rs.getString("email");
						String phoneNum = rs.getString("phoneNum");
						
						 request.setAttribute("idUser", idUser);
						 request.setAttribute("username", username1);
						 request.setAttribute("firstName", firstName);
						 request.setAttribute("lastName", lastName);
						 request.setAttribute("email", email);
						 request.setAttribute("phoneNum", phoneNum);
						 request.getRequestDispatcher("/userupdate.jsp").forward(request, response);
				    }
				    
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
	     }
	   }

