package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.ServletHelper;
import models.DBhelper;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DashboardServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletHelper.checkSessionActive(request, response, getServletContext());
    
		System.out.println(request.getParameter("userRealName"));
		System.out.println(request.toString());
		
		String message = "";
		if (request.getParameter("type").equals("car")) {
			try {
				message += DBhelper.queryCar(request.getParameter("username"));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("type").equals("service")) {
			try {
				message += DBhelper.queryServiceByUsername(request.getParameter("username"));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("type").equals("username")) {
			message = request.getSession().getAttribute("username").toString();
		}
			
			
		response.getWriter().append(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		
		InputStream is = request.getInputStream();
		
		try {
			if (is != null) {
				br = new BufferedReader(new InputStreamReader(is));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = br.read(charBuffer)) > 0) {
					sb.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		if (br != null) {
			br.close();
		}
		
		message = sb.toString();
		try {
			if (request.getParameter("type").equals("updateC")) {
				DBhelper.updateCar(message);
			} else if (request.getParameter("type").equals("insertC")) {
				DBhelper.insertCar(message);
			} else if (request.getParameter("type").equals("updateS")) {
				DBhelper.updateService(message);
			} else if (request.getParameter("type").equals("insertS")) {
				DBhelper.insertService(message);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
