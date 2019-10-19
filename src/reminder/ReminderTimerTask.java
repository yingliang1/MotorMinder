package reminder;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import models.Car;
import models.DBhelper;
import models.Service;
import models.User;

public class ReminderTimerTask extends TimerTask {
  
  public static boolean isRunning;

  @Override
  public void run() {
    System.out.println("Checking for upcoming services at: " + new Date().toString());
    
    // get all services from DB
    List<Service> upcomingServices = DBhelper.getServicesDueNextWeek();

    // for each upcoming service, get the user
    for (Service service : upcomingServices) {
      User user = DBhelper.getUserByUsername(service.getUsername());
      Car car = DBhelper.getCarByVin(service.getVin());
      // Send reminder email
      if (user != null) {
        System.out.println("Sending reminder email to user: " + user.getUsername());
        ReminderEmail email = new ReminderEmail();
        email.generateAndSendEmail(user, car, service);
      }
    }
  }
}
