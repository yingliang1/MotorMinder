package helper;

import java.io.IOException;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reminder.ReminderTimerTask;

public class ServletHelper {
  
  public static void checkSessionActive(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
    String usernameParam = (String) request.getSession().getAttribute("username");
    if(usernameParam==null || usernameParam.equals("")) {
      String errorMsg = "Session expired, please log in again.";
      request.setAttribute("errorMsg", errorMsg);
      RequestDispatcher rd = context.getRequestDispatcher("/login.jsp");
      rd.forward(request, response);
    }
  }
  
  public static void startReminderTimerTask() {
    ReminderTimerTask.isRunning = true;
    
    Timer timer = new Timer();
    ReminderTimerTask reminderTask = new ReminderTimerTask();
    
    long delay  = 1000L;
    //Once per day
    long period = 1000L * 60L * 60L * 24L;
    timer.scheduleAtFixedRate(reminderTask, delay, period);
  }

}
