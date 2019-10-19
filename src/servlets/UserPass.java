package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.ServletHelper;
import models.MySQLDBHelper;

public class UserPass extends HttpServlet{
	   

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
		String username = (String) request.getSession().getAttribute("username");
		String password = request.getParameter("password");
		try {
			UserPassChange(password,username);
			PrintWriter out = response.getWriter();
			out.print("<script>alert('Update Suceess.'); window.location='userpass.jsp' </script>");
			out.flush();
			out.close();
			//request.getRequestDispatcher("/userpass.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void UserPassChange(String password,String username) throws Exception
	{

		Connection connection = MySQLDBHelper.getInstance().databaseconnect();
		PreparedStatement psmt = null;
		psmt = connection.prepareStatement("update user set password=? where username=?;");
		psmt.setString(1, password);
		psmt.setString(2, username);
		int i = psmt.executeUpdate();
		if (i >= 0)
		{
		    psmt.close();
		    connection.close();
		    System.out.println("update success");
		} else
		{
		    psmt.close();
		    connection.close();
		    System.out.println("update failed");
		}
		}
	   }
